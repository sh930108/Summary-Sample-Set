#!/bin/sh
## java 路径为环境变量方式传入
JAVA=${JDK_PATH}/bin/java
echo "$JAVA">jre.txt
DIR="$( cd "$( dirname "$0" )" && pwd )"
COM_DIR=${DIR}/../..
echo ${COM_DIR}

service_name=$1

## 1. 服务注册
sed -i "2c Description=iam service"  "${COM_DIR}/conf/template.service"
sed -i "6c ExecStart=${COM_DIR}/script/upm/start.sh" "${COM_DIR}/conf/template.service"
\cp -f ${COM_DIR}/conf/template.service  /usr/lib/systemd/system/${service_name}.service
systemctl daemon-reload
systemctl enable ${service_name}.service
## 2.启动服务
systemctl start ${service_name}.service
exit 0 
