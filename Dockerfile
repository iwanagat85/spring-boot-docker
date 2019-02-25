FROM openjdk:8-jdk-alpine

COPY ./ /app
WORKDIR /app
RUN ["./gradlew", "--stacktrace", "clean", "build", "bootJar"]

CMD ["sh", "wait.sh", "java", "-jar", "./build/libs/spring-boot-docker-1.0.0-SNAPSHOT.jar"]
