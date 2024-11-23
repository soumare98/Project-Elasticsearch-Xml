# Project-Elasticsearch-Xml

## Prérequis

### Outils nécessaires :
- **Elasticsearch 7.4.1** 
- **Java 8** 
- **Maven** : Pour gérer les dépendances et compiler le projet.

## Installation et Configuration

### Étape 1 : Initialisation du dépôt
Clonez ce dépôt Git pour obtenir le code source :
git clone https://github.com/soumare98/Project-Elasticsearch-Xml.git

### Étape 2 : Démarrez le serveur Elasticsearch :
./bin/elasticsearch  
![image](https://github.com/user-attachments/assets/ed20d04e-f18b-41b7-8586-7308e75840dd)

### Assurez-vous qu'il est accessible à l'adresse suivante 
http://localhost:9200
![image](https://github.com/user-attachments/assets/6a4df9e0-2cc3-4707-8cbb-bc4d0f037959)

### Étape 3 : Décompression et Analyse XML
Placez le fichier xml.zip dans le répertoire src/main/resources
![image](https://github.com/user-attachments/assets/e482cfd6-d266-438a-a426-bb09deb30878)

### Étape 4 : Envoi des données vers Elasticsearch
![image](https://github.com/user-attachments/assets/f62a69e6-d67c-4c20-a7bd-e37b7711d9db)

### Étape 5 : Vérification des données dans Elasticsearch
Une fois le projet exécuté, On peut vérifier les données indexées
![image](https://github.com/user-attachments/assets/89991e2e-7cc8-471e-b75a-d8e891688ab2)
![image](https://github.com/user-attachments/assets/70652992-4784-47e6-b7f1-b4506f2c4e4a)
### Lancement du programme

Le point d'entrée du programme est la classe **Main**. Cette classe effectue les étapes suivantes :

#### 1. Définition des chemins

- Le fichier ZIP contenant le fichier XML est placé dans le répertoire `src/main/resources/xml.zip`.
- Les fichiers extraits seront enregistrés dans le répertoire `target/output/`.

#### 2. Création de l'index Elasticsearch

- Un index est automatiquement créé avec un nom basé sur la date du jour : `documents_<YYYY-MM-DD>`.

#### 3. Décompression et extraction du fichier XML

- Le fichier `xml.zip` est décompressé et le fichier XML contenu est extrait.

#### 4. Analyse du fichier XML

- Le contenu du fichier XML est analysé en **streaming** pour éviter de charger tout le contenu en mémoire.
- Les documents extraits sont convertis en **JSON** à l'aide de la bibliothèque Jackson.

#### 5. Indexation des documents

- Les documents JSON sont envoyés en masse vers **Elasticsearch** en utilisant l'API **Bulk**.

#### 6. Fermeture des connexions

- La connexion Elasticsearch est proprement fermée une fois l'indexation terminée.


## Résultats
![image](https://github.com/user-attachments/assets/f61d86d9-425f-4003-b820-f8dbd082d8e0)

Tous les résultats du projet, y compris le code source, les tests et les données indexées, sont documentés dans ce dépôt

