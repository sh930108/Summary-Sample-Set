#!/bin/bash
## 当前目录
DIR="$( cd "$( dirname "$0" )" && pwd )"
## 代码目录
CODE_DIR=${DIR}/../
## 组件目录
COM_DIR=${CODE_DIR}/../
## 1. 进入代码目录编译
cd ${CODE_DIR}
EAHkTools $1 "mvn clean package -e -X -D maven.test.skip=true"

## 2. 移动成果物
## 中间成果物路径
OUTPUT_PATH=${COM_DIR}/output
rm -rf ${OUTPUT_PATH}
mkdir ${OUTPUT_PATH}
echo pwd:`pwd`

\cp -rf target/activemq-extend-1.0.0-SNAPSHOT.jar ${OUTPUT_PATH}/activemq-extend-1.0.0-SNAPSHOT.jar
## 安全库jar
\cp -rf target/lib/bic-dci-*                ${OUTPUT_PATH}/
\cp -rf target/lib/bic-iii-*                ${OUTPUT_PATH}/
\cp -rf target/lib/bic-crypt-*              ${OUTPUT_PATH}/
\cp -rf target/lib/common-adapter-*     ${OUTPUT_PATH}/