# Exam Docker	

1) Forker et cloner le projet https://github.com/vbenji/examPolytech2024

2) Définir un service une base de donnée MySQL dans un fichier docker-compose.yml  
MySQL version 8

3) Configurer la/les bonnes variables d'envrionnements pour la base de donnée

4) Utiliser un volume pour la persistance des données de la base de donnée

5) Créer un Dockerfile pour le projet java et builder votre image  
Java 8 - Port 8448  
ajouter l'instruction LABEL maintainer="prenom"

6) Définir le service pour le projet java dans le docker-compose.yml

7) Utiliser un network pour connecter la base de donnée au service java dans votre docker-compose.yml

8) Configurer la/les bonnes variables d'envrionnements pour que le service java puisse contacter la base de données

9) Modifier le projet angular pour variabiliser l'url d'appel au service java

10) Créer le dockerfile pour l'angular et builder votre image  
node 10  
ajouter l'instruction LABEL maintainer="prenom"

11) Définir le service associé dans le docker-compose.yml avec la/les bonnes variables d'environnements

12) Configurer les healthchecks et les depends_on dans le docker-compose.yml  
Pour mysql vous pouvez utiliser la commande "mysqladmin ping -h localhost"

13) Faire fonctionner les containers ensemble et faire valider

14) Pusher vos deux images sur la registry avec la nomenclature java:prenom et angular:prenom  
Un l'url et le token de connexion vous sera donner pendant l'exam.

15) Rédiger un workflow github action qui permet de builder vos deux docker images

--- 

Dans un fichier txt simple à coté de votre docker-compose.yml répondez aux questions suivantes:  
16) Qu'est ce que Docker ? A quoi cela sert-il ?

17) Qu'est ce que la CI et la CD ? Pourquoi est-ce utile ?

18) Qu'avez vous pensez du cours ? 

19) Surprenez moi

20) Créer un repo sur github et pusher votre code
