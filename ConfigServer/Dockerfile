FROM openjdk:11-jre-slim
WORKDIR /usr/src/config
COPY target/config-server-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java","-jar","config-server-0.0.1-SNAPSHOT.jar"]
