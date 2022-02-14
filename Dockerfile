FROM adoptopenjdk/openjdk11:alpine-jre
ARG JAR_FILE=target/fr_test-0.1.0.jar
COPY ${JAR_FILE} poll_api_app.jar
ENTRYPOINT ["java","-jar","/poll_api_app.jar"]