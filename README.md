# the final task of the module "working with databases" of the javarush university project


### to run the application you need to follow the following steps:

1. run a command to start the container MySQL:  
docker run --name mysql -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=1QaZ2WsX --restart unless-stopped -v mysql:/var/lib/mysql mysql:8
2. import dump from dump-hibernate-final.sql
3. run a command to start the container Redis:  
docker run -d --name redis-stack -p 6379:6379 -p 8001:8001 redis/redis-stack:latest

Based on the results of 10 launches, we can conclude that using radish speeds up work by 37 percent

Redis	MySQL	% faster
115	145	26.09
99	138	39.39
100	134	34.00
91	119	30.77
92	147	59.78
85	142	67.06
101	127	25.74
101	136	34.65
87	119	36.78
100	120	20.00
	average:	37.43
![image](https://user-images.githubusercontent.com/102983817/218260370-27a97e47-f7a6-436b-89f5-db1331f52a85.png)
