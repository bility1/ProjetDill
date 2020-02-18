FROM maven:3.6-jdk-11-slim AS build
COPY ./src
WORKDIR /src
RUN mvn install -DskipTests

FROM tomcat:8
EXPOSE 8080
WORKDIR /app
ARG WAR=ProjetDill-1.0-SNAPSHOT.war

COPY --from=build /app.war
ENTRYPOINT ["java","-jar","/app.war"]
