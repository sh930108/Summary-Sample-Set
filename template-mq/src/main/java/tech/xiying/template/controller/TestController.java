package tech.xiying.template.controller;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName TestController
 * @Description
 * @Author shanghao5
 * @Date 2021/3/19 17:06
 **/
@RestController
public class TestController {

    @Resource(name = "helloRabbitTemplate")
    private RabbitTemplate rabbitTemplate;

    @GetMapping({ "/test" })
    public void test() {
        MessageProperties messageProperties = new MessageProperties();
        // 设置消息格式
        messageProperties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
        // 设置消息过期时间
//        messageProperties.setExpiration("");
        Message rmqMessage = new Message("hello".getBytes(),messageProperties);
        rabbitTemplate.sendAndReceive(rmqMessage);
    }

}
