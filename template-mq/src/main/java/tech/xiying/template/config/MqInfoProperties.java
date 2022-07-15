package tech.xiying.template.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ClassName MqInfoProperties
 * @Description
 * @Author shanghao5
 * @Date 2021/3/23 20:07
 **/
@Data
@ConfigurationProperties(prefix = "mq.info")
public class MqInfoProperties {

    private String ip = "127.0.0.1";
    private String port;
    /***
     * ssl 加密传输端口
     * ssl端口有值则默认使用ssl端口,无则使用port
     */
    private String sslPort;
    private String userName;
    private String password;

    /**
     * @link MqEnum.MqTypeEnum
     */
    private String mqType;
}
