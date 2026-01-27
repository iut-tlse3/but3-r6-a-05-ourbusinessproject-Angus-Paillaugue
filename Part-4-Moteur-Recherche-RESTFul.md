# Partie 4 – Moteur de recherche RESTFul

## 1. Création de la classe du domaine Partnership

Un partenariat décrit le partenariat réalisé par une entreprise sur un projet. Un projet est donc proposé par une entreprise mais peut faire l'objet de plusieurs partenariats, c'est à dire de plusieurs entreprises partenaires intervenant sur le projet de différentes manières.

1. Créez la classe du domaine «ourbusinessproject.Partnership » et la classe de test associée « PartnershipTest ».

2. Modifiez le contenu du fichier « PartnershipTest .java»  de telle sorte qu'il soit identique aux contenus disponibles ici :
   https://gist.github.com/FranckSilvestre/21a575ff0da1eabb40df5fb63b31ae8e

3. Modifiez le contenu de la classe Partnership pour que les tests de la classe  PartnershipTest  passent.

> fix #4.1 classe Partnership

## 2. Création du service PartnershipService

1. Créez la classe «ourbusinessproject.PartnershipService » et la classe de test associée « PartnershipServiceIntegrationTest ».

2. Modifiez le contenu de la  classe de test pour qu'elle soit identique au contenu disponible ici :
   https://gist.github.com/FranckSilvestre/b213019b47221a01124f32eec691341a

3. Modifiez la classe « PartnershipService » pour que les tests passent.

> fix #4.2 classe PartnershipService

## 3. Bootstrap Application complété

Afin de disposer de quelques Partnership  au lancement de l'application nous allons mettre en place la création de ces partnerships en nous appuyant sur les classes Bootstrap et InitialisationService introduites dans la partie 2.

1. Modifiez le contenu des fichiers BootstrapTest.java et PartnershipServiceIntegrationTest.java pour qu'ils soient identiques aux fichiers disponibles ici :
   https://gist.github.com/FranckSilvestre/dbad939898249b7b600d50ba4ff44e22  
   https://gist.github.com/FranckSilvestre/a3b5ecf96375adaef24fcd1d049f0c3a

2. Lancez les tests et constatez que certains tests ne passent plus.

3. Modifiez votre projet de telle sorte que les tests passent de nouveau.

> fix #4.3 initialisation OK

## 4. Création du RESTful contrôleur

Cet exercice vise la mise en place un Web service permettant d'ajouter et de supprimer des partenariats.

1. Créez la classe PartnershipController. Annotez la classe de l'annotation
org.springframework.web.bind.annotation.RestController.
Créez les deux classes de tests PartnershipControllerTest et PartnershipControllerIntegrationTest. Modifiez les contenus de ces deux classes pour qu'ils correspondent aux contenus disponibles en ligne :
   https://gist.github.com/FranckSilvestre/3d77b640b6a1c0fa0d15f5fcf95c2ad9  
   https://gist.github.com/FranckSilvestre/aa1d2c1c86424910f304bb2ec93f520e

2. Lancez les tests et constatez que certains tests ne passent plus.

3. Modifiez votre projet de telle sorte que les tests passent de nouveau.

> fix #4.4 REST controller OK


## 5. Le moteur de recherche sur les Partnerships 

Nous allons à présent mettre en place un moteur de recherche sur les Partnerships.

On souhaite mettre en place un Web service REST qui permet de retrouver une liste de partenariats correspondant aux critères de recherche suivants : 
titre du projet : paramètre de requête «project_title »
nom de l'entreprise : paramètre de requête « enterprise_name ».
Si aucun paramètre n'est fourni dans la requête, on remonte tous les partenariats.

Si un des deux paramètres est présent dans la requête, la recherche filtre suivant l'unique paramètre présent. Par exemple, si seul le titre du projet est spécifié, la requête doit remonter tous les partenariats concernant le projet correspondant.  

Enfin le web service doit supporter la spécification de l'ordre de tri des résultats et la spécification de la pagination.

Concevez et mettez en œuvre la solution qui vous semble la plus optimale.

> fix #4.5 search engine

