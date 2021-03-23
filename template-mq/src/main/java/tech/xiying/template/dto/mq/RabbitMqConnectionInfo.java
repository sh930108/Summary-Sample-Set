package tech.xiying.template.dto.mq;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName RabbitMqConnectionInfo
 * @Description
 * @Author shanghao5
 * @Date 2020/6/16 10:51
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RabbitMqConnectionInfo {

    private String vHost = "/";

}
