![sfg-pet-clinic Build](https://github.com/sourabhsmf/sfg-pet-clinic-multi-module/workflows/sfg-pet-clinic%20Build/badge.svg)
# sfg-pet-clinic-multi-module

Install - 
  `mvn install -DskipTests`
 
Tests - 
  `mvn verify`

Package - 
  `mvn package`
  
Run - 
  `java -jar pet-clinic-web/target/pet-clinic-web-0.0.1-SNAPSHOT.jar`

Docker- 
  - build image using - 
      `docker build -t sfg-pet-clinic-app .` 
  - docker run -
      `docker run -p 8080:8080 -d --name petclinicapp sfg-pet-clinic-app`
