package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * Создать итератор возвращающий только четные цифры.
 *
 * @author Alex_life
 * @version 1.0
 */
public class EvenNumbersIterator implements Iterator<Integer> {

    private int[] data;
    private int index;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    /**
     * метод hasNext() устанавливает условия, при которых возможно получение элемента контрейнера в next()
     * @return пока итоговый массив меньше длины начального массива
     */
    @Override
    public boolean hasNext() {
        for (int i = 0; i < data.length; i++) {
            if (data[data.length - 1 - i] % 2 == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * метод next() отвечает за получение элемента из контейнера, его итерацию или исключение
     * @return массив с четными значениями (не с индексами чисел!)
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        for (int i = 0; i < data.length; i++) {
            if (data[data.length - 1 - i] % 2 == 0) {
                return index += data[i];
            }
        }
        return data[index++];
    }
}