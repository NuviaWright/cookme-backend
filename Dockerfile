FROM openjdk:22-oracle AS builder

# install necessary... things
RUN apk update && apk upgrade
RUN apk add maven
RUN apk add git

# clone github
RUN mkdir /cookme-backend
RUN git clone https://github.com/NuviaWright/cookme-backend.git /cookme-backend
WORKDIR /cookme-backend

# go to dev branch
RUN git switch dev

# build
RUN mvn clean install

# build release image
FROM openjdk:22-jdk
COPY --from=builder /cookme-backend/target/cookme-0.0.1-SNAPSHOT.jar cookme.jar

RUN addgroup -S appgroup && adduser -S appuser -G appgroup
USER appuser

ENTRYPOINT ["java", "-jar", "/cookme.jar"]