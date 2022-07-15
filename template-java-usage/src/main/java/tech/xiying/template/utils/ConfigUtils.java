package tech.xiying.template.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.system.ApplicationHome;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @ClassName ConfigUtils
 * @Description
 * @Author shanghao5
 * @Date 2020/5/18 9:46
 **/
public class ConfigUtils {

    public ConfigUtils(){}

    /**
     * 获取组件路径
     *
     * @return 组件路径
     */
    public String getBasePath() {
        ApplicationHome h = new ApplicationHome(getClass());
        File jarF = h.getSource();
        //jar 在script/mq/configsync 中
        String basePath = jarF.getParentFile().getParentFile().getParentFile().getParentFile().toString();
        System.out.println("basePath:" + basePath);
        return basePath;
    }

    /**
     * 获取config.properties 中配置
     * @param basePath
     * @return
     * @throws IOException
     */
    public Properties getConfigProperties(String basePath) throws IOException {
        String configPath = basePath + File.separator + "conf" + File.separator + "config.properties";
        File installFile = new File(configPath);
        Properties properties = new Properties();
        properties.load(new FileInputStream(installFile));
        return properties;
    }

    /**
     * 获取实例号
     * @param basePath
     * @return
     * @throws IOException
     */
    public Integer getIndex(String basePath) throws IOException {
        String configPath = basePath + File.separator + "conf" + File.separator + "installation.properties";
        File installFile = new File(configPath);
        Properties properties = new Properties();
        properties.load(new FileInputStream(installFile));
        String index = properties.getProperty("rabbitmq.@index");
        if(StringUtils.isBlank(index)){
            return 1;
        }else{
            return Integer.valueOf(index);
        }
    }

    /**
     * 获取集成服务的根目录
     *
     * @return
     */
    public String getBicPath(){
        ApplicationHome h = new ApplicationHome(getClass());
        File jarF = h.getSource();
        //jar 在script/mq/configsync 中 4层到rabbitmq下，再三层到@bic下
        String bicPath = jarF.getParentFile().getParentFile().getParentFile().getParentFile().
                getParentFile().getParentFile().getParentFile().toString();
        System.out.println("==================================");
        System.out.println("bic path:" + bicPath);
        System.out.println("==================================");
        return bicPath;
    }
}
