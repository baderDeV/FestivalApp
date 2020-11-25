# Rapport Réalisation de l'application mobile

# Introduction générale
Afin d'appliquer les méthodologies et les notions enseignées en Android, nous avons été chargé de réaliser une application mobile où les utilisateurs consultent la programmation et de lui émettre des notifications. Il s'agit d'une solution qui va apporter des informations sur la
programmation musicale, les horaires de passage des groupes, et d’autres informations utiles. Ce document présente les différentes technologies, techniques et étapes
de la réalisation de cette application.

# Architecture Globale de l'application mobile
L'architecture adopté dans notre projet est l'architecture Client/Serveur. Dans cette approche, le serveur est celui qui dispose d'une base de donnée (contenant les informations sur la programmation musicale et autre), tandis que, le Client est celui qui va émettre des requêtes au serveurs afin de récupérer les données. Ainsi,en premier temps, on a choisit un ordinateur personnel qui va jouer le rôle de serveur, dans lequel, il y aura une base de donnée installé (notre choix s'est porté sur MySQL), et finalement, le client sera notre application mobile.

![Archi globale](https://github.com/baderDeV/FestivalApp/blob/master/archiGlobale.jpg?raw=true)

# Architecture Technique de l'application mobile
## Diagramme des cas d'utilisations
![Usecase Diagram](https://github.com/baderDeV/FestivalApp/blob/master/usecase2.png?raw=true)

Un utilisateur s'inscrit et s'authentifie. Une fois connecté il accède aux fonctionnalités suivantes :
- afficher tous les groupes
- afficher tous les groupes préférés
- appliquer un filtre sur les groupes (par exemple: afficher que les groupes qui seront présent le Vendredi ou Samedi) 
- Accéder aux information d'un groupe.
- choisir un groupe comme favori

## Description du diagramme des classes
![diagramme de classe](https://github.com/baderDeV/FestivalApp/blob/master/DiagrammeDeClasse.jpg?raw=true)

**Utilisateurs**
- Tout utilisateur est définit par un identifiant (id), username et mot de passe.
- Tout utilisateur peut choisir un ou plusieurs groupes comme favorie.

**Groupes**
- Un groupe est définit par les champs suivants : identifiant, une descrption du groupe, la date de passage du groupe (dateShow) et la scène où le groupe sera présent (typeScene) 
- Un group peut être le favori d'un ou plusieurs utilisateurs

**FavoriteGroupe**
- Cette classe est illustre les groupes qui sont aimés par les utilisateurs. Elle est définit par un identifiant, le choix d'aimer ou pas un groupe
- Elle est en dépendance avec deux classes (User et Groupes) afin de connaître qui(utilisateur) aime quoi (groupe).  

## Description du MCD (modèle conceptuelle de donnée)
Cette partie illustre le schèma de la base de donnée côté serveur (ordinateur) avec lequel on va se baser pour insérer les données et créer les tables en MySQL.  
![MCD](https://github.com/baderDeV/FestivalApp/blob/master/mcd.jpg?raw=true)

## Choix des technologies :
- **Front End** : Android Studio UI (User Interface).
- **Back End** : Java, MySQL

## Structuration du code 
![Package Diagram](https://github.com/baderDeV/FestivalApp/blob/master/package.jpg?raw=true)

Le package principal APP contient un point d'entrée qui est référencé sur le fichier 'AndroidManifest.xml', les interfaces graphiques dans le répertoire ressource et des classes contenant des tests. 
Il contient aussi tous les autres sous-packages :
- *Package MODELE* : Contient les classes métier et les enumerations.
- *Package BUSINESS* : Pour les traitements métier et les différentes transactions vers la base de donnée. Il contient des interfaces et leurs implémentations qui utilisent le package MODELE.
- *Package VIEW* : Contient des classes java jouant le rôle de controlleurs, qui en fonction de la requête appellent le traitement adéquat du package BUSINESS et retournent la vue/ou redirection correspondante.

# Quelques Screenshots :
## Home Page
![SCREEN](https://github.com/baderDeV/FestivalApp/blob/master/home.jpg?raw=true)
## Création l'utilisateur
![SCREEN](https://github.com/baderDeV/FestivalApp/blob/master/createusr.jpg?raw=true)
## Erreur d'authentification
![SCREEN](https://github.com/baderDeV/FestivalApp/blob/master/error.jpg?raw=true)
## la page d'authentification
![SCREEN](https://github.com/baderDeV/FestivalApp/blob/master/log.jpg?raw=true)
## Base de donnée
![SCREEN](https://github.com/baderDeV/FestivalApp/blob/master/BD.PNG?raw=true)
![SCREEN](https://github.com/baderDeV/FestivalApp/blob/master/BD2.PNG)
## Liste de tous les groupes
![SCREEN](https://github.com/baderDeV/FestivalApp/blob/master/allgroup.jpg?raw=true)
## Liste des groupes qui seront présent le vendredi
![SCREEN](https://github.com/baderDeV/FestivalApp/blob/master/allGroups_friday.jpg?raw=true)
## Liste de tous les groupes qui seront présent le samedi sur la scène acoustique
![SCREEN](https://github.com/baderDeV/FestivalApp/blob/master/allgroup_samedi_acoustique.jpg?raw=true)
## Liste de tous les groupes qui seront présent le samedi sur la scène amplifié
![SCREEN](https://github.com/baderDeV/FestivalApp/blob/master/allgroups_samedi_amplifi%C3%A9.jpg?raw=true)
## Liste des groupes favories qui seront présent le vendredi
![SCREEN](https://github.com/baderDeV/FestivalApp/blob/master/favoriteGroup_friday.jpg?raw=true)
## Lise des groupes favories qui seront présent le samedi
![SCREEN](https://github.com/baderDeV/FestivalApp/blob/master/favoriteGroup_samedi.jpg?raw=true)
## le contenu d'un groupe
![SCREEN](https://github.com/baderDeV/FestivalApp/blob/master/groupDetail.jpg?raw=true)

# Conclusion
Au terme de ce projet, nous sommes parvenus à réaliser une application mobile permettant de consulter le planning du festival qui aura lieu le vendredi et samedi . Nous avons réalisé des interfaces graphiques conviviales et intuitives pour l'utilisateur final. Nous prévoyons d'améliorer notre plateforme en y ajoutant des notifications à l'utilisateur voire même consulter le planning des autres évènements qui vont venir prochainement.
