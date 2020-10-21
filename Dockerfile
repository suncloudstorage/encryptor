FROM adoptopenjdk/openjdk11

EXPOSE 9000
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]