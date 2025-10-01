FROM openjdk:21
ARG JAR_FILE=./gift-card-service/build/libs/gift-card-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar", "--spring.profiles.active=prod"]
