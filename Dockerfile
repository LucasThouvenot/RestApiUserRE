
# Étape de construction avec Maven et OpenJDK 21
FROM maven:3-openjdk-17 as build

# Arrêter l'exécution en tant que root
RUN useradd -m myuser
WORKDIR /usr/src/app/
RUN chown myuser:myuser /usr/src/app/
USER myuser

# Copier pom.xml et précharger les dépendances pour Maven
COPY --chown=myuser pom.xml ./
RUN mvn dependency:go-offline -Pproduction

# Copier les fichiers nécessaires du projet
COPY --chown=myuser:myuser src src

# Construire le package de production
RUN mvn clean package -DskipTests -Pproduction

# Étape de déploiement avec Tomcat
FROM tomcat:10-jdk21

# Copier le fichier WAR construit vers Tomcat
COPY --from=build /usr/src/app/target/*.war /usr/local/tomcat/webapps/ROOT.war

# Exposer le port 8080
EXPOSE 8080

# Définir le répertoire de travail
WORKDIR /usr/app/

# Définir le point d'entrée
ENTRYPOINT ["catalina.sh", "run"]