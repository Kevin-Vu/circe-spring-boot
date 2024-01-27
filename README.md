[comment]: <> "[![CircleCI](https://circleci.com/gh/Kevin-Vu/okayo-facture.svg?style=svg)](https://circleci.com/gh/Kevin-Vu/okayo-facture)"
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/652838ec809046299d53bb617ca74753)](https://www.codacy.com/gh/Kevin-Vu/circe-spring-boot/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Kevin-Vu/circe-spring-boot&amp;utm_campaign=Badge_Grade)
![Version](https://img.shields.io/badge/version-5.0.0-blue)

# Circe-Spring-Boot

Manage your clients and their invoices.

## Prerequisite

```
$ docker pull postgres:14
$ docker run --name circe-postgres -p 5432:5432 -e POSTGRES_PASSWORD=password -d postgres:14
$ docker exec circe-postgres psql -U postgres -c "CREATE DATABASE \"CIRCE_DATA\"" postgres
$ docker exec circe-postgres psql -U postgres -c "CREATE DATABASE \"CIRCE_REFERENTIEL\"" postgres
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

Once the app is running go to `http://localhost:8000/swagger-ui.html`  
Credentials : `admin:admin`

## Database

<img src="diagram.png" width="750" alt="database">

## Front-End

Front Available [here](https://github.com/Kevin-Vu/circe-angular)
