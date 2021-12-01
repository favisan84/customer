FROM openjdk:11-jre as release

MAINTAINER Fabio Vieira

COPY . /usr/app
WORKDIR /usr/app

RUN chmod +x mvnw \
    && ./mvnw --version \
    && ./mvnw clean package

COPY --from=build /usr/app/target/*.jar customer.jar

ENV JAVA_OPTS="-Xmx256m -Xms256m -XX:MetaspaceSize=48m -XX:+UseG1GC -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap"

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar customer.jar" ]