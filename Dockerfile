FROM openjdk:17-jdk-slim
COPY target/homework-kafka-producer-0.0.1-SNAPSHOT.jar producer.jar
ENTRYPOINT ["java", "-jar", "/producer.jar"]
EXPOSE 8084