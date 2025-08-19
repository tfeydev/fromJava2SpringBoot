FROM node:22

WORKDIR /app

# Dependencies
COPY package*.json ./
RUN npm install

# Copy source
COPY . .

EXPOSE 4200

# Angular Dev Server
CMD ["npm", "run", "start"] 
