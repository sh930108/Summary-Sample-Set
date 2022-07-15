package tech.xiying.template.dto;


import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserLog {

    private String username;
    private String userId;
    private String state;
}
