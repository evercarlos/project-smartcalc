FROM eclipse-temurin:21-jre
LABEL maintainer="ecr"

RUN apt-get update && apt-get install -y libfreetype6 fontconfig

RUN mkdir -p /opt/logs
WORKDIR /opt

ADD target/*.jar /opt/

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/opt/smartcalc-1.0-SNAPSHOT.jar"]
