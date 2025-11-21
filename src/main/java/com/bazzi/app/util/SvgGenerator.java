package com.bazzi.app.util;

import org.springframework.stereotype.Component;

@Component
public class SvgGenerator {

    public static String generateBadge(String color, String label, int fontSize, long today, long total) {
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
