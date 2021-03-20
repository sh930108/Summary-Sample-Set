package tech.xiying.template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import tech.xiying.template.api.ControllerResult;
import tech.xiying.template.service.LogicService;

import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName FileController
 * @Description
 * @Author shanghao5
 * @Date 2021/3/20 14:59
 **/
@RestController
public class FileController extends BaseController{

    @Autowired
    private LogicService logicService;

    /**
     * 文件处理controller类
     *
     * @param file
     * @param response
     */
    @PostMapping(value = "/file-handle")
    public ControllerResult<String> fileHandle(@RequestParam(required = true) MultipartFile file,
                                          HttpServletResponse response){



        return getControllerResult(response,"ok");
    }


}
