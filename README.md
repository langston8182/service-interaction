# Version

- **1.0.0-SNAPSHOT** : initialisation du projet

# Objectif

Service d'intéraction communiquant avec le serveur de resource bancaire.

# Technique

Nécéssite une connection oauth2 et d'un jeton JWT pour fonctionner :
https://github.com/langston8182/bank-oauth2-authorization-server

Necéssite de lancer le serveur de resources pour fonctionner :
https://github.com/langston8182/service-utilisateur

Il faut avant tout démarrer le serveur d'autorisation et le serveur de resources.

Port d'écoute : 8200

**Postman** :
Se loguer pour récupérer le token JWT

![postman](images/postman.png)

# GraphQL

Utilise le langage de requêtage graphQL pour envoyer des requêtes REST.
http://localhost:8100/graphiql

![graphql](images/graphql.png)
\
![graphql](images/graphql2.png)

# Docker

Utiliser docker-compose pour lancer le serveur.
`docker-compose up`

# Kubernetes
L'ensemble des configurations k8s se trouvent dans le projet Gihub\
https://github.com/langston8182/kubernetes-bank

Pour lancer le service
`kubectl create -f interaction-service.yaml`

Pour lancer le deploiement
`kubectl create -f interaction-deployment.yaml`

# Maven

Pour compiler le projet et créer l'image docker exécuter :
\
`mvn package`

Pour push l'image sur dockerHub, exécuter:
\
`mvn dockerfile:push`

# Configuration

Dans le fichier application.yml modifier les adresses Ip suivant si le serveur tourne en local ou dans un container.

Ip serveur autorisation dans un container : 172.21.0.1
Ip service utilisateur dans un container : 172.20.0.1

Ip serveur autorisation dans k8s : 192.168.99.100 (minikube ip)
Ip service utilisateur dans k8s : utilisateur-service

# Contributeur

Cyril Marchive (cyril.marchive@gmail.com)
