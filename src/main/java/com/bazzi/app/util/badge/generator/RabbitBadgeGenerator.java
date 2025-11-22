package com.bazzi.app.util.badge.generator;

import org.springframework.stereotype.Component;

/**
 * 래빗 스타일 뱃지 생성기
 * 귀여운 토끼 캐릭터가 포함된 핑크색 뱃지
 */
@Component
public class RabbitBadgeGenerator implements BadgeGenerator {

    @Override
    public String generate(String color, String label, long today, long total) {
        // color 파라미터는 무시하고 고정 핑크 스타일 사용
        String viewsText = formatViews(total);
        String todayText = formatViews(today);

        return String.format(
                "<svg width=\"150\" height=\"28\" xmlns=\"http://www.w3.org/2000/svg\">\n" +
                "  <defs>\n" +
                "    <!-- 핑크 그라데이션 -->\n" +
                "    <linearGradient id=\"pinkGrad\" x1=\"0%%\" y1=\"0%%\" x2=\"0%%\" y2=\"100%%\">\n" +
                "      <stop offset=\"0%%\" style=\"stop-color:#ffd4f0;stop-opacity:1\" />\n" +
                "      <stop offset=\"100%%\" style=\"stop-color:#ffb4e0;stop-opacity:1\" />\n" +
                "    </linearGradient>\n" +
                "  </defs>\n" +
                "  \n" +
                "  <!-- 외곽 테두리 -->\n" +
                "  <path d=\"M 5 2 L 145 2 L 148 5 L 148 23 L 145 26 L 5 26 L 2 23 L 2 5 Z\" \n" +
                "        fill=\"#ff94d4\" stroke=\"#ff94d4\" stroke-width=\"1\"/>\n" +
                "  \n" +
                "  <!-- 메인 배경 -->\n" +
                "  <path d=\"M 6 4 L 144 4 L 146 6 L 146 22 L 144 24 L 6 24 L 4 22 L 4 6 Z\" \n" +
                "        fill=\"url(#pinkGrad)\"/>\n" +
                "  \n" +
                "  <!-- 상단 하이라이트 -->\n" +
                "  <rect x=\"8\" y=\"6\" width=\"134\" height=\"2\" fill=\"white\" opacity=\"0.5\" rx=\"1\"/>\n" +
                "  \n" +
                "  <!-- 왼쪽 토끼 캐릭터 -->\n" +
                "  <g transform=\"translate(14, 14)\">\n" +
                "    <!-- 토끼 몸통/얼굴 -->\n" +
                "    <ellipse cx=\"0\" cy=\"0\" rx=\"4.5\" ry=\"5\" fill=\"white\"/>\n" +
                "    \n" +
                "    <!-- 왼쪽 귀 -->\n" +
                "    <ellipse cx=\"-2.5\" cy=\"-4.5\" rx=\"1.3\" ry=\"3.5\" fill=\"white\" \n" +
                "             transform=\"rotate(-15 -2.5 -4.5)\"/>\n" +
                "    <!-- 왼쪽 귀 안쪽 -->\n" +
                "    <ellipse cx=\"-2.5\" cy=\"-4.3\" rx=\"0.6\" ry=\"2\" fill=\"#ffb4e0\" \n" +
                "             transform=\"rotate(-15 -2.5 -4.3)\"/>\n" +
                "    \n" +
                "    <!-- 오른쪽 귀 -->\n" +
                "    <ellipse cx=\"2.5\" cy=\"-4.5\" rx=\"1.3\" ry=\"3.5\" fill=\"white\" \n" +
                "             transform=\"rotate(15 2.5 -4.5)\"/>\n" +
                "    <!-- 오른쪽 귀 안쪽 -->\n" +
                "    <ellipse cx=\"2.5\" cy=\"-4.3\" rx=\"0.6\" ry=\"2\" fill=\"#ffb4e0\" \n" +
                "             transform=\"rotate(15 2.5 -4.3)\"/>\n" +
                "    \n" +
                "    <!-- 눈 -->\n" +
                "    <circle cx=\"-1.5\" cy=\"-0.5\" r=\"0.6\" fill=\"#333333\"/>\n" +
                "    <circle cx=\"1.5\" cy=\"-0.5\" r=\"0.6\" fill=\"#333333\"/>\n" +
                "    \n" +
                "    <!-- 볼 터치 -->\n" +
                "    <ellipse cx=\"-3\" cy=\"1\" rx=\"1.2\" ry=\"1\" fill=\"#ff94d4\" opacity=\"0.4\"/>\n" +
                "    <ellipse cx=\"3\" cy=\"1\" rx=\"1.2\" ry=\"1\" fill=\"#ff94d4\" opacity=\"0.4\"/>\n" +
                "    \n" +
                "    <!-- 코 -->\n" +
                "    <path d=\"M 0 1 L -0.6 0 L 0.6 0 Z\" fill=\"#ff69b4\"/>\n" +
                "    \n" +
                "    <!-- 입 -->\n" +
                "    <path d=\"M 0 1.2 L 0 2 M 0 2 L -1 3 M 0 2 L 1 3\" \n" +
                "          stroke=\"#ff69b4\" stroke-width=\"0.4\" fill=\"none\" \n" +
                "          stroke-linecap=\"round\" stroke-linejoin=\"round\"/>\n" +
                "  </g>\n" +
                "  \n" +
                "  <!-- 텍스트 -->\n" +
                "  <text x=\"75\" y=\"18\" font-family=\"Arial, sans-serif\" font-size=\"11\" \n" +
                "        font-weight=\"bold\" fill=\"white\" text-anchor=\"middle\">\n" +
                "    %s: %s / %s\n" +
                "  </text>\n" +
                "  \n" +
                "  <!-- 오른쪽 당근 -->\n" +
                "  <g transform=\"translate(138, 14)\">\n" +
                "    <!-- 당근 몸통 -->\n" +
                "    <ellipse cx=\"0\" cy=\"2\" rx=\"2.5\" ry=\"5\" fill=\"#FF7F00\"/>\n" +
                "    <!-- 당근 줄무늬 -->\n" +
                "    <path d=\"M -1.5 0 Q 0 0.5 1.5 0\" stroke=\"#E56700\" stroke-width=\"0.3\" fill=\"none\"/>\n" +
                "    <path d=\"M -2 2 Q 0 2.5 2 2\" stroke=\"#E56700\" stroke-width=\"0.3\" fill=\"none\"/>\n" +
                "    <path d=\"M -1.5 4 Q 0 4.5 1.5 4\" stroke=\"#E56700\" stroke-width=\"0.3\" fill=\"none\"/>\n" +
                "    <!-- 당근 잎 -->\n" +
                "    <ellipse cx=\"-1.5\" cy=\"-3\" rx=\"0.8\" ry=\"2.5\" fill=\"#4CAF50\" transform=\"rotate(-20 -1.5 -3)\"/>\n" +
                "    <ellipse cx=\"0\" cy=\"-3.5\" rx=\"0.8\" ry=\"2.8\" fill=\"#66BB6A\"/>\n" +
                "    <ellipse cx=\"1.5\" cy=\"-3\" rx=\"0.8\" ry=\"2.5\" fill=\"#4CAF50\" transform=\"rotate(20 1.5 -3)\"/>\n" +
                "  </g>\n" +
                "</svg>",
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
