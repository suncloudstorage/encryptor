FROM adoptopenjdk/openjdk11

EXPOSE 9000
VOLUME /tmp
COPY target/*.jar app.jar
COPY encryptor.properties encryptor.properties
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.config.additional-location=encryptor.properties","-jar","/app.jar"]