# TrainingPlatformSpring
Spring Boot 2.0 application for university project written in Kotlin.

This app serves as a backend for TraineeNet app - see [repository](https://github.com/PGliw/TraineeNet)

## Database
App uses Google Cloud Platform (GCP) Google Cloud instance with MySQL 5.7 DBMS.

## Entity Relationship Diagram
Diagram generated in MySQL Workbench based on tables created by Hibernate ORM.
![ERD](ERD.png)

## Class diagram
Although the diagram is written in Polish, all entities, attributes and methods will be named in English.
![Class diagram](ClassDiagram.png)

## Caution
This app is not fully implemented as it wasn't the goal of the university project.

## Some security issues shoud be taken into accound:
* password are not encrypted (they are stored as cleartext)
* trainee can login to trainer app and vice-versa (althought not sure weather it is vulnerability or feature)

Howewer, OAuth was used as authorization method.
