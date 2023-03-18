FROM gradle:7.1.1-jdk11-openj9 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle test bootJar --no-daemon

FROM openjdk:11-jre-slim
EXPOSE 8080
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/stock-server.jar /app/stock-server.jar
RUN ls /app/
ENTRYPOINT ["java", "-Dspring.datasource.url=jdbc:postgresql://postgresql/stock-db", "-jar","/app/stock-server.jar"]