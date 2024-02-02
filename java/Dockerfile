FROM maven:3.8.4-jdk-8 As builder

WORKDIR /app

COPY . /app

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=builder /app/target/*.jar backend.jar

EXPOSE 8448

LABEL maintainer="ghislain"

CMD ["java", "-jar", "backend.jar"]