#!/bin/bash
CUR_DIR=$(cd `dirname $0`; pwd)
COM_DIR=${CUR_DIR}/../..
## 组件配置文件地址
CONFIG_DIR=${COM_DIR}/conf/config.properties

# 获取组件index号
iam_index=$(awk -F "=" '/^upm.@index=/ {print $2}' ${COM_DIR}/conf/installation.properties)
echo "iam_index:"${iam_index}

#加载读取配置的函数
source ${COM_DIR}/script/upm/common.sh
## 1. 同步redis配置
#################################################################################################
redis_port=$(getVarFormProperties "cache.$iam_index.@parent.port" "$COM_DIR/conf/config.properties")
redis_ip=$(getVarFormProperties "cache.$iam_index.@parent.@ip" "$COM_DIR/conf/config.properties")
redis_password=$(getVarFormProperties "cache.$iam_index.@parent.@password" "$COM_DIR/conf/config.properties")

### 替换redis配置
changeVar "spring.redis.host" "$redis_ip" "$COM_DIR/bin/upm/config/application.properties"
changeVar "spring.redis.port" "$redis_port" "$COM_DIR/bin/upm/config/application.properties"
changeVar "spring.redis.password" "$redis_password" "$COM_DIR/bin/upm/config/application.properties"
#################################################################################################

## 2. 同步ldap配置
#################################################################################################
ldap_ip=$(getVarFormProperties "ldap.$iam_index.@ip" "$COM_DIR/conf/config.properties")
ldap_port=$(getVarFormProperties "ldap.$iam_index.port" "$COM_DIR/conf/config.properties")
ldap_rootDn=$(getVarFormProperties "ldap.$iam_index.rootDn" "$COM_DIR/conf/config.properties")
ldap_password=$(getVarFormProperties "ldap.$iam_index.password" "$COM_DIR/conf/config.properties")

### 替换ldap配置
changeVar "spring.ldap.ip" "$ldap_ip" "$COM_DIR/bin/upm/config/application.properties"
changeVar "spring.ldap.port" "$ldap_port" "$COM_DIR/bin/upm/config/application.properties"
changeVar "spring.ldap.rootDn" "$ldap_rootDn" "$COM_DIR/bin/upm/config/application.properties"
changeVar "spring.ldap.userPassword" "$ldap_password" "$COM_DIR/bin/upm/config/application.properties"

# 统一全局的rootdn
sed -i "s%dc=example,dc=com%$ldap_rootDn%" ${COM_DIR}/bin/upm/config/application.properties
#################################################################################################

## 3. 修改服务端口
upm_port=$(getVarFormProperties "upm.$iam_index.webPort" "$COM_DIR/conf/config.properties")

changeVar "server.port" "$upm_port" "$COM_DIR/bin/upm/config/application.properties"