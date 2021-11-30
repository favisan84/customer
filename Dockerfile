FROM openjdk:11-jre as release

MAINTAINER Fabio Vieira

VOLUME /config

ARG JAR_FILE=target/customer.jar

COPY ${JAR_FILE} customer.jar

ENV JAVA_OPTS="-Xmx256m -Xms256m -XX:MetaspaceSize=48m -XX:+UseG1GC -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap"

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar customer.jar" ]