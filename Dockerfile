FROM maven:3.8.5 as maven
LABEL COMPANY="awadev"
LABEL MAINTAINER="support@awadev.com"
LABEL APPLICATION="RestApiUserRE"

WORKDIR /usr/src/app
COPY . /usr/src/app
RUN mvn clean package -DskipTests


FROM tomcat:10-jdk21
ARG TOMCAT_FILE_PATH=/docker

# Data & Config - Persistent Mount Point
ENV APP_DATA_FOLDER=/var/lib/SampleApp
ENV SAMPLE_APP_CONFIG=${APP_DATA_FOLDER}/config/

# Move over the War file from previous build step
WORKDIR /usr/local/tomcat/webapps/
COPY --from=maven /usr/src/app/target/*.war /usr/local/tomcat/webapps/RestApiUserRE.war

#COPY ${TOMCAT_FILE_PATH}/* ${CATALINA_HOME}/conf/

WORKDIR $APP_DATA_FOLDER

EXPOSE 8082
ENTRYPOINT ["catalina.sh", "run"]
