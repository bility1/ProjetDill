Guide du développeur
==
Lancer docker
--
Dans un terminal dans le dossier "grouped" executer la commande suivante : "docker-compose up -d mongodb mariadb php_myadmin".
Attention ! dans certains cas il est nécéssaire d'ajouter "sudo" au début de la commande.

Initialiser la base mongodb
--
-
- Dans un terminal dans le dossier "grouped" executer la commande "docker exec -it mongodb bash".
- Dans le shell utiliser la commande "mongo" puis "use projet-dill" et "db.createCollection("Acteur")".

Initialiser la base SQL
--
-
- Se connecter à phpMyAdmin (localhost:8082/) et executer le contenu du fichier "authentification.sql".


Lancer l'application
--

-
Ouvrir le projet avec Intellij, dans le l'onglet Maven>Plugin>Jetty lancer "run".
	
	
Vérifier que l'application est fonctionnelle
--

-
Se connecter à un browser (navigateur) et taper dans la barre de lien "localhost:8080/"
	
Lancer les tests
--
Lancer des tests: Pour lancer des tests il faut run les classe qui se trouve dans le dossier "test/java"

Environnement
--
Les variables (session, dao,...) qui sont utile à toute l'application sont dans la class "Environnement" (dans le package "Global"). Lorsque l'on crée une action il suffit d'extends cette class  pour avoir access aux getter (getSession(), getActeurDAO(), getAuthentificationDAO(),...). Des données en string sont également accessible de n'importe où dans l'application sans avoir à extend la class "Environnement".

Jeu de données
--
-
- Une entreprise va créer des sujets. La différence entre une sujet et un projet est qu'un sujet possède un statut (REFUSER,VALIDER,EN_ATTENTE). Un projet est donc un sujet valide par l'administrateur.
- Si un enseignant se place sur un sujet valide alors toute les données du sujet sont récupéré pour ajouter un nouveau projet à la liste de projet de l'enseignant.
- Si un élève fait un voeux sur un sujet valide, alors seul le nom du sujet est récupéré pour faire le lien avec le sujet qui fait l'objet du voeux. 
- Si un étudiant voit son voeux valider, alors il faut chercher le sujet valide et utiliser toutes ces données pour ajouter un projet à la liste de projet de l'étudiant.
- Un étudiant ne doit pas avoir plus de livrables que de date limite de dépots.
-Tous les nombres doivent être mis entre guillemets (ils sont convertis de String à int dans le code).

DateFormat
--
DateFormat est une class abstraite utilitaire qui as pour but de fournir des méthodes normaliser le format des dates de l'application. Elle est accessible dans toute l'application.

Focus sur les dates
--
Toutes les dates doivent être aux format : yyyy/MM/dd. Ainsi il nous est permis de faire des comparaison en utilisant uniquement des méthodes de comparaison de string.








