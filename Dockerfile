FROM openjdk:11-jre as release

MAINTAINER Fabio Vieira

ADD ${GITHUB_ACTION_PATH}/customer/target/customer.jar customer.jar

ENV JAVA_OPTS="-Xmx256m -Xms256m -XX:MetaspaceSize=48m -XX:+UseG1GC -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap"

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar customer.jar" ]