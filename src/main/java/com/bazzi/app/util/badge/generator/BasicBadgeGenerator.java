package com.bazzi.app.util.badge.generator;

import org.springframework.stereotype.Component;

/**
 * 기본 스타일 뱃지 생성기
 * color 파라미터로 색상을 직접 지정합니다.
 */
@Component
public class BasicBadgeGenerator implements BadgeGenerator {

    @Override
    public String generate(String color, String label, long today, long total) {
        // hex 색상값에 # 접두사가 없으면 추가
        if (color != null && !color.startsWith("#") && !color.startsWith("rgb") && color.matches("[0-9A-Fa-f]{3,6}")) {
            color = "#" + color;
        }
        if (color == null || color.isEmpty()) {
            color = "#4CAF50";
        }

        String viewsText = formatViews(total);
        String todayText = formatViews(today);

        return String.format(
                "<svg width=\"150\" height=\"28\" xmlns=\"http://www.w3.org/2000/svg\">\n" +
                "  <!-- 배경 -->\n" +
                "  <rect width=\"150\" height=\"28\" rx=\"5\" fill=\"%s\"/>\n" +
                "  \n" +
                "  <!-- 텍스트 -->\n" +
                "  <text x=\"75\" y=\"18\" font-family=\"Arial, sans-serif\" font-size=\"12\" \n" +
                "        font-weight=\"bold\" fill=\"white\" text-anchor=\"middle\">\n" +
                "    %s: %s / %s\n" +
                "  </text>\n" +
                "</svg>",
                color,
                label, todayText, viewsText
        );
    }

    private String formatViews(long views) {
        if (views >= 1000000) {
            return String.format("%.1fM", views / 1000000.0);
        } else if (views >= 1000) {
            return String.format("%.1fK", views / 1000.0);
        }
        return String.valueOf(views);
    }
}
