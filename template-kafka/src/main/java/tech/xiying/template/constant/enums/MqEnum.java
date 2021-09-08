package tech.xiying.template.constant.enums;

/**
 * @ClassName MqEnum
 * @Description
 * @Author shanghao5
 * @Date 2021/3/23 16:20
 **/
public class MqEnum {

    /**
     * mq 连接枚举类
     * 协议类
     */
    public enum MqTypeEnum {
        AMQ(1,"amq"),
        RMQ(2,"rabbitmq"),
        ;
        /**
         * mq 类型
         */
        private int mqType;

        /**
         * 选型描述
         */
        private String desc;

        MqTypeEnum(int mqType, String desc) {
            this.mqType = mqType;
            this.desc = desc;
        }

        public int getMqType() {
            return mqType;
        }
        public String getDesc() {
            return desc;
        }

        public MqTypeEnum matchMqType(String mqType){
            for(MqTypeEnum typeEnum : MqTypeEnum.values()){
                if(typeEnum.getDesc().equalsIgnoreCase(mqType)){
                    return typeEnum;
                }
            }
            return RMQ;
        }
    }

    /**
     * mq 连接枚举类
     * 协议类
     */
    public enum MqProtocolEnum {
        AMQ_TCP(1,"tcp"),
        AMQ_SSL(2,"ssl"),
        RMQ_AMQP(3,"amqp"),
        RMQ_SSL(4,"amqpSsl"),
        ADDRESS_TCP(5,"tcp"),
        ADDRESS_SSL(6,"ssl"),
        ;
        private int type;
        private String desc;

        MqProtocolEnum(int type,String desc){
            this.type = type;
            this.desc = desc;
        }

        public int getType() {
            return type;
        }

        public String getDesc() {
            return desc;
        }
    }

    /**
     * RMQ 交换机类型枚举
     */
    public enum ExchangeTypeEnum{
        DIRECT(1,"direct"),
        TOPIC(1,"topic"),
        FANOUT(1,"fanout"),
        ;

        /**
         * 类型
         */
        private int type;
        /**
         * 描述
         */
        private String desc;

        ExchangeTypeEnum(int type, String desc) {
            this.type = type;
            this.desc = desc;
        }

        public int getType() {
            return type;
        }
        public String getDesc() {
            return desc;
        }
    }
}
