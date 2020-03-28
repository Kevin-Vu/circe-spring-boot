[comment]: <> "[![CircleCI](https://circleci.com/gh/Kevin-Vu/okayo-facture.svg?style=svg)](https://circleci.com/gh/Kevin-Vu/okayo-facture)"  
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/fb8ce8a48b3c42b0b120d7e93c775699)](https://www.codacy.com/manual/Kevin-Vu/okayo-facture?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Kevin-Vu/okayo-facture&amp;utm_campaign=Badge_Grade)
![Version](https://img.shields.io/badge/version-2.0.0-blue)
# Circe-Spring-Boot

Manage your clients and their invoices.

## Prerequisite
```
$ docker pull postgres:11
$ docker run --name circe-postgres -p 5432:5432 -e POSTGRES_PASSWORD=password -d postgres:11
$ docker exec circe-postgres psql -U postgres -c "CREATE DATABASE \"CIRCE\"" postgres
```

## Compile and run test
```sh
$ mvn -U clean install
```

## Run the application
```sh
$ mvn spring-boot:run
```

## Access to api documentation
Once the app is running go to `http://localhost:8080/swagger-ui.html`

## Database 
<img src="diagram.png" width="750">

## Front-End
Front Available [here](https://github.com/Kevin-Vu/circe-angular)
