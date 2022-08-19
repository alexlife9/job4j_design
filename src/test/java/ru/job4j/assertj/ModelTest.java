package ru.job4j.assertj;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

/**
 * assertj Утверждения с примитивными типами
 *
 * @author Alex_life
 * @version 1.0
 * @since 20.08.2022
 */
class ModelTest {

    /**
     * Порядок написания утверждений в библиотеке AssertJ простой и единообразный -
     * в метод assertThat() передаем результат выполнения этапа Act (переменная result) и
     * проверяем соответствие ожидаемому результату - мы ожидаем true:
     */
    @Test
    void checkBoolean() {
        Model model = new Model(1, 5.255d, "name", true);
        boolean result = model.isCondition();
        assertThat(result).isTrue();
    }

    /**
     *
     * Возможна проверка сразу нескольких утверждений для одного assertThat(result).
     * При таком виде проверки надо понимать, что тест будет пройден только если все утверждения вернут true.
     * Если какое-то утверждение вернет false, то следующие за ним утверждения проверяться не будут.
     */
    @Test
    void checkStringChain() {
        Model model = new Model(1, 5.255d, "I am learning Java", true);
        String result = model.getLine();
        assertThat(result).isNotNull()
                .isNotEmpty()
                .isNotBlank()
                .containsIgnoringCase("java")
                .contains("am", "Java")
                .doesNotContain("javascript")
                .startsWith("I am")
                .startsWithIgnoringCase("i")
                .endsWith("Java")
                .isEqualTo("I am learning Java")
        ;
    }

    /**
     * Проверка целочисленных типов интуитивно понятна:
     */
    @Test
    void checkInt() {
        Model model = new Model(2, 5.2d, "name", true);
        int result = model.getTop();
        assertThat(result).isNotZero()
                .isPositive()
                .isEven()
                .isGreaterThan(1)
                .isLessThan(3)
                .isEqualTo(2)
        ;
    }

    /**
     * Количественная проверка типов с плавающей точкой на равенство производится с учетом точности оценки,
     * которая может быть выражена как в абсолютных величинах, так и в процентах.
     * Сначала указывается опорная точка оценки (значение 5.25d), а далее допустимая погрешность.
     * Качественная оценка результата эквивалентна понятиям "больше" "меньше"
     */
    @Test
    void checkDoubleChain() {
        Model model = new Model(1, 5.255d, "name", true);
        double result = model.getNum();
        assertThat(result).isEqualTo(5.26d, withPrecision(0.006d))
                .isCloseTo(5.25d, withPrecision(0.01d))
                .isCloseTo(5.25d, Percentage.withPercentage(1.0d))
                .isGreaterThan(5.25d)
                .isLessThan(5.26d)
        ;
    }
}