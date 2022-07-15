package tech.xiying.template.service.impl;

import jef.tools.csvreader.CsvReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tech.xiying.template.constant.ErrorCode;
import tech.xiying.template.exception.CommonRuntimeException;
import tech.xiying.template.service.LogicService;

import java.io.*;

/**
 * @ClassName LogicServiceImpl
 * @Description
 * @Author shanghao5
 * @Date 2021/3/20 15:24
 **/
@Service
public class LogicServiceImpl implements LogicService {

    private static Logger logger = LoggerFactory.getLogger(LogicServiceImpl.class);


    @Override
    public void fileHandle(MultipartFile file) throws IOException {
        paramCheck(file);

        File csvFile = handleFile(file);

        BufferedReader reader = null;
        try{
            reader = new BufferedReader(new FileReader(csvFile));
            CsvReader csvReader = new CsvReader(reader);
            // csv头
            csvReader.readHeaders();

            // 每行读取
            while(csvReader.readRecord()) {
                Integer status = Integer.valueOf(csvReader.get("status"));
                // 根据头获取对应列的字段
                csvReader.get("name");
                csvReader.get("telephone");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(reader != null){
                reader.close();
            }
        }
    }

    /**
     * 参数校验
     * @param file
     */
    private void paramCheck(MultipartFile file){
        if(file == null){
            throw new CommonRuntimeException(ErrorCode.ERR_COMMON_ERROR.getErrorCode(), "file can`t null");
        }
    }

    /**
     * 上传文件处理
     *
     * @param fileContent
     * @return
     */
    private File handleFile(MultipartFile fileContent){
        // 文件处理
        ApplicationHome home = new ApplicationHome(getClass());
        //获取jar路径
        String tmpDir = home.getDir().getParentFile().toString() + File.separator + "tmpdata" + File.separator;

        File directoryFile = new File(tmpDir);
        if (!directoryFile.exists() || !directoryFile.isDirectory()) {
            directoryFile.mkdir();
        }
        // 文件放置位置
        String filepath = tmpDir + fileContent.getOriginalFilename();
        File file = new File(filepath);
        try {
            fileContent.transferTo(file);
        } catch (IOException exception) {
            logger.warn("file transfer error");
        }
        return file;
    }
}
