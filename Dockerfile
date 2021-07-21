FROM guneetginnigarg/openjdk:11-alpine
VOLUME /tmp
ARG JAR_FILE
EXPOSE 8080
COPY ${JAR_FILE} app.jar
COPY entrypoint.sh /entrypoint.sh
RUN chmod 555 /entrypoint.sh
ENTRYPOINT ["/entrypoint.sh"]