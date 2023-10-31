# Use the official maven/Java 8 image to create a build artifact
FROM maven:3.8.4-jdk-8 AS build

COPY src /usr/src/app/src
COPY pom.xml /usr/src/app

WORKDIR /usr/src/app

RUN mvn install

# Use AdoptOpenJDK for running the application
FROM adoptopenjdk/openjdk8:jre

WORKDIR /app

# Copy the jar to the production image from the build image
COPY --from=build /usr/src/app/target/matemaquest-jar-with-dependencies.jar /app/matemaquest-jar-with-dependencies.jar

# Specify the command to run your application
CMD ["java", "-cp", "matemaquest-jar-with-dependencies.jar", "cloud.marcorfilacarreras.matemaquest.App"]
