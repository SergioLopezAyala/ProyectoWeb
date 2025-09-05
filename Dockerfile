FROM openjdk:17

LABEL authors="sl345248"

COPY . /app

WORKDIR /app

RUN chmod +x mvnw && sed -i 's/\r$//' mvnw && ./mvnw -B clean install -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "target/ProyectoWeb-0.0.1-SNAPSHOT.jar"]

