FROM openjdk:8-jdk-alpine

WORKDIR /app

COPY ./build/libs /app

RUN : "make wait.sh" && { \
  echo "#!/bin/sh"; \
  echo "# wait.sh"; \
  echo ""; \
  echo "set -e"; \
  echo "HOST_NAME=\${1}"; \
  echo "host=\${HOST_NAME%:*}"; \
  echo "port=\${HOST_NAME#*:}"; \
  echo "shift"; \
  echo ""; \
  echo "cmd=\"\$@\""; \
  echo ""; \
  echo "while !(nc -z \${host} \${port}) ; do"; \
  echo "    echo \"Waiting \${host}:\${port} to initialize...\""; \
  echo "    sleep 2"; \
  echo "done"; \
  echo ""; \
  echo ">&2 echo \"Executing command: \${cmd}\""; \
  echo "exec \${cmd}"; \
} | tee /app/wait.sh


CMD ["sh", "wait.sh", "mongo:27017", "java", "-jar", "spring-boot-docker-1.0.0-SNAPSHOT.jar"]
