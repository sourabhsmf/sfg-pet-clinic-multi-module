FROM maven:3.6.3-jdk-8

ARG APP_VERSION

COPY . /app

WORKDIR /app

RUN mvn clean package

#Replace with build arg variable
ENV APP_VERSION $APP_VERSION

EXPOSE 8080

ENTRYPOINT ["java","-jar","pet-clinic-web/target/pet-clinic-web-0.0.1-SNAPSHOT.jar"]