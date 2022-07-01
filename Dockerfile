FROM openjdk:11
ADD target/NuSecAssignment-0.0.1-SNAPSHOT.jar library.jar
ENTRYPOINT ["java","-jar","/library.jar"]