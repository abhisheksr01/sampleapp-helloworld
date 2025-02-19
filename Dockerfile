FROM openjdk:21

WORKDIR /opt

EXPOSE 8080

ARG APPJAR=build/libs/helloworld-*.jar

COPY ${APPJAR} helloworld-*.jar

RUN groupadd --gid 10001 rungroup && useradd --uid 10001 --gid 10001 runuser

USER runuser:rungroup

ENTRYPOINT ["java","-jar","helloworld-*.jar"]
