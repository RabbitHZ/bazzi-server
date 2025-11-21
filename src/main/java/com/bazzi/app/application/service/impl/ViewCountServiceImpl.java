package com.bazzi.app.application.service.impl;

import com.bazzi.app.application.dto.response.ViewCountResponseDto;
import com.bazzi.app.application.service.ViewCountService;
import com.bazzi.app.infrastructure.persistence.RedisViewCountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ViewCountServiceImpl implements ViewCountService {

    private final RedisViewCountRepository redisViewCountRepository;
    @Override
    public ViewCountResponseDto getViewCount(String username) {
        int today = redisViewCountRepository.getTodayViewCount(username);
        int total = redisViewCountRepository.getTotalViewCount(username);
        return new ViewCountResponseDto(today, total);
    }

    @Override
    public ViewCountResponseDto incrementViewCount(String username) {
        redisViewCountRepository.incrementTodayViewCount(username);
        redisViewCountRepository.incrementTotalViewCount(username);
        return getViewCount(username);
    }

    @Override
    public ViewCountResponseDto resetViewCount(String username) {
        redisViewCountRepository.resetViewCount(username);
        return getViewCount(username);
    }

    @Override
    public void updateTotalWithToday(String username) {
        redisViewCountRepository.updateTotalWithToday(username);
    }
}
