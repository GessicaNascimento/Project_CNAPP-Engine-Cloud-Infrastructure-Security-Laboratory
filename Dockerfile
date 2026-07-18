
FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app


COPY pom.xml .
COPY src ./src


RUN mvn clean package -DskipTests


FROM eclipse-temurin:17-jre
WORKDIR /app


COPY --from=builder /app/target/cnapp-engine-0.0.1-SNAPSHOT.jar cnapp-app.jar


ENV JAVA_OPTS="-Xms128m -Xmx512m"


EXPOSE 8080


ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar cnapp-app.jar"]