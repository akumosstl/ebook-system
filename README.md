# Challenge 

The perfect world couldn´t be implemented in this repo. 
To see how this repo could be in the future, using cloud patterns, swagger and real security check my others repositories, like this:

https://github.com/akumosstl/wallet-microservices


# Backend

Spring Book API
===============

This is a minimal Spring Boot application that provides:
- CORS configuration (allows requests from any origin for demo)
- JWT-based authentication (mock user 'admin' / 'password')
- REST CRUD API for Book entity
- Search endpoint: GET /api/books/search?author=


## Run:

```
docker-compose -f infra-docker-compose up 
mvn spring-boot:run
```

## Auth:
- POST /auth/login with JSON {"user":"admin","password":"password"}
- Response: {"token":"<jwt>"}
- Use header: Authorization: Bearer <jwt> for protected endpoints (POST/PUT/DELETE)

Notes:
- In production, secure the JWT secret and restrict CORS origins.

## Docker
The docker-compose file has all you need to run, including forntend app.

```
docker-compose up
```

# Frontend 

# eBook Frontend

Simple SPA using Angular.

## Build and run at localhost:
Run

```bash

npm run start

```

Then access:

```

localhost:4200

```

## Docker

First of all, you need run the following command:

```
npm run build

```

Then run:

```
docker build .

```

Then to up application and running using docker please run the docker-compose.yml file at backend project.

There you will see the forntend application as a docker-compose service.

To access through docker try:

```
localhost:3000

```