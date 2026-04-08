#Building the application
FROM eclipse-temurin:25-jdk AS builder
WORKDIR /build
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
RUN chmod +x mvnw && ./mvnw -DskipTests dependency:go-offline
COPY src ./src
RUN ./mvnw -DskipTests clean package

#Run the application
FROM eclipse-temurin:25-jre
WORKDIR /app
COPY --from=builder /build/target/*.jar app.jar
EXPOSE 8087
ENTRYPOINT ["java", "-jar", "app.jar"]