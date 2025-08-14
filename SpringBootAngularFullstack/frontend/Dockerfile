# Stage 1: Build Angular app
FROM node:22-alpine AS build
WORKDIR /app

# Build argument for API URL
ARG API_BASE_URL
ENV API_BASE_URL=${API_BASE_URL}

# Install Angular CLI globally
RUN npm install -g @angular/cli

# Install dependencies
COPY package*.json ./
RUN npm install

# Copy source
COPY . .

# Replace API base URL in config.json
RUN sed -i "s|API_BASE_URL_PLACEHOLDER|${API_BASE_URL}|g" src/assets/config.json

# Build production
RUN ng build --configuration production

# Stage 2: Serve with Nginx
FROM nginx:alpine
COPY --from=build /app/dist/*/browser/ /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
