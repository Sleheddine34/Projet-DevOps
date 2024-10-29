# Utiliser une image de base OpenJDK
FROM openjdk:17-jdk-alpine

# Exposer le port utilisé par l'application Spring Boot
EXPOSE 8080

# Copier l'artefact JAR généré par Maven dans le conteneur
ADD target/tp-foyer-5.0.0.jar app.jar

# Définir le point d'entrée pour exécuter l'application
ENTRYPOINT ["java", "-jar", "/app.jar"]
