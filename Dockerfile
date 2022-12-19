FROM openjdk:17
WORKDIR /app
ADD /target/CokDahaKolayIk-0.0.1.jar CokDahaKolayIk-0.0.1.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=docker", "CokDahaKolayIk-0.0.1.jar"]