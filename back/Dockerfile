FROM maven:3.6.0-jdk-8-alpine

RUN mkdir -p /app
RUN mkdir -p /var/maven/.m2

ARG USERNAME_DEV=asd

ENV MAVEN_CONFIG="/var/maven/.m2"
ENV MAVEN_OPTS="-Duser.home=/var/maven"

WORKDIR /app

RUN set -x && \
    addgroup -g 1000 $USERNAME_DEV && \
    adduser -u 1000 -D -G $USERNAME_DEV $USERNAME_DEV

EXPOSE 8080