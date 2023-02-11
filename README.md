# the final task of the module "working with databases" of the javarush university project

## the purpose of the project is to determine the difference in the time of receiving requests when using Redis and MySQL


### to run the application you need to follow the following steps:

1. run a command to start the container MySQL:  
docker run --name mysql -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=1QaZ2WsX --restart unless-stopped -v mysql:/var/lib/mysql mysql:8
2. import dump from dump-hibernate-final.sql
3. run a command to start the container Redis:  
docker run -d --name redis-stack -p 6379:6379 -p 8001:8001 redis/redis-stack:latest

Based on the results of 10 launches, we can conclude that using radish speeds up work by 37 percent

![image](https://user-images.githubusercontent.com/102983817/218260370-27a97e47-f7a6-436b-89f5-db1331f52a85.png)
