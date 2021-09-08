package tech.xiying.template.consumer;


import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;


/**
 * @ClassName NetDomainChangeConsumer
 * @Description
 * @Author shanghao5
 * @Date 2020/4/23 11:14
 **/
@Component
@Slf4j
public class UserLogConsumer {

    private static Logger logger = LoggerFactory.getLogger(UserLogConsumer.class);

    @KafkaListener(groupId = "user-log",topics = {"user-log"})
    public void consumer(ConsumerRecord<?,?> consumerRecord){
        //判断是否为null
        Optional<?> kafkaMessage = Optional.ofNullable(consumerRecord.value());
        log.info(">>>>>>>>>> record =" + kafkaMessage);
        if(kafkaMessage.isPresent()){
            //得到Optional实例中的值
            Object message = kafkaMessage.get();
            System.err.println("消费消息:"+message);
        }
    }


}
