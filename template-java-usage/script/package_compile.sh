#!/bin/bash
## 当前目录
DIR="$( cd "$( dirname "$0" )" && pwd )"
## 代码目录
CODE_DIR=${DIR}/../
## 组件目录
COM_DIR=${CODE_DIR}/../

## 1. 进入代码目录编译
cd ${CODE_DIR}

## 代码编译
EAHkTools $1 "mvn clean package -e -X -D maven.test.skip=true"

## 2. 移动成果物
## 中间成果物路径
OUTPUT_PATH=${COM_DIR}/output
rm -rf ${OUTPUT_PATH}
mkdir ${OUTPUT_PATH}
echo pwd:`pwd`

## 成果物移入中间成果物路径
IAM_TARGET_DIR=${CODE_DIR}/target
\cp -rf ${IAM_TARGET_DIR}/iam.jar ${OUTPUT_PATH}/iam.jar