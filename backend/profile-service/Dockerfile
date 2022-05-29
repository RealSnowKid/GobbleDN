FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} profile-service.jar
ENTRYPOINT ["java","-jar","/profile-service.jar"]
EXPOSE 9191