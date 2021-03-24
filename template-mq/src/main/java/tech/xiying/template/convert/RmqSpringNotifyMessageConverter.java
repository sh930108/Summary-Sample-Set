package tech.xiying.template.convert;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;

import java.io.UnsupportedEncodingException;


/**
 * @ClassName RmqSpringNotifyMessageConverter
 * @Description
 * @Author shanghao5
 * @Date 2020/2/24 17:10
 **/
public class RmqSpringNotifyMessageConverter extends SimpleMessageConverter implements MessageConverter {

//    @Override
//    public Object fromMessage(Message message) throws MessageConversionException {
//        Object object = super.fromMessage(message);
//        if (object instanceof byte[]) {
//            try {
//                object = new String((byte[]) object,"UTF-8");
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
//        }
//        return new TextNotifyMessage(object.toString());
//    }
}
