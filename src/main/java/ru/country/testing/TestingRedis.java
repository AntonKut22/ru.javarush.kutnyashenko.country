package ru.country.testing;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisStringCommands;
import ru.country.redis.CityCountry;
import ru.country.utilites.RedisClientProvider;

import java.util.List;

public class TestingRedis {

    private final ObjectMapper mapper;
    private final RedisClient redisClient;

    public RedisClient getRedisClient() {
        return redisClient;
    }

    public void shutdownRedisClient(){
        redisClient.shutdown();
    }

    public TestingRedis() {
        mapper = new ObjectMapper();
        redisClient = new RedisClientProvider().prepareRedisClient();
    }

    public void testRedisData(List<Integer> ids) {
        try (StatefulRedisConnection<String, String> connection = redisClient.connect()) {
            RedisStringCommands<String, String> sync = connection.sync();
            for (Integer id : ids) {
                String value = sync.get(String.valueOf(id));
                try {
                    mapper.readValue(value, CityCountry.class);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void pushToRedis(List<CityCountry> cities) {
        try (StatefulRedisConnection<String, String> connection = redisClient.connect()) {
            RedisStringCommands<String, String> sync = connection.sync();
            for (CityCountry cityCountry : cities) {
                try {
                    sync.set(String.valueOf(cityCountry.getId()), mapper.writeValueAsString(cityCountry));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
