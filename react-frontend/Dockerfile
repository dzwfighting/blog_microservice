FROM node:19-alpine

WORKDIR /app

COPY package*.json ./

RUN npm install

COPY . .

RUN npm run build

RUN npm install -g serve

EXPOSE 3307

CMD ["serve", "-s", "build", "-l", "3307"]