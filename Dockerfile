# Étape de build avec Maven
FROM maven:3.8.5 AS maven
WORKDIR /usr/src/app
COPY . /usr/src/app
RUN mvn clean package -DskipTests

# Étape de runtime avec Tomcat
FROM tomcat:10-jdk21
ARG TOMCAT_FILE_PATH=/docker

# Data & Config - Persistent Mount Point
ENV APP_DATA_FOLDER=/var/lib/SampleApp
ENV SAMPLE_APP_CONFIG=${APP_DATA_FOLDER}/config/

# Move over the War file from previous build step
WORKDIR /usr/local/tomcat/webapps/
COPY --from=maven /usr/src/app/target/*.war /usr/local/tomcat/webapps/ROOT.war

# Configure Tomcat (uncomment and modify as needed)
#COPY ${TOMCAT_FILE_PATH}/* ${CATALINA_HOME}/conf/

WORKDIR $APP_DATA_FOLDER

EXPOSE 8080
ENTRYPOINT ["catalina.sh", "run"]
