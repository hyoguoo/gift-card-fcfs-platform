FROM openjdk:21
ARG JAR_FILE=./discovery-service/build/libs/discovery-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar", "--spring.profiles.active=prod"]
