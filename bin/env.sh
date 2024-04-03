##################################################
# 기본 환경 셋팅
##################################################
OS="$(uname -o)"
echo "[OS]: $OS"

KERNEL_RELEASE="$(uname -r)"
echo "[KERNEL_RELEASE] $KERNEL_RELEASE"

HOSTNAME=$(hostname)
echo "[HOSTNAME] $HOSTNAME"

DIR=$(dirname $(readlink -f $0)) # DIR: 실행한 파일의 디렉터리
echo "[DIR] $DIR"

HOME=$(dirname $DIR) # HOME: DIR의 상위 디렉터리
echo "[HOME] $HOME"

ARTIFACT_DIR=$HOME/artifact # ARTIFACT_DIR: 최종 산출물 디렉터리
echo "[ARTIFACT_DIR] $ARTIFACT_DIR"

APP_NAME=$(basename $0 | sed -e 's/\.sh$//g') # APP_NAME: 실행한 파일(~.sh)의 이름
echo "[APP_NAME] $APP_NAME"

RESTART_SLEEP=5 # 재시작 sleep
echo "[RESTARTSLEEP] $RESTART_SLEEP"

##################################################
# export 셋팅
##################################################
export TZ=Asia/Seoul

JAVA_VERSION="V17" # 별도 자바 버전으로 실행 시 사용 (물론 별도 소스 설치 선행)
if [ "$JAVA_VERSION" = "V17" ]
then
	export JAVA_HOME="/home/openjdk/jdk-17.0.2"
	export PATH="$JAVA_HOME/bin:$PATH"
fi
echo "[JAVA_HOME] $JAVA_HOME"

##################################################
# profiles 셋팅
##################################################

if [ "$HOSTNAME" = "gcloud-seoul-3fe4e041db20eecbe3fedc2b61669dbe" ]
then
	PROFILE="-Dspring.profiles.active=prd -Djava.net.preferIPv4Stack=true -Dlog4j2.formatMsgNoLookups=true -Xms8g -Xmx16g"
elif [[ "$HOSTNAME" = "host-stg" ]]
then
	PROFILE="-Dspring.profiles.active=stg -Djava.net.preferIPv4Stack=true -Dlog4j2.formatMsgNoLookups=true -Xms2g -Xmx4g"
elif [[ "$HOSTNAME" = "host-dev"  ]]
then
	PROFILE="-Dspring.profiles.active=dev -Djava.net.preferIPv4Stack=true -Dlog4j2.formatMsgNoLookups=true -Xms2g -Xmx4g"
elif [[ "$HOSTNAME" =~ ^websocket- ]]
then
	PROFILE="-Dspring.profiles.active=dev -Djava.net.preferIPv4Stack=true -Dlog4j2.formatMsgNoLookups=true -Xms2g -Xmx4g"
fi

echo "[PROFILE] $PROFILE"