package tech.xiying.template.consumer;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @ClassName NetDomainChangeConsumer
 * @Description
 * @Author shanghao5
 * @Date 2020/4/23 11:14
 **/
@Component
public class MqExampleConsumer {

    private static Logger logger = LoggerFactory.getLogger(MqExampleConsumer.class);

    @PostConstruct
    public void initConsumer(){

    }


}
