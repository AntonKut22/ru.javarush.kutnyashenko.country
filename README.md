# the final task of the module "working with databases" of the javarush university project


### to run the application you need to follow the following steps:

1. run a command to start the container MySQL
docker run --name mysql -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=1QaZ2WsX --restart unless-stopped -v mysql:/var/lib/mysql mysql:8
2. import dump from dump-hibernate-final.sql
3. run a command to start the container Redis
docker run -d --name redis-stack -p 6379:6379 -p 8001:8001 redis/redis-stack:latest
