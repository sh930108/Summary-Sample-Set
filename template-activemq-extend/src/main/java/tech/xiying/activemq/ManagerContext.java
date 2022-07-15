package tech.xiying.activemq;

import org.apache.activemq.broker.BrokerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;

/**
 * @ClassName ManagerContext
 * @Description
 * @Author shanghao5
 * @Date 2020/9/1 11:09
 **/
public class ManagerContext implements ApplicationContextAware {

    private static final transient Logger logger = LoggerFactory.getLogger(ManagerContext.class);

    private ApplicationContext springContext;
    /**
     * 由manager工程使用获取监控信息
     */
    public static BrokerService broker=null;

    @PostConstruct
    public void start() {
        broker = springContext.getBean(BrokerService.class);
        logger.info("==========================");
        logger.info("manager broker name:" + broker.getBrokerName()+",isUseJmx:"+broker.isUseJmx());
        logger.info("==========================");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.springContext = applicationContext;

    }
}
