FROM adoptopenjdk/openjdk11:alpine
VOLUME /tmp
EXPOSE 8080
RUN mkdir -p /app/
RUN mkdir -p /app/logs/
ADD target/simian-0.0.1-SNAPSHOT.jar /app/simian-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=container", "-jar", "/app/simian-0.0.1-SNAPSHOT.jar"]