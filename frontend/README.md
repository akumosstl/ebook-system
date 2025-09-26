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
docker build -t frontend:angular .

```

Then to up application and running using docker please run the docker-compose.yml file at backend project.

There you will see the forntend application as a docker-compose service.