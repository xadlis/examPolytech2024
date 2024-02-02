FROM node:10 as builder

WORKDIR /app

COPY . .

LABEL maintainer="ghislain"

RUN npm install
RUN npm run build

FROM nginx

COPY nginx/ /etc/nginx/

COPY entrypoint.sh /docker-entrypoint.d/entrypoint.sh
RUN chmod +x /docker-entrypoint.d/entrypoint.sh

COPY --from=builder /app/dist/* /usr/share/nginx/html