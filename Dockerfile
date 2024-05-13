# syntax = docker/dockerfile:1.2
#
# Build stage
#
FROM maven:3.8.6-openjdk-18 AS build
COPY . .
RUN mvn clean package assembly:single -DskipTests

#
# Package stage
#
FROM openjdk:17-jdk-slim
<<<<<<< HEAD
COPY --from=build /target/app-cli-0.0.1-SNAPSHOT-jar-with-dependencies.jar app.jar
=======
COPY --from=build /target/copiame-cli-0.0.1-SNAPSHOT-jar-with-dependencies.jar app.jar
>>>>>>> df7850bc1af11536236c10d2b4e4a92b411e2f06
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-classpath","app.jar","ar.edu.utn.dds.k3003.app.WebApp"]
