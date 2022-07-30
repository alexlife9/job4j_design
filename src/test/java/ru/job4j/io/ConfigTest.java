package ru.job4j.io;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
 * @version 4.0
 * @since 30.07.2022
 */

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment_and_errors.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
    }

    @Test
    void whenPairWithEmptyLine() {
        String path = "./data/pair_with_empty_line.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name3")).isEqualTo("Value3");
    }

    @Test
    void whenPairWithComment() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name2")).isEqualTo("Value2");
    }


    @Test
    void whenPairWithStartLineSignEquals() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            String path = "./data/pair_with_start_line_sign_equals.properties";
            Config config = new Config(path);
            config.load();
        });
    }

    @Test
    void whenOnlySignEquals() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            String path = "./data/pair_with_only_sign_equals.properties";
            Config config = new Config(path);
            config.load();
            assertThat(config.value("name5")).isEqualTo("Value5");
        });
    }

    @Test
    void whenPairWithoutSignEquals() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            String path = "./data/pair_without_sign_equals.properties";
            Config config = new Config(path);
            config.load();
            assertThat(config.value("name6")).isEqualTo("Value6");
        });
    }

    @Test
    void whenOnlyKeyWithoutValue() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            String path = "./data/pair_with_key_and_without_value.properties";
            Config config = new Config(path);
            config.load();
            assertThat(config.value("name7")).isEqualTo("Value7");
        });
    }
}