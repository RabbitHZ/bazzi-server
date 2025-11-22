package com.bazzi.app.util;

import org.springframework.stereotype.Component;

@Component
public class SvgGenerator {

    public static String generateBadge(String color, String label, int fontSize, long today, long total) {
        // hex 색상값에 # 접두사가 없으면 추가
        if (color != null && !color.startsWith("#") && !color.startsWith("rgb") && color.matches("[0-9A-Fa-f]{3,6}")) {
            color = "#" + color;
        }

        // 간단한 SVG 생성 예제 (실제 구현은 더 복잡할 수 있음)
        return String.format(
                "<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"120\" height=\"20\">" +
                        "<rect width=\"100%%\" height=\"100%%\" fill=\"%s\"/>" +
                        "<text x=\"10\" y=\"14\" font-size=\"%d\" fill=\"white\">%s: %d / %d</text>" +
                        "</svg>",
                color, fontSize, label, today, total
        );
    }
}
