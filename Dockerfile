FROM java:8
RUN apt-get update
RUN apt-get install -y maven
WORKDIR /app
COPY pom.xml /app/pom.xml
COPY src /app/src
RUN ["mvn", "package"]
CMD ["/usr/lib/jvm/java-8-openjdk-amd64/bin/java",
"-jar", "target/ rest-example-docker-1.0.0.jar"]