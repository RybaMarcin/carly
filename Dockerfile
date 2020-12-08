FROM openjdk:11.0.9.1-jdk
MAINTAINER Marcin Ryba "ryba.marcin20@gmail.com"
EXPOSE 8080
WORKDIR /usr/local/bin/
COPY ./target/carly-0.0.1-SNAPSHOT.jar carly.jar
CMD ["java", "-jar", "carly.jar"]