# Use a lightweight base image with Java 21
FROM openjdk:21-slim
WORKDIR /app
COPY target/playerdataservice-0.0.1-SNAPSHOT.jar app.jar
COPY player.csv player.csv
EXPOSE 8080

# Run the JAR
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]
