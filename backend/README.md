Spring Book API
===============

This is a minimal Spring Boot application that provides:
- CORS configuration (allows requests from any origin for demo)
- JWT-based authentication (mock user 'admin' / 'password')
- REST CRUD API for Book entity
- Search endpoint: GET /api/books/search?author=


## Run:
- docker-compose -f infra-docker-compose up 
- mvn spring-boot:run

## Auth:
- POST /auth/login with JSON {"user":"admin","password":"password"}
- Response: {"token":"<jwt>"}
- Use header: Authorization: Bearer <jwt> for protected endpoints (POST/PUT/DELETE)

Notes:
- In production, secure the JWT secret and restrict CORS origins.

## Docker
The docker-compose file has all you need to run, including forntend app.

- docker-compose up


Then access:


```
localhost:3000

```
To login use:

```
user: admin

password: password

```