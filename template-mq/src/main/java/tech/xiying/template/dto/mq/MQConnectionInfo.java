package tech.xiying.template.dto.mq;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.xiying.template.constant.enums.MqEnum;

/**
 * 连接信息：协议，ip和端口
 *
 * @ClassName ConnectInfo
 * @Description
 * @Author shanghao5
 * @Date 2020/2/25 11:37
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MQConnectionInfo {

    /** 连接标识：请遵循规范填写 */
    private String clientId;
    /**********************/

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
     * 使用MQ枚举值：使用方根据配置中key值mqType 进行判断
     */
    private MqEnum.MqTypeEnum mqTypeEnum;

    /**
     * 启动时需要重连连接次数（非运行期，运行期默认无限重连）
     *
     * 默认是-1 无限重连
     */
    private Integer startReconnectTime = -1;

    /*******************  支持多ip连接MQ集群参数 **********************/
     /**
     * 多ip 集群方式支持；
     * 格式：ip1:port,ip2:port,ip3:port ...  例：10.19.158.110:7017,10.19.158.111:7017,10.19.158.112:7017
     *
     * mqTypeEnum 必传
     */
    private String addresses;
    /**
     * 集群使用的是否为ssl
     */
    private String addressesProtocol = MqEnum.MqProtocolEnum.ADDRESS_TCP.getDesc();
    /**************************************************************/

    /**  rabbitmq 选型专属连接信息 **/
    private RabbitMqConnectionInfo rabbitMqConnectionInfo = new RabbitMqConnectionInfo();

}
