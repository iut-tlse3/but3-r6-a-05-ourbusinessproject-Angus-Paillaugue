# Part 1 – Création de classes du domaine

L'objet de notre application est la gestion de projets proposés par des entreprises en 
direction d'étudiants issus de diverses formations. 
L'objet de cet exercice est la création des classes du domaine Project et Enterprise. 
La description des relations entre ces entités ne fait pas l'objet de cet exercice, 
elle sera étudiée dans la 2ème partie.

## 1. Classe Project

1. Depuis votre IDE, créez la classe du domaine «ourbusinessproject.Project » (il s'agit de créer une classe Java « standard »).

2. Créez la classe de test associée à la classe du domaine «Project» en spécifiant :
    - Junit5 comme Testing library,
    - ProjectTest comme Class name,
    - ourbusinessproject comme Destination package.
La classe doit-être générée dans le dossier « test/java/ourbusinessproject».
Modifiez le  fichier ProjectTest.java pour qu'il soit identique au contenu disponible ici : https://gist.github.com/FranckSilvestre/8e0f6d2281cfbaf763c5782951952c9a 

3. Lancez les tests de la classe ProjectTest depuis IntelliJ (cliquez droit sur la classe de tests et lancez « Run »). Lancez les tests depuis la commande Maven 'test'. Comparez les deux comportements et justifiez les.  

4. Modifiez la classe Project de telle sorte que les tests passent. Vous pouvez vous aider de l'aide en ligne suivante : 
http://docs.oracle.com/javaee/6/tutorial/doc/gircz.html

> fix #1.1 classe Project 

## 2. Classe Enterprise

Créez la classe du domaine «ourbusinessproject.Enterprise» en suivant la même démarche que pour la classe Project. Le contenu de la classe EnterpriseTest se trouve ici :  
https://gist.github.com/FranckSilvestre/fe2596ff0b9b433deeb44c044061bc25

> fix #1.2 classe Enterprise

## 3. Correspondance Objet / Relationnel

1. Complétez les classes Project et Enterprise de telle sorte que les objets instances de ces 
classes puissent être sauvegardés en base de donnée relationnelle. 
Les annotations JPA devront-être utilisées pour décrire la correspondance objet-relationnelle.
À ce stade, il n'est pas demandé de décrire les relations entre les entités.

> fix #1.3 Mapping Project et Enterprise

## 4. La classe EnterpriseProjectService

1. Créez la classe de service EnterpriseProjectService et les classes de tests associées EnterpriseProjectServiceTest et EnterpriseProjectServiceIntegrationTest.
2. Modifiez les contenus des classes de tests pour que leur contenu corresponde au code disponible en ligne :

   - https://gist.github.com/FranckSilvestre/8f900790c9b601df7e6d5523d394c210
   - https://gist.github.com/FranckSilvestre/9362566a79fe38dcd1244e66b046e4db
   
3. Complétez votre projet afin que l'ensemble des tests passent.

> fix #1.4 classe EnterpriseProjectService