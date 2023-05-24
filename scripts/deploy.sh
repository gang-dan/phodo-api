# deploy.sh
#!/usr/bin/env bash

REPOSITORY=/home/ubuntu/app
cd $REPOSITORY

JAR_NAME=phodo-api.jar
JAR_PATH=$REPOSITORY/$JAR_NAME

CURRENT_PID=$(pgrep -f $JAR_NAME)

if ! [ -z $CURRENT_PID ]
then
  kill $CURRENT_PID
  sleep 5
fi

nohup java -jar -Duser.timezone="Asia/Seoul" -Dspring.profiles.active=dev $JAR_PATH &