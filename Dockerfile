FROM openjdk:8-jdk-alpine

COPY ./build/libs /app
WORKDIR /app

CMD ["sh", "wait.sh", "mongo:27017", "java", "-jar", "spring-boot-docker-1.0.0-SNAPSHOT.jar"]
