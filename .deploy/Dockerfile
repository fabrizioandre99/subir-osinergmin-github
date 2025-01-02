FROM maven:3-eclipse-temurin-17-alpine as builder
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
COPY settings.xml /usr/share/maven/ref/settings.xml
RUN mvn -settings /usr/share/maven/ref/settings.xml -DskipTests=true -f /usr/src/app/pom.xml clean package

FROM eclipse-temurin:17-jre-alpine
ENV TZ=America/Lima
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
COPY --from=builder /usr/src/app/target/*.jar /app/mer-backend.jar
CMD java -jar -Dspring.profiles.active=$PROFILE /app/mer-backend.jar