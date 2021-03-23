package tech.xiying.template.config;

import com.google.common.collect.Maps;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.SimplePropertyValueConnectionNameStrategy;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.xiying.template.config.ssl.NoCheckTrustManager;
import tech.xiying.template.constant.CommonConstant;
import tech.xiying.template.constant.enums.MqEnum;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import java.util.Map;

/**
 * @ClassName RabbitmqConfig
 * @Description
 * @Author shanghao5
 * @Date 2021/3/23 17:08
 **/
@Configuration
@EnableConfigurationProperties(MqInfoProperties.class)
public class RabbitmqConfig {

    @Bean
    public ConnectionFactory rabbitmqConnFactory(MqInfoProperties mqInfoProperties){
        ConnectionFactory rmqFactory = new ConnectionFactory();
        //设置服务地址
        rmqFactory.setHost(mqInfoProperties.getIp());
        //端口
        //amqp协议 端口 类似与mysql的3306
        rmqFactory.setPort(Integer.valueOf(mqInfoProperties.getSslPort()));
        //设置账号信息，用户名、密码、vhost
        rmqFactory.setVirtualHost("/");
        rmqFactory.setUsername(mqInfoProperties.getUserName());
        rmqFactory.setPassword(mqInfoProperties.getPassword());
        rmqFactory.setAutomaticRecoveryEnabled(true);

        // ssl处理
        if(StringUtils.isNotBlank(mqInfoProperties.getSslPort())){
            try {
                SSLContext sslContext = SSLContext.getInstance(CommonConstant.TLS_TYPE);
                // 跳过验证
                TrustManager[] dummyTrustManager = new TrustManager[] { new NoCheckTrustManager() };
                sslContext.init(null, dummyTrustManager, null);
                rmqFactory.useSslProtocol(sslContext);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return rmqFactory;
    }

    @Bean
    public CachingConnectionFactory rabbitmqCachingConnectionFactory(ConnectionFactory connectionFactory){
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(connectionFactory);
        cachingConnectionFactory.setConnectionNameStrategy(new SimplePropertyValueConnectionNameStrategy("hello-rabbitmq"));
        return cachingConnectionFactory;
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory){
        RabbitAdmin rabbitAdmin = new RabbitAdmin(rabbitmqCachingConnectionFactory(connectionFactory));
        // 声明exchange
        Map<String, Object> arguments = Maps.newHashMap();
        // 设置队列超时时间为10秒
        arguments.put("x-message-ttl", 24*60*60*1000L);
        // 队列过期时间，队列在多长时间未被访问将被删除，单位：毫秒；
        arguments.put("x-expires", 24*60*60*1000L);

        Exchange exchange = new FanoutExchange(MqEnum.ExchangeTypeEnum.FANOUT.getDesc(),true,false);
        rabbitAdmin.declareExchange(exchange);
        // 声明队列
        rabbitAdmin.declareQueue(new Queue("hello",
                true,false,false,arguments));
        // 绑定交换机
        rabbitAdmin.declareBinding(new Binding("hello",
                Binding.DestinationType.QUEUE,
                MqEnum.ExchangeTypeEnum.FANOUT.getDesc(),
                "hello", Maps.newHashMap()));
        return rabbitAdmin;
    }



}
