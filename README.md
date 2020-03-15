[![CircleCI](https://circleci.com/gh/Kevin-Vu/okayo-facture.svg?style=svg)](https://circleci.com/gh/Kevin-Vu/okayo-facture)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/fb8ce8a48b3c42b0b120d7e93c775699)](https://www.codacy.com/manual/Kevin-Vu/okayo-facture?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Kevin-Vu/okayo-facture&amp;utm_campaign=Badge_Grade)
![Version](https://img.shields.io/badge/version-1.0.0-blue)
# Okayo-facture

Manage your clients and their invoices.

## Compile and run test
```sh
mvn -U clean install
```

## Run the application
```sh
mvn spring-boot:run
```

## Access to api documentation
Once the app is running go to `http://localhost:8080/swagger-ui.html`

## Database 
<img src="diagram.png" width="750">

## Next development
- Connect the a real database (actually it's a H2 in memory)
- Add more api : CRUD for designation etc
- Implement an authentication in order to access the content
- Develop a front-end
