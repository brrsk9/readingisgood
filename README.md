----------------------
Tech Stack
----------------------
- Java 11
- Spring Boot
- Hibernate
- PostgreSQL
- Docker
- Swagger


----------------------
Informations
----------------------
- Hibernate Envers is used for logging changes on entities.
- Free version of AWS PostgreSQL is used. DB username and password are in application.yaml.
- Basic authentication is used for securing the application. Username : admin1 - Password: admin1pass
- Postman request examples are in the ZIP file.
- Docker is used for containerization.
- Swagger is used for OpenAPI specification.


----------------------
Links and Commands
----------------------
- Swagger : http://localhost:8080/swagger-ui.html
- Running container commands :
	- mvn clean install
	- docker build -t readingisgood-app .
	- docker run -p 8080:8080 readingisgood-app
