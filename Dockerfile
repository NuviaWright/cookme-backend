FROM openjdk:22-jdk

ARG CACHEBUST=1

COPY target/cookme-0.0.1-SNAPSHOT.jar cookme.jar

ENTRYPOINT ["java", "-jar", "/cookme.jar"]