package com.bazzi.app.util.badge.generator;

import org.springframework.stereotype.Component;

/**
 * 메이플스토리 스타일 뱃지 생성기
 * 고정된 오렌지 스타일을 사용합니다. (color 파라미터 무시)
 */
@Component
public class MapleBadgeGenerator implements BadgeGenerator {

    // 고정 스타일 색상
    private static final String MAIN_COLOR = "#FF6B00";
    private static final String ACCENT_COLOR = "#FF9500";
    private static final String BORDER_COLOR = "#CC5500";

    @Override
    public String generate(String color, String label, long today, long total) {
        // color 파라미터는 무시하고 고정 스타일 사용
        String viewsText = formatViews(total);
        String todayText = formatViews(today);

        return String.format(
                "<svg width=\"150\" height=\"28\" xmlns=\"http://www.w3.org/2000/svg\">\n" +
                "  <defs>\n" +
                "    <linearGradient id=\"mainGrad\" x1=\"0%%\" y1=\"0%%\" x2=\"0%%\" y2=\"100%%\">\n" +
                "      <stop offset=\"0%%\" style=\"stop-color:%s;stop-opacity:1\" />\n" +
                "      <stop offset=\"50%%\" style=\"stop-color:%s;stop-opacity:1\" />\n" +
                "      <stop offset=\"100%%\" style=\"stop-color:%s;stop-opacity:1\" />\n" +
                "    </linearGradient>\n" +
                "    <filter id=\"textShadow\" x=\"-50%%\" y=\"-50%%\" width=\"200%%\" height=\"200%%\">\n" +
                "      <feGaussianBlur in=\"SourceAlpha\" stdDeviation=\"0.5\"/>\n" +
                "      <feOffset dx=\"1\" dy=\"1\" result=\"offsetblur\"/>\n" +
                "      <feMerge>\n" +
                "        <feMergeNode/>\n" +
                "        <feMergeNode in=\"SourceGraphic\"/>\n" +
                "      </feMerge>\n" +
                "    </filter>\n" +
                "  </defs>\n" +
                "  \n" +
                "  <!-- 외곽 테두리 -->\n" +
                "  <path d=\"M 5 2 L 145 2 L 148 5 L 148 23 L 145 26 L 5 26 L 2 23 L 2 5 Z\" \n" +
                "        fill=\"%s\" stroke=\"%s\" stroke-width=\"1\"/>\n" +
                "  \n" +
                "  <!-- 메인 배경 -->\n" +
                "  <path d=\"M 6 4 L 144 4 L 146 6 L 146 22 L 144 24 L 6 24 L 4 22 L 4 6 Z\" \n" +
                "        fill=\"url(#mainGrad)\"/>\n" +
                "  \n" +
                "  <!-- 상단 하이라이트 -->\n" +
                "  <rect x=\"8\" y=\"6\" width=\"134\" height=\"2\" fill=\"%s\" opacity=\"0.6\" rx=\"1\"/>\n" +
                "  \n" +
                "  <!-- 장식 요소 -->\n" +
                "  <circle cx=\"10\" cy=\"14\" r=\"2\" fill=\"%s\" opacity=\"0.8\"/>\n" +
                "  <circle cx=\"140\" cy=\"14\" r=\"2\" fill=\"%s\" opacity=\"0.8\"/>\n" +
                "  \n" +
                "  <!-- 텍스트 -->\n" +
                "  <text x=\"75\" y=\"18\" font-family=\"Arial, sans-serif\" font-size=\"11\" \n" +
                "        font-weight=\"bold\" fill=\"white\" text-anchor=\"middle\"\n" +
                "        filter=\"url(#textShadow)\">\n" +
                "    %s: %s / %s\n" +
                "  </text>\n" +
                "</svg>",
                // defs - mainGrad
                ACCENT_COLOR, MAIN_COLOR, BORDER_COLOR,
                // 외곽 테두리
                BORDER_COLOR, BORDER_COLOR,
                // 상단 하이라이트
                ACCENT_COLOR,
                // 장식 요소
                ACCENT_COLOR, ACCENT_COLOR,
                // 텍스트
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
