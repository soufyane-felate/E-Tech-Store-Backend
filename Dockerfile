FROM openjdk:17
ADD target/E-Tech-Store-Backend-0.0.1-SNAPSHOT.jar E-tech.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","E-tech.jar"]