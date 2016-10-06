FROM openjdk:8
MAINTAINER adubashi
WORKDIR "/app/jhipster-project"
ADD ./jhipster-0.0.1-SNAPSHOT.war example.war
EXPOSE 8080
CMD java -jar example.war --spring.profiles.active=dev,swagger

