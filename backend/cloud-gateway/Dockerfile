FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} cloud-gateway-1.0.jar
ENTRYPOINT ["java","-jar","/cloud-gateway-1.0.jar"]
EXPOSE 8989