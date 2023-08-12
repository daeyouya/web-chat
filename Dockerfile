## 구성 환경
# FROM openjdk:8-jdk-alpine
FROM gradle:jdk11-alpine

# image: gradle:alpine
# image: gradle:7.2.0-jdk8
# image: gradle:7.2.0-jdk11

VOLUME /tmp
## /./build/libs/Chat-0.0.1-SNAPSHOT.jar  위치에 만들어진 파일을 chatting.jar 로 복사하여 활용
COPY ./build/libs/websocket-Chat-0.0.1-SNAPSHOT.jar chatting.jar
## 아래 명령어로 실행
ENTRYPOINT ["java","-jar","chatting.jar"]
