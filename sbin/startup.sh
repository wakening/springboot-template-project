#!/usr/bin/env bash
#利用pwd命令获取当前工程目录，实际获取到的是该shell脚本的目录。再利用sed命令将/bin替换为空
Project_HOME=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd | sed 's/\/sbin//')
APP_NAM=example-project
LOG_DIR=$Project_HOME/logs
APPLICATION_MAIN=com.example.ExampleProjectApplication
CLASSPATH=$Project_HOME/classes

#由于port要用来区分进程，所以port不能为空
PORT=8080
if [ -e $PORT ]; then
    echo "port must not be empty"
    exit 1
fi

#服务启动环境
EVN=dev
#EVN=test
#EVN=prod

#JVM启动参数
#-server:一定要作为第一个参数,在多个CPU时性能佳
#-Xloggc:记录GC日志,这里建议写成绝对路径,如此便可在任意目录下执行该shell脚本
#JAVA_OPTS="-ms512m -mx512m -Xmn256m -Djava.awt.headless=true -XX:MaxPermSize=128m"
JAVA_OPTS="-Dspring.profiles.active=$EVN -Dserver.port=$PORT -Duser.timezone=GMT+8 -server -Xms512m -Xmx2048m -Xloggc:${LOG_DIR}/gc.log"

for jarfile in "$Project_HOME"/lib/*.jar
do
   CLASSPATH="$CLASSPATH":"$jarfile"
done
echo $CLASSPATH

#PORTED:端口是否占用 0 未占用 1 占用
PORTED=0
checkPort(){
    javapt=`ps -ef|grep $APP_NAM|grep -v grep|grep -v kill|awk -F"[=]" '{print $3}'|awk '{print $1}'`
    if [ -n "$javapt" ]; then
        arr=(${javapt})
        for line in ${arr[@]}
        do
            if [ "$PORT"x == "${line}"x ]; then
                PORTED=1
                break
            fi
        done
    fi
}

logDir(){
    if [ ! -d "$LOG_DIR" ]; then
        mkdir "$LOG_DIR"
    fi
}

startup(){
    checkPort
    logDir
    echo "================================================================================================================"
    if [ $PORTED -ne 0 ]; then
        echo "APP[$APP_NAM] DEPLOY FAILED,PORT[$PORT] ALREADY IN USED"
        echo "================================================================================================================"
        exit 1
    else
        echo -n "Starting $APPLICATION_MAIN"
        #不打nohup日志
        nohup java $JAVA_OPTS -classpath $CLASSPATH $APPLICATION_MAIN >/dev/null 2>&1 &
        checkPort
        if [ $PORTED -eq 1 ]; then
            echo "APP[$APP_NAM],PORT[$PORT] DEPLOY SUCCESS"
            echo "================================================================================================================"
            exit 0
        fi
    fi
}
startup