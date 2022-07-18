# iaccess-vision-assessment

## Local Setup

- Run IntelliJ IDEA
- Clone this github repository through IntelliJ IDEA
- Update maven project to download the required dependencies
- Create local mysql database ```localhost:3306/iaccess-vision```
- Run ```RestApiApplication.main```
- Access the Swagger API through ```http://localhost:8080/swagger-ui/index.html```

## Overview

- 3 environments where my apps are deployed: ```DEV, STAGE and PROD```
- 2 applications using this setup: ```app1, app2```
- In these environments, keep a list of IPs which identifies the list of clients that are allowed to access my apps
- Client can have 1 or more ip addresses
- IP address can be associated to 1 or more clients

## RESTful endpoints

- Add a client ip (IPv4 only) to the whitelist specific to an environment and app
```
POST http://localhost:8080/whitelists
```
Request Body:
```
{
  "applicationName": "app1",
  "clientName": "string",
  "environmentName": "DEV",
  "ipAddress": "string"
}
```
Take note: IP Address has regex pattern validation!
- Provide the list of ips without duplicates; can filtered by environment, app and/or client name
```
POST http://localhost:8080/whitelists/search
```
Request Body:
```
{
  "applicationName": "app1",
  "clientName": "string",
  "environmentName": "DEV"
}
```
- Remove a client ip from the list
```
DELETE http://localhost:8080/whitelists/{ip_address}
```

## Tools used

- IntelliJ IDEA
- Java 11
- Spring Boot
- Spring Data JPA
- Spring Validation
- MyBatis
- Swagger API
- Lombok
- MySQL
- Apache Tomcat
- Apache Maven

## Nice to have

- Add unit test cases
- Add Spring Secrutiy
- Deploy the application to Heroku
