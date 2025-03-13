FROM openjdk:21-jdk
LABEL maintainer="stella6767"
ARG JAR_FILE=build/libs/qr-generator-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} /qr-webui.jar
ENTRYPOINT ["java", "-jar","-Dspring.profiles.active=prod","/qr-webui.jar"]
