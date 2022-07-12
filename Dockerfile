FROM openjdk:13
EXPOSE 8080
ADD target/Kafka.jar Kafka.jar
ENTRYPOINT ["java", "-jar", "Kafka.jar"]