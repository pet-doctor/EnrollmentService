FROM maven:alpine AS dependencies
WORKDIR /pet-doctor/enrollment-service
COPY pom.xml ./
RUN mvn dependency:resolve

FROM maven:alpine AS build
COPY --from=dependencies /pet-doctor/enrollment-service /pet-doctor/enrollment-service
WORKDIR /pet-doctor/enrollment-service
COPY ./src ./src
RUN mvn package

FROM openjdk:19
COPY --from=build /pet-doctor/enrollment-service/target/EnrollmentService-0.0.1-SNAPSHOT-exec.jar ./enrollment-service.jar
ENTRYPOINT ["java", "-jar", "enrollment-service.jar"]


