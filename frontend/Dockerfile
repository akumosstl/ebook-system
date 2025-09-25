FROM node:lts-alpine as build

WORKDIR /app

COPY package*.json ./
RUN rm -rf node_modules package-lock.json # Clean up old dependencies
RUN npm install

COPY . .

RUN npm run build

FROM nginx:alpine

COPY --from=build /dist/ebook-frontend /usr/share/nginx/html

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]