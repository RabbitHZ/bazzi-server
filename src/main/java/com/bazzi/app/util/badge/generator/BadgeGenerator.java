package com.bazzi.app.util.badge.generator;

/**
 * 뱃지 생성기 인터페이스
 * 새로운 스타일 추가 시 이 인터페이스를 구현하면 됩니다.
 */
public interface BadgeGenerator {

    /**
     * 뱃지 SVG 생성
     *
     * @param color 뱃지 색상 (basic 스타일에서만 사용, 다른 스타일은 무시 가능)
     * @param label 뱃지 라벨
     * @param today 오늘 조회수
     * @param total 총 조회수
     * @return SVG 문자열
     */
    String generate(String color, String label, long today, long total);
}
