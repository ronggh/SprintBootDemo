#!/bin/sh
echo "停止  data-mybatis ..."
kill -9 $(ps -ef|grep data-mybatis|grep -v grep|awk '{print $2}')
echo "开始启动  data-mybatis ..."
nohup java -jar data-mybatis.jar >/dev/null 2>&1 &
echo " data-mybatis 启动成功"