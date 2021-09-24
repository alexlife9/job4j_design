package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * Создать итератор возвращающий только четные цифры.
 *
 * @author Alex_life
 * @version 3.0
 */
public class EvenNumbersIterator implements Iterator<Integer> {

    private int[] data;
    private int index;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    /**
     * вынес в отдельный метод поиска четных чисел
     * @return четные или нет
     */
    public boolean giveEven() {
        while (index < data.length) {
            if (data[index] % 2 == 0) {
                return true;
            }
            index++;
        }
        return false;
    }
    /**
     * метод hasNext() устанавливает условия, при которых возможно получение элемента контрейнера в next()
     * @return вызываем отдельный метод проверки на четность
     */
    @Override
    public boolean hasNext() {
        return giveEven();
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
        return data[index++];
    }
}