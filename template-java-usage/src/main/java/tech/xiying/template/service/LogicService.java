package tech.xiying.template.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @ClassName LogicService
 * @Description
 * @Author shanghao5
 * @Date 2021/3/20 15:24
 **/
public interface LogicService {

    /**
     * 文件处理
     *
     * @param file
     */
    void fileHandle(MultipartFile file) throws IOException;
}
