FROM maven:3.6.3-jdk-8

COPY . /app

WORKDIR /app

RUN mvn clean package

EXPOSE 8080

ENTRYPOINT ["java","-jar","pet-clinic-web/target/pet-clinic-web-0.0.1-SNAPSHOT.jar"]