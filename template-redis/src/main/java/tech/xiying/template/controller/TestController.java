package tech.xiying.template.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TestController
 * @Description
 * @Author shanghao5
 * @Date 2021/3/19 17:06
 **/
@RestController
public class TestController {

    @GetMapping({ "/test" })
    public void test() {

    }

}
