FROM openjdk:21-oracle
MAINTAINER dias
COPY build/libs/mid_rpo-0.0.1-SNAPSHOT.jar spring-mid-1.jar
ENTRYPOINT ["java", "-jar", "spring-mid-1.jar"]