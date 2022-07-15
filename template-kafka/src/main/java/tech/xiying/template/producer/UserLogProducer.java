package tech.xiying.template.producer;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import tech.xiying.template.dto.UserLog;

@Component
public class UserLogProducer {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    /**
     * 发送数据
     * @param userid
     */
    public void sendLog(String userid){
        UserLog userLog = new UserLog();
        userLog.setUsername("jhp").setUserId(userid).setState("0");
        System.err.println("发送用户日志数据:"+userLog);
        kafkaTemplate.send("user-log", new Gson().toJson(userLog));
    }

}
