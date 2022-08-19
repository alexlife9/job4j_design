package ru.job4j.iterator;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * @author Alex_life
 * @version 1.0
 * @since 20.08.2022
 */
class ArrayItTest {
    @Test
    void whenMultiCallHasNextThenTrue() {
        /* Arrange - создание объекта и заполнение его данными */
        ArrayIt it = new ArrayIt(
                new int[] {1, 2, 3}
        );
        /* Act - выполнение действия и сохранение результата */
        boolean rsl = it.hasNext();
        /* Assert - оценка соответствия результата ожидаемому исходу */
        assertThat(rsl).isTrue();
        /* этапы Act и Assert могут быть размещены в одной строке кода */
        assertThat(it.hasNext()).isTrue();
    }

    @Test
    void whenReadSequence() {
        ArrayIt it = new ArrayIt(
                new int[] {1, 2, 3}
        );
        assertThat(it.next()).isEqualTo(1);
        assertThat(it.next()).isEqualTo(2);
        assertThat(it.next()).isEqualTo(3);
    }
}