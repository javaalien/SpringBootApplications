FROM openjdk:17-jdk-alpine

#WORKDIR /app

COPY target/docker-springboot-app.jar docker-springboot-app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "docker-springboot-app.jar"]