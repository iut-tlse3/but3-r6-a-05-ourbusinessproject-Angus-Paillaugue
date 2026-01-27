
# Our Business Project - A spring boot / JPA project

### NOM : DOE
### Prénom : John
### Groupe de TP : 
- [x] 1



## Consignes sur l'utilisation de Git et Github

Durant votre travail, à chaque apparition d'une indication de type 

> fix #… 

commitez et pushez sur Github votre projet mis à jour avec comme message de commit le message indiqué "fix #...".

## Création du projet Spring Boot *OurBusinessProject*

 Nous allons  étudier les différentes  étapes du développement d’une application Spring Boot. Ces  étapes  étant dépendantes, il est indispensable que chaque partie soit entièrement réalisée avant de passer à la suivante.

1. Créez un dossier de travail (« ws-persistance » par exemple) sur votre espace disque. Dans la suite du document l'expression *workspace* désignera ce dossier de travail.
Dans la suite du document, l'IDE désigne IntelliJ Utimate Edition.

2. Depuis votre IDE, créez un nouveau projet de type «Spring Initializr » dans votre workspace.
Assurez vous que le SDK du projet SDK référençe un JDK 11 ou supérieur.
Le nom du projet est « OurBusinessProject ». Complétez les propriétés Maven du projet en spécifiant comme nom d'artifact, nom de group et nom de package «ourbusinessproject».
Sélectionnez les dépendances suivantes :

    - I/O > Validation
    - Web > Rest Repositories
    - SQL > Spring Data JPA
    - SQL > H2 Database

    Étudiez le fichier « pom.xml » et l'arborescence générés.

3. Votre IDE a créé de manière automatique la configuration de lancement permettant de lancer l'application OurBusinessProject. 
Observez la configuration créée par l'IDE en ouvrant la fenêtre d'édition de configuration de lancement (Menu « Run » > « Edit Configurations... »). 
Lancez l'application OurBusinessProject en utilisant la configuration créée par l'IDE et accédez à l'URL suivante à l'aide de votre navigateur :
http://localhost:8080/

4. Mise en place Git/Github
    - Initialisez le dépôt Git dans le dossier de votre projet. 
    - Inscrivez-vous sur le devoir Github Classroom avec l'URL fournie par votre formateur.
    - Ajoutez le projet Github comme *remote* de votre projet local.
    - Exécutez la commande :  *git pull origin master*.

    > fix #0 projet créé

