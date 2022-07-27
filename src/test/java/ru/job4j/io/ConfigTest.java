package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

/**
 *  Читаем файл конфигурации
 *
 * Тесты содержат следующие проверки:
 * --на чтение файла с комментариями и пустыми строками
 * --на чтение файла с нарушением шаблона ключ=значение (например =значение, или ключ=,
 * или строка без символа "=" ключзначение, или строка без ключа и без значения, но с символом =)
 * в этом случае нужно ожидать исключение IllegalArgumentException
 * --Строка вида "ключ=значение=1" или "ключ=значение=" должна быть обработана и распознана
 * как ключ "ключ" и значение "значение=1" или "значение=" соответственно.
 *
 * @author Alex_life
 * @version 3.0
 * @since 27.07.2022
 */

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
    }

    @Test
    void whenPairWithCommentAndEmptyLine() {
        String path = "./data/pair_with_comment_and_empty_line.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name2")).isEqualTo("Value2");
    }

    @Test
    void whenPairWithPatternViolationAndEmptyLineAndComment() {
        String path = "./data/pair_with_pattern_violation_and_empty_line_and_comment.properties";
        Config config = new Config(path);
        config.load();

        assertThat(config.value("name3")).isEqualTo("Value3");
    }

    @Test
    void whenPairWithPatternViolation() {
        String path = "./data/pair_with_pattern_violation.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name4")).isEqualTo("Value4");
    }
}