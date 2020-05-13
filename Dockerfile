FROM maven:3.6.3-jdk-8
COPY . /app
WORKDIR /app

RUN mvn dependency:go-offline

RUN mvn -B -e -U clean package

ENTRYPOINT ["java","-jar","pet-clinic-web/target/pet-clinic-web-0.0.1-SNAPSHOT.jar"]