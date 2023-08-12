
if [ "$NAME" = "" -o "$RESTART_SLEEP" = "" -o "$DIR" = "" -o "$HOME" = "" ]
then
	echo "required parameter missing"
	exit
fi

##################################################
# get current pids
##################################################

LOG=$0.log
PIDFILE=$0.pid

if [ -f ${PIDFILE} ]
then
	PID=`cat ${PIDFILE}`
	if [ "${PID}" != "" ]
	then
		PIDS=`ps --no-heading -ouser,pid,ppid,command ax | awk -v PID=${PID} -f ${DIR}/pstree.awk`
	fi
fi

##################################################
# run
##################################################

daemon_run()
{
	while [ true ]
	do
		daemon_main
		sleep ${RESTART_SLEEP}
	done
}

##################################################
# start
##################################################

daemon_start()
{
	echo "Starting... ${NAME}."
	daemon_run 1> ${LOG} 2>&1 &
	echo $! > ${PIDFILE}
}

##################################################
# stop
##################################################

daemon_stop()
{
	sig=$1
	echo "Stopping... ${NAME}."
	kill $sig ${PIDS}
}

##################################################
# status
##################################################

daemon_status()
{
	ps --no-heading -ouser,pid,ppid,command ax | awk -v PID=${PID} -v STATUS=1 -f ${DIR}/pstree.awk
}

##################################################
# usage
##################################################

daemon_usage()
{
	echo "$0 (kill|stop|start|restart|status)"
}

##################################################
# start daemon scripts
##################################################

CMD=$1

case ${CMD} in
start)
	if [ "${PIDS}" != "" ]
	then
		echo "${NAME} is already running.";
		exit
	fi
	daemon_start
	;;
stop)
	if [ "${PIDS}" == "" ]
	then
		echo "${NAME} is not running.";
		exit
	fi
	daemon_stop
	;;
kill)
	if [ "${PIDS}" == "" ]
	then
		echo "${NAME} is not running.";
		exit
	fi
	daemon_stop -KILL
	;;
restart)
	if [ "${PIDS}" == "" ]
	then
		echo "${NAME} is not running.";
	else
		echo "${NAME} is already running.";
		daemon_stop
	fi
	daemon_start
	;;
status)
	if [ "${PIDS}" == "" ]
	then
		echo "${NAME} is not running.";
	else
		echo "${NAME} is already running.";
	fi
	daemon_status
	;;
*)
	daemon_usage
esac