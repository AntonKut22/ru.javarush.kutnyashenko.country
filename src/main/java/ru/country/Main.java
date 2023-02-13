package ru.country;

import org.hibernate.SessionFactory;
import ru.country.entity.City;
import ru.country.redis.CityCountry;
import ru.country.testing.TestingMysql;
import ru.country.testing.TestingRedis;
import ru.country.utilites.Convertor;
import ru.country.utilites.CreationSessionFactory;
import ru.country.utilites.FetchingData;

import java.util.List;
import static java.util.Objects.nonNull;

public class Main {


    public static void main(String[] args) {
        SessionFactory sessionFactory = new CreationSessionFactory().getSessionFactory();
        Convertor convertor = new Convertor();
        TestingMysql testingMysql = new TestingMysql();
        TestingRedis testingRedis = new TestingRedis();
        FetchingData fetchingData = new FetchingData();
        List<City> allCities = fetchingData.fetchData(sessionFactory);
        List<CityCountry> preparedData = convertor.transformData(allCities);
        testingRedis.pushToRedis(preparedData);

        sessionFactory.getCurrentSession().close();

        List<Integer> ids = List.of(3, 2545, 123, 4, 189, 89, 3458, 1189, 10, 102);

        long startRedis = System.currentTimeMillis();
        testingRedis.testRedisData(ids);
        long stopRedis = System.currentTimeMillis();

        long startMysql = System.currentTimeMillis();
        testingMysql.testMysqlData(ids, sessionFactory);
        long stopMysql = System.currentTimeMillis();

        System.out.printf("%s:\t%d ms\n", "Redis", (stopRedis - startRedis));
        System.out.printf("%s:\t%d ms\n", "MySQL", (stopMysql - startMysql));

        shutdown(testingRedis, sessionFactory);
    }

    private static void shutdown(TestingRedis testingRedis, SessionFactory sessionFactory) {
        if (nonNull(sessionFactory)) {
            sessionFactory.close();
        }
        if (nonNull(testingRedis.getRedisClient())) {
            testingRedis.shutdownRedisClient();
        }
    }






}