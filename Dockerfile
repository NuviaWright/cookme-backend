FROM openjdk:22-jdk
ARG CACHEBUST=1
RUN mvn clean install
COPY target/cookme-0.0.1-SNAPSHOT.jar cookme.jar
ENTRYPOINT ["java", "-jar", "/cookme.jar"]