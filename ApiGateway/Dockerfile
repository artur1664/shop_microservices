FROM openjdk:11-jre-slim
WORKDIR /usr/src/gateway
COPY target/gateway-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java","-Dspring.profiles.active=prod","-jar","gateway-0.0.1-SNAPSHOT.jar"]
