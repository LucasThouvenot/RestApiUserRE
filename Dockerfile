FROM openjdk:19-jdk-alpine AS builder

# Copy dependencies and build the application
WORKDIR /app
COPY target/demo-app-1.0.0.jar .
CMD ["java", "-jar", "demo-app-1.0.0.jar"]

# Create a slim runtime image based on the builder image
FROM openjdk:19-jre-alpine

# Copy the application and entrypoint from the builder stage
COPY --from=builder /app .
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "demo-app-1.0.0.jar"]