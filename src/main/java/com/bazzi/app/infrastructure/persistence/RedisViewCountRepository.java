package com.bazzi.app.infrastructure.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RedisViewCountRepository {

    private final RedisTemplate<String, String> redisTemplate;

    public RedisViewCountRepository(@Qualifier("stringRedisTemplate") RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    private static final String TODAY_KEY_PREFIX = "today:";
    private static final String TOTAL_KEY_PREFIX = "total:";

    //오늘 조회수 조회
    public int getTodayViewCount(String username) {
        String todayKey = TODAY_KEY_PREFIX + username;
        String today = redisTemplate.opsForValue().get(todayKey);
        return Integer.parseInt(today != null ? today : "0");
    }

    //전체 조회수 조회
    public int getTotalViewCount(String username) {
        String totalKey = TOTAL_KEY_PREFIX + username;
        String total = redisTemplate.opsForValue().get(totalKey);
        return Integer.parseInt(total != null ? total : "0");
    }

    //오늘 조회수 증가
    public void incrementTodayViewCount(String username) {
        String todayKey = TODAY_KEY_PREFIX + username;
        redisTemplate.opsForValue().increment(todayKey, 1);
    }

    //전체 조회수 증가
    public void incrementTotalViewCount(String username) {
        String totalKey = TOTAL_KEY_PREFIX + username;
        redisTemplate.opsForValue().increment(totalKey, 1);
    }

    //오늘, 전체 조회수 초기화
    public void resetViewCount(String username) {
        String todayKey = TODAY_KEY_PREFIX + username;
        String totalKey = TOTAL_KEY_PREFIX + username;
        redisTemplate.opsForValue().set(todayKey, "0");
        redisTemplate.opsForValue().set(totalKey, "0");
    }
}
