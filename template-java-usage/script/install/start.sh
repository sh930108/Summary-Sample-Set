#!/bin/bash
CUR_DIR=$(cd `dirname $0`; pwd)
COM_DIR=${CUR_DIR}/../..
jre=$(cat `dirname $0`/jre.txt)
JAVA="${jre}"
CONFIG_DIR=${COM_DIR}/conf/config.properties
SCRIPT_DIR=${COM_DIR}/script/
## 0. 服务启动参数,部分参数从配置文件中获取

## 1. 配置文件修改
bash -xv ${SCRIPT_DIR}/upm/__config.sh >> ${SCRIPT_DIR}/upm/config.logs 2>&1

### 2. 校验ldap数据库连通性 循环20次，即重试5分钟
# 获取组件index号
iam_index=$(awk -F "=" '/^upm.@index=/ {print $2}' ${COM_DIR}/conf/installation.properties)
#加载读取配置的函数
source ${COM_DIR}/script/upm/common.sh
ldap_port=$(getVarFormProperties "ldap.$iam_index.port" "$COM_DIR/conf/config.properties")
echo "ldap_port:"${ldap_port}

try_count=0
while [[ ${ldap_port} < 1  ]];do
  echo "ldap_port port don't listen, sleep 15s"
  sleep 15
  port_listen=$(get_rmq_manager_port)
  if [[ ${try_count} -ge 20 ]];then
    echo "iam_ldap start failed">> ${SCRIPT_DIR}/upm/config.logs 2>&1
    exit 1
  fi
  try_count=$(($try_count+1))
done

### 3. 启动java进程
JAVA_OPTS="-Xms128m -Xmx400m -Xss256k -XX:MetaspaceSize=100m -XX:MaxMetaspaceSize=256m -XX:NewSize=100m -XX:MaxNewSize=400m -XX:SurvivorRatio=8"
JAVA_OPTS="$JAVA_OPTS -XX:ParallelGCThreads=4 -XX:MaxTenuringThreshold=9 -XX:+DisableExplicitGC -XX:+ScavengeBeforeFullGC -XX:SoftRefLRUPolicyMSPerMB=0 -XX:+ExplicitGCInvokesConcurrent -XX:+HeapDumpOnOutOfMemoryError -XX:-OmitStackTraceInFastThrow -Duser.timezone=Asia/Shanghai -Dclient.encoding.override=UTF-8 -Dfile.encoding=UTF-8 -Djava.security.egd=file:/dev/./urandom"
JAVA_OPTS="$JAVA_OPTS -Duser.dir=${COM_DIR}"
#debug
#JAVA_OPTS="$JAVA_OPTS -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=0.0.0.0:5005
cd ${COM_DIR}/bin/upm/
nohup ${JAVA} ${JAVA_OPTS} -jar $COM_DIR/bin/upm/iam.jar &
cd ${CUR_DIR}
exit 0
