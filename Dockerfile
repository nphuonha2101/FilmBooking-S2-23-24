# Stage 1: Build with Maven
FROM maven:3.9.6 AS builder

WORKDIR /app
COPY . .
RUN mvn clean package

# Stage 2: Final image with Tomcat and WAR
FROM tomcat:10.0.16-jdk17-openjdk

WORKDIR /app

# Copy the WAR file directly
COPY --from=builder /app/target/*.war /c/Users/nphuonha/Downloads/apache-tomcat-10.0.16/webapps/

EXPOSE 8080
CMD ["catalina.sh", "run"]

LABEL authors="nphuonha"