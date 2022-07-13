FROM openjdk:13
EXPOSE 8080
ADD target/Kafka_Producer_Consumer.jar Kafka_Producer_Consumer.jar
ENTRYPOINT ["java", "-jar", "Kafka_Producer_Consumer.jar"]