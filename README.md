
# Instant System - Parking API

Le but de l’exercice est de :  développer une application “serveur” exposant une API REST pour permettre à une application mobile, ou un site Web, d’afficher la liste des parkings à proximité comme illustré sur l’écran ci-dessou

![Logo](https://lh4.googleusercontent.com/caFvi1UCXEGCkuqCNwq5l-_Z6ebqEHSdIhx9nojAPPbKT9AHbdROflrs1NM1rxUYrj_inJYUFr2j05R_LtZ8-LBiB5XRiYFrpGohRy-pgTghDoDToBN7wG-aMon5xsU-TZWb_wRs=s1600)


## Resources

[All parking API]('https://data.grandpoitiers.fr/api/records/1.0/search/?dataset=mobilite-parkings-grand-poitiers-donnees-metiers&rows=1000&facet=nom_du_parking&facet=zone_tarifaire&facet=statut2&facet=statut3')

[Available place real time API]('https://data.grandpoitiers.fr/api/records/1.0/search/?dataset=mobilites-stationnement-des-parkings-en-temps-reel&facet=nom')


## Librairies 

 - Spring Web (pour nous permettre d'exposer le web service)
 - Jackson (pour transformer un string vers un objet json)
 - Spring Test (pour réaliser les tests unitaires)

## Démarche

`1 - Création d'un projet Spring  : Dans cet exercice on a seulement besoin de quelques librairies qui sont (Spring Web, Jackson, Spring Test)`


`2 - Création des packages nécessaires :`

    - Package Controller : dans ce package on va mettre les points d'entrée   
    - Package Service : dans ce package on va développer la logique métier de l'application
    - Package DTO : dans ce package on va créer les entités (non mappé) dont on a besoin 

`3 - Création de l'entité 'Parking' dans le package DTO puisque on va pas mapper cette entité :`

| Variable         | Type      | Description                               |
|:-----------------|:----------|:------------------------------------------|
| `name`           | `String`  | Nom de parking                            |
| `capacity`       | `Integer` | La capacité de parking                    |
| `availablePlace` | `Integer` | Les places disponibles au parking         |
| `point2D`        | `Point2D` | La localisation de parking                |
| `distance`       | `Double`  | La distance entre le client et le parking |
| `isFree`         | `Boolean` | pour voir si le parking est gratuit       |


`4 - Création et mettre en place ParkingService :`
    
    
    Création et développement de la méthode List<Parking> getAvailableParking(int diffDistance, Double xPosition, Double yPosition) 
       
       - Au premier lieu on va récupérer les données exposées par le web Service
       - Deuxiéme étape c'est de filtrer ces données en appliquant quelques critéres (Places disponibles , et Approximité)
       - Par la suite ordonner la liste selon l'approximité et retourner le résultat  
    
    Création et développement de la méthod getWithFreeCriteria(Double xPosition, Double yPosition,IsFree isFree)

       - Au premier lieu on va récupérer les données exposées par le web Service
       - Deuxiéme étape c'est de filtrer ces données en appliquant le critére de recherche isFree
       - Par la suite ordonner la liste selon l'approximité et retourner le résultat  

`5 - Créer la classe ParkingController dans le package Controller , par la suite dans les ws faire un appelle au service ParkingService et retourner le resultat de getAvailableParking et getWithFreeCriteria`
    


## Référence API

#### Get available parking

```http
  GET /api/get-available-park/{diffDistance}?xPosition=var&yPosition=var
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `diffDistance` | `Integer` | **Required**. le rayon de la recherche |
| `xPosition` | `Integer` | **Required**. postion x du client |
| `yPosition` | `Integer` | **Required**. position y du client |

#### Get free parking

```http
  GET /api/get-available-park/{isFree}?xPosition=var&yPosition=var
```

| Parameter   | Type      | Description                                        |
|:------------|:----------|:---------------------------------------------------|
| `IsFree`    | `IsFree`  | **Required**. Valeurs Possible : PAID,FREE,UNKNOWN |
| `xPosition` | `Integer` | **Required**. postion x du client                  |
| `yPosition` | `Integer` | **Required**. position y du client                 |


## Proléme détecté et non résolu

l’URL et format des données parking pourront donc être complètement différents, donc il faut configurer le projet pour qu'il puisse traiter n'importe quel schéma de donnée .


