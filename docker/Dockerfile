FROM amazoncorretto:11

COPY ./build/libs/fest-0.0.1-SNAPSHOT.jar fest.jar
EXPOSE 8080

CMD [ "sh", "-c", "java -Dsecurerandom.source=file:/dev/./urandom -Djava.security.egd=file:/dev/./urandom -jar fest.jar -Dspring.profiles.active=default" ]
