package tech.xiying.template.yaml;


import com.google.gson.Gson;
import org.junit.Test;
import tech.xiying.template.utils.YamlConvertUtil;

import java.util.Map;


/**
 * @ClassName HandlerTest
 * @Description
 * @Author shanghao5
 * @Date 2020/9/25 15:10
 **/
public class YamlTest {

    private  final Gson gson = new Gson();

    @Test
    public void transformComponentDtoDto(){
        String yamlContent = "#####";
        String s = System.lineSeparator();
        Map<String, Object> stringObjectMap = YamlConvertUtil.yamlToFlattenedMap(yamlContent);
        String yaml = YamlConvertUtil.flattenedMapToYaml(stringObjectMap);
        System.out.println(yaml);
    }


}
