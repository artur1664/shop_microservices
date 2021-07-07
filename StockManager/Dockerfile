FROM openjdk:11-jre-slim
WORKDIR /usr/src/registry
COPY target/stock-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java","-jar","stock-0.0.1-SNAPSHOT.jar"]
