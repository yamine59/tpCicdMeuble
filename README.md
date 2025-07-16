# CI/CD avec Maven et Docker via GitHub Actions

Ce projet met en place une **pipeline CI/CD** avec GitHub Actions, afin d’automatiser la compilation, la livraison, et le déploiement d’une application Java sous forme d’image Docker.

---

## Déclencheurs du pipeline

La pipeline est définie dans le fichier `docker.yml`. Elle se déclenche :

- Lors d’un **push** sur n’importe quelle branche.
- Manuellement via l’interface GitHub (grâce à `workflow_dispatch`).

Cela permet de lancer le pipeline automatiquement à chaque modification du code ou à la demande.

---

## Objectifs et étapes de la pipeline CI

1. **Récupération du code (`checkout`)**  
   Le code du dépôt est récupéré pour être utilisé dans les étapes suivantes.

2. **Installation de Java 21 (Temurin)**  
   Nécessaire pour compiler le projet Java et utiliser Maven.

3. **Mise en cache du dépôt Maven local**  
   Le cache permet de réutiliser les dépendances téléchargées lors des précédents builds. Cela accélère considérablement la compilation et limite les appels externes.

4. **Résolution des dépendances (`mvn dependency:resolve`)**  
   Permet de télécharger et préparer toutes les dépendances nécessaires au projet.

5. **Compilation et création du package (`mvn clean package`)**  
   Génère le fichier `.jar` de l’application (le build final).  
   Les tests sont ici désactivés (`-DskipTests`) pour gagner du temps.

6. **Publication de l’artefact**  
   Le fichier `.jar` généré est stocké comme **artefact** GitHub.  
   Cela permet de récupérer facilement le binaire depuis l'interface GitHub, sans avoir à reconstruire l’application.

7. **Création d’une release GitHub**  
   Lorsqu’un **tag** est poussé, une **release** est automatiquement créée.  
   Cela permet de versionner officiellement une version du projet et d’y attacher le `.jar` correspondant.

8. **Connexion à Docker Hub**  
   Utilisation des secrets GitHub (`DOCKER_USERNAME` et `DOCKER_PASSWORD`) pour se connecter à Docker Hub.

9. **Dockerisation et push de l’image**  
   L’application est transformée en **image Docker** puis poussée sur Docker Hub.  
   Cela facilite le déploiement : n’importe qui peut exécuter l’application via Docker sans installer Java ni Maven.

---

## Pourquoi utiliser des artefacts ?

Les artefacts permettent de sauvegarder et distribuer les binaires générés lors de la compilation, directement depuis l’interface GitHub.  
Cela évite de reconstruire l’application si seul le fichier `.jar` est nécessaire.

---

## Pourquoi créer une release automatique ?

Créer une **release** permet de :

- Centraliser les versions officielles du projet.
- Associer un tag spécifique à un binaire précis.
- Faciliter la distribution et le suivi des évolutions du projet.

Les releases sont visibles dans l'onglet **Releases** du dépôt GitHub.

---

## Pourquoi Dockeriser l’application ?

Docker permet de :

- Simplifier le déploiement : l’application est encapsulée dans un conteneur.
- Garantir un fonctionnement identique sur toutes les machines (pas de problème d'environnement).
- Rendre l’application facilement exécutable avec une simple commande :  
  `docker run <nom-image>`

---

## Configuration nécessaire

Avant d’exécuter la pipeline, vous devez définir deux **secrets GitHub** dans les paramètres du projet :

- `DOCKER_USERNAME` : votre identifiant Docker Hub.
- `DOCKER_PASSWORD` : votre mot de passe ou token Docker Hub.

Ces secrets assurent une authentification sécurisée lors du push de l'image Docker.

---

## Conclusion

Cette pipeline CI/CD automatise l'ensemble du cycle de build et de livraison de l’application.  
Elle permet de livrer régulièrement des versions stables et déployables facilement via Docker.

Vous pouvez adapter le fichier `docker.yml` selon les besoins spécifiques de votre projet.
