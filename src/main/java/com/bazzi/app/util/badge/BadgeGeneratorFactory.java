package com.bazzi.app.util.badge;

import com.bazzi.app.util.badge.generator.BadgeGenerator;
import com.bazzi.app.util.badge.generator.BasicBadgeGenerator;
import com.bazzi.app.util.badge.generator.MapleBadgeGenerator;
import com.bazzi.app.util.badge.generator.RabbitBadgeGenerator;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;

/**
 * 뱃지 생성기 팩토리
 * 스타일 타입에 따라 적절한 Generator를 반환합니다.
 *
 * 새로운 스타일 추가 방법:
 * 1. BadgeStyleType에 새 enum 값 추가
 * 2. BadgeGenerator를 구현하는 새 클래스 생성
 * 3. 이 팩토리의 생성자에서 generators Map에 등록
 */
@Component
public class BadgeGeneratorFactory {

    private final Map<BadgeStyleType, BadgeGenerator> generators;

    public BadgeGeneratorFactory(
            BasicBadgeGenerator basicBadgeGenerator,
            MapleBadgeGenerator mapleBadgeGenerator,
            RabbitBadgeGenerator rabbitBadgeGenerator
    ) {
        this.generators = new EnumMap<>(BadgeStyleType.class);
        this.generators.put(BadgeStyleType.BASIC, basicBadgeGenerator);
        this.generators.put(BadgeStyleType.MAPLE, mapleBadgeGenerator);
        this.generators.put(BadgeStyleType.RABBIT, rabbitBadgeGenerator);
    }

    /**
     * 스타일 타입에 해당하는 Generator 반환
     */
    public BadgeGenerator getGenerator(BadgeStyleType styleType) {
        return generators.getOrDefault(styleType, generators.get(BadgeStyleType.BASIC));
    }

    /**
     * 스타일 타입 코드로 Generator 반환
     */
    public BadgeGenerator getGenerator(String styleTypeCode) {
        BadgeStyleType styleType = BadgeStyleType.fromCode(styleTypeCode);
        return getGenerator(styleType);
    }

    /**
     * 뱃지 SVG 생성 (편의 메서드)
     */
    public String generateBadge(String styleType, String color, String label, long today, long total) {
        BadgeGenerator generator = getGenerator(styleType);
        return generator.generate(color, label, today, total);
    }
}
