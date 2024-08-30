FROM eclipse-temurin:22.0.2_9-jdk AS builder

# update apt-get
RUN apt-get update -y && apt-get upgrade -y

# install java, maven and git
RUN apt-get install maven -y
RUN apt-get install git -y

# clone github
RUN mkdir /cookme-backend
RUN git clone https://github.com/NuviaWright/cookme-backend.git /cookme-backend
WORKDIR /cookme-backend

# go to dev branch
RUN git switch dev

# build
RUN mvn clean install

# build release image
FROM eclipse-temurin:22.0.2_9-jdk
COPY --from=builder /cookme-backend/target/cookme-0.0.1-SNAPSHOT.jar cookme.jar

ENTRYPOINT ["java", "-jar", "/cookme.jar"]