# Build Stage
FROM node:22 AS build
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
ARG NODE_ENV=development
RUN if [ "$NODE_ENV" = "production" ]; then npm run build --configuration production; fi

# Runtime Stage
FROM nginx:alpine
COPY --from=build /app/dist/frontend /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
