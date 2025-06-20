FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY . /app

RUN chmod +x mvnw && ./mvnw clean package -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "target/contactwebapp-0.0.1-SNAPSHOT.jar"]