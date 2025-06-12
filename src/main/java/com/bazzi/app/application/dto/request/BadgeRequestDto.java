package com.bazzi.app.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Badge 요청 DTO")
public class BadgeRequestDto {

    @Schema(description = "github url", example = "https://github.com/example")
    private String url = "";

    @Schema(description = "뱃지 색상", example = "blue")
    private String color = "blue";

    @Schema(description = "뱃지 라벨", example = "Views")
    private String label = "Views";

    @Schema(description = "뱃지 폰트 사이즈", example = "12")
    private int fontSize = 12;
}
