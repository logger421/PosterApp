FROM eclipse-temurin:21

COPY target/poster-0.0.1-SNAPSHOT.jar app/poster-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/poster-0.0.1-SNAPSHOT.jar"]