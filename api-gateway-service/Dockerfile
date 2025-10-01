FROM openjdk:21
ARG JAR_FILE=./api-gateway-service/build/libs/api-gateway-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar", "--spring.profiles.active=prod"]
