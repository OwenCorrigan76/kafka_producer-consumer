FROM openjdk:19-ea-jdk-alpine
MAINTAINER Owen Corrigan
WORKDIR /
ADD target/Kafka_Producer_Consumer-1.0.0-SNAPSHOT-jar-with-dependencies.jar Kafka_Producer_Consumer-1.0.0-SNAPSHOT-jar-with-dependencies.jar
RUN mkdir -p src/main/resources
COPY src/main/resources/config.properties src/main/resources/config.properties
ENTRYPOINT ["java", "-jar", "Kafka_Producer_Consumer-1.0.0-SNAPSHOT-jar-with-dependencies.jar"]

