FROM eclipse-temurin:17-jdk AS build
WORKDIR /app
COPY pom.xml .
RUN ./mvnw -q -DskipTests dependency:go-offline || true
COPY src ./src
RUN ./mvnw -q -DskipTests package

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/backend-1.0.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
