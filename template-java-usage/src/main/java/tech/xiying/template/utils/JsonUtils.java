package tech.xiying.template.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * json 工具类
 */
public class JsonUtils {
    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        String DATA_FORMATTER_ISO8601 = "yyyy-MM-dd'T'HH:mm:ssXXX";
        SimpleDateFormat sdf = new SimpleDateFormat(DATA_FORMATTER_ISO8601);
        objectMapper.setDateFormat(sdf);
    }
    protected JsonUtils() {
    }

    ;

    public static String object2Json(Object obj) {
        if (obj == null) {
            return null;
        }
        String result = null;
        try {
            result = objectMapper.writeValueAsString(obj);
        } catch (IOException e) {
            logger.error("convert to json string error", e);
        }
        return result;
    }

    public static Map<?, ?> jsonToMap(String json) {
        return json2Object(json, Map.class);
    }

    public static <T> T json2Object(String json, Class<T> cls) {
        T result = null;
        try {
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            result = objectMapper.readValue(json, cls);
        } catch (IOException e) {
            logger.error("convert to json object error with json string: " + json, e);
        }

        return result;
    }

    public static <T> T conveterObject(Object srcObject, Class<T> destObjectType) {
        String jsonContent = object2Json(srcObject);
        return json2Object(jsonContent, destObjectType);
    }

    public static <T> List<T> fromJsonList(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (IOException e) {
            logger.error("convert json to list error, json string:" + json, e);
            return null;
        }
    }

    public static String getJsonValue(String json, String key) {
        String value = null;
        try {
            JsonNode node = objectMapper.readTree(json);
            value = node.path(key).textValue();
        } catch (IOException e) {
            logger.error("get key " + key + " error", e);
        }
        return value;
    }

    public static Map<String, String> getAttributeMap(String src) {
        Map<String, String> result = new HashMap<String, String>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            @SuppressWarnings("unchecked")
            Map<String, Map<String, Object>> maps = objectMapper.readValue(src, Map.class);
            if (null != maps && maps.size() != 0) {
                for (Map.Entry<String, Map<String, Object>> entity : maps.entrySet()) {
                    result.put(entity.getKey(), String.valueOf(entity.getValue()));
                }
            }
        } catch (Exception e) {
            logger.error("convert the json string to map error, json string: " + src, e);
        }
        return result;
    }

    public static <T> T toObject(String json, TypeReference<T> type) {
        T t = null;
        try {
            // 设置容忍未知属性
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            t = objectMapper.readValue(json, type);
        } catch (IOException e) {
            logger.error("errors in parsing json to object!", e);
        }
        return t;
    }
}
