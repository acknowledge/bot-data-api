# Data API

API made with Java Spring Boot to save and retrieve data from chatbot conversations.

# Dependencies

- Spring Boot Web
- Spring Boot Data JPA
- H2 database

The whole project is managed with Maven. With Maven you can install the dependencies, run the tests and then launch the app locally.
For convenience, a standard IDE like IntelliJ allows you to do that easily.

A JAR file is generated, but no specific steps are done to deploy the project to a production environment yet.

# REST Endpoints

- POST data/{customerId}/{dialogId}: Create new dialogue entry
- POST consents/{dialogId}: Give (or not) consent to store the dialogue
- GET data: Get filtered and paginated data back

Swagger is used to document the API and run test requests:
- Swagger web interface: http://localhost:8080/swagger-ui.html
- JSON specification of the API: http://localhost:8080/v2/api-docs

![Swagger interface](https://github.com/acknowledge/bot-data-api/blob/master/other/swagger.png?raw=true)

To manually run some test requests from the terminal, here are some examples:
- `curl -X POST localhost:8080/data/2/23 -H 'Content-type:application/json' -d '{"text":"Hey hey", "language":"EN"}'`
- `curl -X POST localhost:8080/consents/6 -H 'Content-type:application/json' -d 'true'`
- `curl -X GET "http://localhost:8080/data?language=EN&pageNo=0&pageSize=10"`

# Data storage

The API currently stores data in a local H2 relational database for ease of use. The database is cleared when the service goes down. At startup, the database is inflated with a few dummy data automatically.
It is not meant to be deployed in production.

Feel free to connect to the database via the browser. Access to H2 database interface: http://localhost:8080/h2-console/
- Credentials in local setup:
  - JDBC URL: jdbc:h2:mem:testdb
  - Password: password

![H2 interface](https://github.com/acknowledge/bot-data-api/blob/master/other/h2-interface.png?raw=true)

# Improvements to do

- Keep the data in-memory while there is no consent, and only save the whole dialog when consent is given
- Create an Enum for language to limit this field to supported languages
- Create a "service" to export the logic currently sitting in the controller
- Define better tests (unit tests and integration tests) and improve coverage
- Add authentication to access the API (Basic Auth, API Key or OAuth)
- Define deployment settings and setup a CI/CD pipeline