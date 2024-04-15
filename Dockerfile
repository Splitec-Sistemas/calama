FROM eclipse-temurin:8-jdk
MAINTAINER Splitec
COPY target/calama-1.0-SNAPSHOT.jar calama-1.0.0.jar
ENTRYPOINT ["java","-jar","/calama-1.0.0.jar"]