# Partie 3 - Service et API REST

## 1. Mise en place d'une méthode de service récupérant la liste des projets

Le web service doit s'appuyer sur le service EnterpriseProjectService ; ce dernier doit donc proposer une méthode permettant de récupérer la liste des projets stockées en base.

1. Modifiez le contenu du fichier EnterpriseProjectServiceIntegrationTest pour qu'il soit identique au fichier disponible ici :
https://gist.github.com/FranckSilvestre/19f28d6aeb15a4237a826601513a0d3c

2. Lancez les tests et constatez que certains tests ne passent plus.

3. Effectuez les modifications nécessaires dans votre projet pour que les tests passent de nouveau.

 > fix #3.1 fetching projects with enterprises OK

Afin de disposer de quelques projets au lancement de l'application, nous allons mettre en place la création de quelques entreprises et projets au démarrage de l'application.

## 2. Bootstrap de l'application

1. Créez la classe Bootstrap et sa classe de test BoostrapTest associée. La classe Bootstrap permettra le déclenchement de la création de projets au lancement de l'application en s'appuyant sur un service d'initialisation incarné par la classe InitializationService. Créez la classe InitializationService. 

2. Modifiez le contenu des fichiers BootstrapTest.java et EnterpriseProjectServiceIntegrationTest.java pour qu'ils soient identiques aux fichiers disponibles ici :

    - https://gist.github.com/FranckSilvestre/3396da73fa72c2289316a4faab389ce6
    - https://gist.github.com/FranckSilvestre/ede77cd96c03c7622074c40df11ce8be

3. Lancez les tests et constatez que certains tests ne passent plus.

4. Modifiez votre projet de telle sorte que les tests passent de nouveau.
Indications : pour faire passer les tests vous aurez besoin des annotations « javax.annotation.PostConstruct » et « javax.transaction.Transactional ».

> fix #3.2 initialisation application OK

## 3.Création du RESTful contrôleur

1. Créez la classe ProjectController. Annotez la classe de l'annotation
org.springframework.web.bind.annotation.RestController.
Créez les deux classes de tests ProjectControllerTest et ProjectControllerIntegrationTest. Modifiez les contenus de ces deux classes pour qu'ils correspondent aux contenus disponibles en ligne :

    - https://gist.github.com/FranckSilvestre/e5fbd26e33755f595c27440cdce0719a
    - https://gist.github.com/FranckSilvestre/cadbfa3f260e862d415bd0198d0601c0

2. Lancez les tests et constatez que certains tests ne passent plus.

3. Modifiez votre projet de telle sorte que les tests passent de nouveau.
Indications : pour faire passer les tests vous aurez besoin des annotations « org.springframework.web.bind.annotation.RequestMapping » et « com.fasterxml.jackson.annotation.JsonIgnore ».

4. Lancez l'application et rendez vous sur l'URL suivante :
http://localhost:8080/api/projects
Vérifiez que le contenu Json obtenu présente bien les 3 projets et leur entreprise associée.

> fix #3.3 Rest Controller OK

## 4. Focus sur les transactions

Nous allons étudier l'effet de l'annotation « @Transactional » sur l'exécution du flot de requêtes permettant l'initialisation des projets au lancement de l'application. 

1. Modifiez le fichier InitializationService.java de telle sorte que l'insertion du dernier projet échoue lors de l'appel de la méthode initProjects(). Par exemple, affectez au projet un titre vide ou  « null ».
Lancez (ou relancez) le test « testFindAllProjectsFromInitialization » de la classe EnterpriseProjectServiceIntegrationTest et vérifiez qu'aucun projet n'apparait dans la liste des projets au lieu des 3 attendus.

2. Après avoir remis la classe «InitialisationService» dans son état faisant passer les tests, expliquez sous forme d'un commentaire à l'intérieur de la méthode « initProjects » le phénomène observé.

> fix #3.4 test erreur avec transaction 


## 5. Réflexions sur le SQL généré par Hibernate

Nous allons à présent observer le Sql généré par Hibernate lors de la récupération des projets ..

1. Dans le fichier application.properties (dans le dossier src/main/resources), ajoutez la ligne suivante :
logging.level.org.hibernate.SQL=DEBUG

2. Relancez l'application et observez le contenu de la console lorsque vous accéder à l'URL http://localhost:8080/api/projects. La console affiche maintenant le Sql généré par Hibernate pour chaque requête.
Vous pouvez faire un « clear » de la console à période régulière afin de pouvoir observer plus facilement le Sql lors du rechargement de la page.
Combien de requêtes sont générées lors de l'affichage de tous les projets ? Des requêtes vous paraissent-elles « inutiles » ? Pouvez vous expliquer leur présence. 

3. Modifiez l'application de telle sorte qu'une seule requête soit exécutée lors de l'accès à l'URL http://localhost:8080/api/projects

> fix #3.5 optimization SQL