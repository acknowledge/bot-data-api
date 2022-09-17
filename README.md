# Data API

API made with Spring Boot to save and retrieve data from chatbot conversations.

# Dependencies

- Spring Boot Web
- Spring Boot Data JPA
- H2 database

# REST Endpoints

- POST data/{customerId}/{dialogId}: Create new dialogue entry
- POST consents/{dialogId}: Give (or not) consent to store the dialogue
- GET data: Get filtered and paginated data

# Useful links

- Swagger web interface to see the API and run test requests: http://localhost:8080/swagger-ui.html
- JSON specification of the API: http://localhost:8080/v2/api-docs
- Access to H2 database interface: http://localhost:8080/h2-console/
  - Credentials in local setup:
    - JDBC URL: jdbc:h2:mem:testdb
    - Password: password


# Improvements to do

- Enum for language
- Tests
- Get data/ --> finish the filters
- 