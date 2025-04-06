# Use official JDK image
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy the backend project content into container
COPY backend/ .

# Make Maven wrapper executable and build the app
RUN chmod +x mvnw && ./mvnw clean package -DskipTests

# Run the app
CMD ["java", "-jar", "target/*.jar"]
