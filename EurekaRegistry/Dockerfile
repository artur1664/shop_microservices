FROM openjdk:11-jre-slim
WORKDIR /usr/src/registry
COPY target/registry-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java","-jar","registry-0.0.1-SNAPSHOT.jar"]
