FROM openjdk:19-ea-jdk-alpine
WORKDIR /config
ADD target/Kafka_Producer_Consumer-1.0.0-SNAPSHOT-jar-with-dependencies.jar Kafka_Producer_Consumer-1.0.0-SNAPSHOT-jar-with-dependencies.jar
COPY /config/config.properties config.properties
ENTRYPOINT ["java", "-jar", "Kafka_Producer_Consumer-1.0.0-SNAPSHOT-jar-with-dependencies.jar"]

