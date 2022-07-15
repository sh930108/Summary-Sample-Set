package tech.xiying.template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tech.xiying.template.producer.UserLogProducer;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class MqTemplateApplication {
    @Autowired
    private UserLogProducer kafkaSender;
    @PostConstruct
    public void init(){
        for (int i = 0; i < 10; i++) {
            //调用消息发送类中的消息发送方法
            kafkaSender.sendLog(String.valueOf(i));
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(MqTemplateApplication.class, args);
    }

}
