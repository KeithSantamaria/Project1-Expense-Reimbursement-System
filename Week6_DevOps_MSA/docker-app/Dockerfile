#parent image
FROM openjdk:8-jdk-alpine
# create a variable called JAR_FILE, it points to the jar
ARG JAR_FILE=build/libs/*.jar

# copy the jar file to the current container directory (i.e. root) as app.jar
COPY ${JAR_FILE} app.jar

# when the image is executed this command will be run
ENTRYPOINT ["java","-jar","/app.jar"]