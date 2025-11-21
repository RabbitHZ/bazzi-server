package com.bazzi.app.application.service;

import com.bazzi.app.application.dto.response.ViewCountResponseDto;

public interface ViewCountService {
    ViewCountResponseDto getViewCount(String username);
    ViewCountResponseDto incrementViewCount(String username);
    ViewCountResponseDto resetViewCount(String username);
    void updateTotalWithToday(String username);
}
