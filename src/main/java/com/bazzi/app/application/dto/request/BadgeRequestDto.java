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

    @Schema(description = "github url", example = "https://github.com/username")
    private String url = "";

    @Schema(description = "뱃지 색상 (색상명, hex, rgb 지원)", example = "#4CAF50")
    @Pattern(regexp = "^(#[0-9A-Fa-f]{6}|#[0-9A-Fa-f]{3}|rgb\\(\\s*\\d{1,3}\\s*,\\s*\\d{1,3}\\s*,\\s*\\d{1,3}\\s*\\)|[a-zA-Z]+)$",
            message = "색상값은 hex(#RRGGBB), rgb(r,g,b) 또는 색상명이어야 합니다")
    private String color = "#4CAF50";

    @Schema(description = "뱃지 라벨", example = "Views")
    private String label = "Views";

    @Schema(description = "뱃지 폰트 사이즈", example = "12")
    private int fontSize = 12;
}
