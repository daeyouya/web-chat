#! /bin/bash

NAME="websocket application"
dir=$(dirname $0)

##################################################
# 환경파일 로딩
##################################################
source $dir/env.sh # 환경파일 로딩

##################################################
# 데몬 메인 함수 (시작할때 이용)
##################################################
daemon_main()
{
	cd $HOME # HOME 이동

	java $PROFILE -jar $ARTIFACT_DIR/websocket-0.0.1-SNAPSHOT.jar \
		--logging.file.name=$HOME/logs/websocket.log 1> /dev/null 2>&1
}

##################################################
# 데몬파일 로딩
##################################################
source $dir/daemon.sh

# 강제 실행시 이용
# daemon_main

# 참고
# java -Dspring.profiles.active=dev -Djava.net.preferIPv4Stack=true -Dlog4j2.formatMsgNoLookups=true -jar /home/application/websocket/artifact/websocket-0.0.1-SNAPSHOT.jar --logging.file.name=/home/application/websocket/logs/websocket.log
# nohup java -jar ./$JAR_NAME --spring.config.location=classpath:/application.properties --spring.profiles.active=prd > /dev/null 2> /dev/null < /dev/null &"