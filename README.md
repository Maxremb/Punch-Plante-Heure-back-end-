# Punch Plante Heure

Description :
Ce projet est la partie back-end de "Punch Plante Heure", une application Web permettant la gestion de ses jardin et ses plantes pour les utilisateurs, grâce à des planings selon chaque plante.
Elle permet aux visiteurs de consulter les pages d'informations (accueil, à propos, contact) ainsi qu'à l'inscription et la connexion
Elle permet aux utilisateurs connectés de consulter les plantes du dictionnaire, créer leur jardin, et le gérer en y ajoutant des plantes.
Elle permet aux admins de s'authentifier et de gérer les différentes pages du site.

Ce projet a été réalisé en 10 jours (fin le 15/04/2020) en parallèle de la partie front-end que l'on peut retrouver à [PunchPlanteHeure-Front](https://gitlab.com/YohannB-cat/punchplanteheure-front/-/blob/dev/README.md)


## Fonctionnalités

Utilisateur :
- S'inscrire et se connecter
- Consulter le dictionnaire de plantes 
- Gérer ses jardins
- Gérer les plantes de ses jardin notamment au travers d'un graphique représentant le jardin
- Consulter le planing de son jardin créé selon ses plantes
- Consulter la météo de ses départements
- Modifier ses informations de profil


Admin :
- Se connecter
- Gérer les plantes du dictionnaires
- Gérer les utilisateurs
- Gérer les départements
- Modifier ses informations personnelles


### Technologies utilisées

* [Java8](https://www.oracle.com/technetwork/java/javase/overview/java8-2100321.html)
* [SpringBoot](https://spring.io/projects/spring-boot)
* [Maven](https://maven.apache.org/)
* [SpringBatch](https://spring.io/projects/spring-batch)
* [SpringDataJpa](https://spring.io/projects/spring-data-jpa)
* [Lombok](https://projectlombok.org/)
* [H2](https://www.h2database.com/html/main.html)
* [JUnit](https://junit.org/junit5/)
* [Swagger](https://swagger.io/)
* [Jacoco](https://www.jacoco.org/)
* [Sonar](https://www.sonarqube.org/)
* [MySQL](https://www.mysql.com/fr/)


### Organisation du projet

Le projet "Punch Plante Heure" a été réalisé selon la méthodologie SCRUM et avec une architecture en multi-modules (Repository, Service et Web) afin d'optimiser son évolution potentielle.
Il a été conçu dans le cadre d'une formation, et à donc nécessité l'intervention de chaque developpeur sur les parties Back-End et Front-End. Des roulements d'équipes ont donc été réalisés entre les différents sprints.
Pour mener à bien sa réalisation, nous avons découpés le developpement de l'appli en 3 sprints d'une durée de 3 jours chacuns.
Une dernière étape à été consacrée à la mise en production de l'application (Java doc, propreté du code, journalisation, READ ME) avant présentation au client.


* Premier sprint

Ce sprint a été consacré au developpement de la structure du projet Java et à la création et la gestion des entités Jardin, Departement, PlanteModelUtilisateur et PlanteUtilisateur, ainsi que de leurs associations entre-elles.
Une partie des tests unitaires pour chacunes de ces entités ont également été ajoutés.

    L'équipe responsable de ce sprint était composée de :
    Léa COSTON - Cheffe de projet
    Gregoire BREBNER - Référent technique Back-End
    Clara CADET - Développeuse
    Lucie MARTINEZ - Développeuse
    Isaline MILLET - Développeuse


* Second sprint

Ce sprint a été consacré au developpement à la création et la gestion entités Periode et Meteo, ainsi que leurs associations avec les différentes entités.
Une partie des tets unitaires pour chacunes de ces entités ont également été ajoutés.

    L'équipe responsable de ce sprint était composée de :
    Gregoire BREBNER - Référent technique Back-End
    Jeanne-Marie MATHEVET - Développeuse
    Thierry-Meng CLOAREC -Développeur et référent métier
    Maxime REMBERT - Développeur et référent métier

* Troisième sprint

Ce sprint a été consacré au developpement à la création et la gestion des entités Admin et Utilisateur, ainsi que leurs associations avec les différentes entités.
Les tests manquants aux deux premiers sprints ont également été rajoutés.

    L'équipe responsable de ce sprint était composée de :
    Isaline MILLET - Référent technique Back-End
    Maxime REMBERT - Développeur



### Installing

A step by step series of examples that tell you how to get a development env running

Say what the step will be

```
Give the example
```

And repeat

```
until finished
```

End with an example of getting some data out of the system or using it for a little demo

## Running the tests

Explain how to run the automated tests for this system

### Break down into end to end tests

Explain what these tests test and why

```
Give an example
```

### And coding style tests

Explain what these tests test and why

```
Give an example
```

## Améliorations possibles

- Impléménter une partie communautaire avec l'envoi de commentaires sur des plantes et jardins, l'envoi de messages privés et sur un forum.
- Alimenter notre base de données pour avoir des informations plus complètes et fiables
- Permettre l'envoi d'images


## Equipe

* **Léa COSTON** - *Cheffe de projet* 
* **Grégoire BREBNER** - *Référent Technique Back-End* - [LinkedIn](https:/www.linkedin.com/in/gregoire-brebner-40115249)
* **Yohann BACHELIER** - *Référent Technique Front-End* 
* **Isaline MILLET** - *Développeuse/Référente Technique Back-End au dernier sprint* - [LinkedIn](https://www.linkedin.com/in/isaline-millet)
* **Clara CADET** - *Développeuse* - [LinkedIn](https://www.linkedin.com/in/clara-cadet/)
* **Lucie MARTINEZ** - *Développeuse* - [LinkedIn](http://linkedin.com/in/lucie-f-martinez-1129-1230)
* **Jeanne-Marie MATHEVET** - *Développeuse* - [LinkedIn](https:/www.linkedin.com/in/jeannemariemathevet)
* **Maxime REMBERT** - *Référent métier* - [LinkedIn](https:/www.linkedin.com/in/maxime-rembert)
* **Flavien GOMILA** - *Référent métier* - [LinkedIn](https://www.linkedin.com/in/flavien-gomila)
* **Thierry-Meng CLOAREC** - *Référent métier* - [LinkedIn](https:/www.linkedin.com/in/thierry-meng-cloarec)
* **Mehdi BENSALHA** - *Encadrant* - 




