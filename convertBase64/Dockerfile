FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} convertBase64.jar

ENTRYPOINT ["java", "-jar", "convertBase64.jar"]