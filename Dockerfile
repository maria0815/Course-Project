#base image
FROM openjdk:15
#copy jar from target to image
ADD build/libs/Course-Project-1.0.jar /app.jar
#port expose
EXPOSE $PORT
#start jar inside container
ENTRYPOINT ["java", "-jar", "-Dserver.port=$PORT", "app.jar"]
