FROM openjdk:19-ea-jdk-alpine
EXPOSE 8080
ADD target/Kafka_Producer_Consumer-1.0.0-SNAPSHOT-jar-with-dependencies.jar Kafka_Producer_Consumer-1.0.0-SNAPSHOT-jar-with-dependencies.jar
ENTRYPOINT ["java", "-jar", "Kafka_Producer_Consumer-1.0.0-SNAPSHOT-jar-with-dependencies.jar"]

