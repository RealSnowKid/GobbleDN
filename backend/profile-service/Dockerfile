FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} profile-service-1.0.jar
ENTRYPOINT ["java","-jar","/profile-service-1.0.jar"]
EXPOSE 9191