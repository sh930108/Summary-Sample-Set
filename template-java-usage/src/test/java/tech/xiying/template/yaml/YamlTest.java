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
        String yamlContent = "#config variables begin\\r\\nmiddleware:\\r\\n  consul:\\r\\n    host: localhost\\r\\n    port: 8500\\r\\n\\r\\n  postgresql:\\r\\n    version: 11.4\\r\\n    host: 10.97.164.163\\r\\n    port: 5432\\r\\n    dbname: order\\r\\n    username: postgres\\r\\n    password: Hik3Yun2Yao1Ps\\r\\n    driverclass: org.postgresql.Driver\\r\\n\\r\\n  redis:\\r\\n    node: 10.97.164.163:6379,10.97.164.163:6380,10.97.164.163:6381,10.97.164.163:6382,10.97.164.163:6383,10.97.164.163:6384,10.97.164.163:6385,10.97.164.163:6386\\r\\n    password: Hik3Yun2Yao1Rs\\r\\n    version: 5.0.5\\r\\n\\r\\n  rabbitmq:\\r\\n    host: 10.97.164.160\\r\\n    port: 5672\\r\\n    username: admin\\r\\n    password: Hik3Yun2Yao1Rq\\r\\n\\r\\n  kafka:\\r\\n    bootstrapServers: 10.215.255.44:9092 #kafka服务器地址 多个以,号隔开\\r\\n    producer:\\r\\n      retries: 5\\r\\n      batchSize: 33554432\\r\\n      bufferMemory: 33554432\\r\\n      acks: \\\"all\\\"\\r\\n      keySerializer: org.apache.kafka.common.serialization.StringSerializer\\r\\n      valueSerializer: org.apache.kafka.common.serialization.StringSerializer\\r\\n      zkUrl: 10.215.255.44:2181/kafka\\r\\n\\r\\nsleuth:\\r\\n  ampler:\\r\\n    probability: 0.2\\r\\nartemisUrl: http://yunyao-apigw-svc/\\r\\n\\r\\nconfigs:\\r\\n  profile: pre\\r\\n\\r\\n#config variables end\\r\\n\\r\\n#deployment variables begin\\r\\nreplicas: 2\\r\\ninitContainer:\\r\\n  image:\\r\\n    repository: af.hikvision.com.cn/docker-pbg/tianmu/tianmu-centos7.3-jdk11\\r\\n    tag: 1.0.0\\r\\n  imagePullPolicy: IfNotPresent\\r\\n  #command建议固话在镜像的 endpoint里, 除非需要调试, 再覆盖替换\\r\\n  command:\\r\\n    - \\\"sh\\\"\\r\\n    - \\\"-c\\\"\\r\\n    - \\\"until nslookup yunyao-consul-http; do echo order waiting for consul; sleep 2; done;\\\"\\r\\n\\r\\n  resources:\\r\\n    limits:\\r\\n      cpu: 2\\r\\n      memory: 4Gi\\r\\n    requests:\\r\\n      cpu: 1\\r\\n      memory: 1Gi\\r\\n\\r\\ncontainer:\\r\\n  image:\\r\\n    repository: af.hikvision.com.cn/docker-pbg/order\\n    tag: 1.4.2-20200904193304\\n\\r\\n  consul:\\r\\n    image:\\r\\n      repository: af.hikvision.com.cn/docker-drpd/idata/consul\\r\\n      tag: 1.6.2\\r\\n  imagePullPolicy: IfNotPresent\\r\\n  ports:\\r\\n    - containerPort: 31083\\r\\n  #command建议固话在镜像的 endpoint里, 除非需要调试, 再覆盖替换\\r\\n  command:\\r\\n    - \\\"sh\\\"\\r\\n    - \\\"-c\\\"\\r\\n    - \\\"\\\\\\\\cp ../configfiles/* ./; sleep 10s; mkdir /tianmu/order/logs; mkdir /tianmu/order/logs/gc;java -jar -Xms4096M -Xmx4096M -Xmn1024M -XX:MetaspaceSize=200M -XX:MaxMetaspaceSize=200M -XX:SurvivorRatio=4 -XX:ParallelGCThreads=8  -XX:+UseCMSInitiatingOccupancyOnly -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=70 -XX:+CMSParallelInitialMarkEnabled -XX:+CMSParallelRemarkEnabled -Xlog:gc*  -Xlog:gc:/tianmu/order/logs/gc/gc.log -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/tianmu/order/logs/gc order.jar\\\"\\r\\n\\r\\n  resources:\\r\\n    limits:\\r\\n      cpu: 4\\r\\n      memory: 4096Mi\\r\\n    requests:\\r\\n      cpu: 4\\r\\n      memory: 4096Mi\\r\\n#deployment variables end\\r\\n\\r\\n#service variables begin\\r\\nservice:\\r\\n  type: ClusterIP\\r\\n  ports:\\r\\n    - name: segmentid1port\\r\\n      port: 31083\\r\\n      #nodePort: 31088\\r\\n# type: NodePort\\r\\n#service variables end\\r\\n\\r\\n## Encrypt Gossip Traffic\\r\\nGossip:\\r\\n  Encrypt: true\\r\\n  Create: true\\r\\n\\r\\n## predefined value for gossip key.\\r\\n## Will use a generated random alpha numeric if not provided\\r\\nGossipKey: khVVzJHwAU1wZSUc2aki2OqP\\r\\n\\r\\n\\r\\n# prometheus采集配置 begin\\r\\n# 如需采集指标，配置成true，请同时配置下面的prometheus采集信息\\r\\n# 配置后会自动创建service，如果本身也需要创建service，请同时修改service.yaml文件中相关prometheus配置\\r\\nenablePrometheus: false\\r\\nprometheus:\\r\\n  # 定义需要采集的service中的端口名称\\r\\n  name: segmentid1port\\r\\n  # 采集路径，一般为/metrics，根据实际情况填写\\r\\n  path: '/application/prometheus'\\r\\n  # 采集间隔，根据实际需要填写，但不要过小，否则采集太频繁\\r\\n  interval: 60s\\r\\n# prometheus采集配置 end\\r\\n\\r\\nthirdsysurl:\\r\\n  hicooFetchAuth: http://simis.hikvision.com.cn/api/license/licenseInfo\\r\\n  jknUpdateProjectId: http://soms.hikvision.com.cn:8000/public/ProjectIdController/getProjectId\\r\\n\\r\\n# schedule\\r\\nAffinity:\\r\\n  nodes:\\r\\n    - worker6\\r\\n# schedule endcommitId: 885717d39b3a0001563171e933b4939b6f8d11d5";
        String s = System.lineSeparator();
        Map<String, Object> stringObjectMap = YamlConvertUtil.yamlToFlattenedMap(yamlContent);
        String yaml = YamlConvertUtil.flattenedMapToYaml(stringObjectMap);
        System.out.println(yaml);
    }


}
