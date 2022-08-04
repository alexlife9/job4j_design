package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

/**
 * Именованные аргументы
 *
 * @author Alex_life
 * @version 1.0
 * @since 03.08.2022
 */
class ArgsNameTest {
    @Test
    void whenGetFirst() {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        assertThat(jvm.get("Xmx")).isEqualTo("512");
    }

    @Test
    void whenGetFirstReorder() {
        ArgsName jvm = ArgsName.of(new String[] {"-encoding=UTF-8", "-Xmx=512"});
        assertThat(jvm.get("Xmx")).isEqualTo("512");
    }

    @Test
    void whenGetFirstReorder2() {
        ArgsName jvm = ArgsName.of(new String[] {"-encoding=UTF-8", "-Xmx=512"});
        assertThat(jvm.get("encoding")).isEqualTo("UTF-8");
    }

    @Test
    void whenMultipleEqualsSymbol() {
        ArgsName jvm = ArgsName.of(new String[] {"-request=?msg=Exit=", "-Xmx=512"});
        assertThat(jvm.get("request")).isEqualTo("?msg=Exit=");
    }

    @Test
    void whenGetNotExist() {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        assertThatThrownBy(() -> jvm.get("Xms")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenWrongSomeArgument() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{}))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenMissingKey() {
        assertThatThrownBy(() -> ArgsName.of(new String[] {"-=512"}))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenMissingEqualSign() {
        assertThatThrownBy(() -> ArgsName.of(new String[] {"-Xmx512"}))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenMissingDashSign() {
        assertThatThrownBy(() -> ArgsName.of(new String[] {"Xmx=512"}))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
