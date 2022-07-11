package ru.job4j.collection;

import java.util.Iterator;
import java.util.Objects;

/**
 * Реализация коллекции Set на массиве
 * Коллекция не должна хранить дубликаты. Для хранения использовать SimpleArrayList
 *
 * @author Alex_life
 * @version 1.0
 * @since 11.07.2022
 */
public class SimpleSet<T> implements Set<T> {

    /**
     * создаем коллекцию set с емкостью 100 элементов
     */
    private final SimpleArrayList<T> set = new SimpleArrayList<>(100);

    /**
     * метод add добавляет элемент в коллекцию Set. Допускаем только уникальные элементы
     * 1.проверяем уникальный ли добавляемый элемент с помощью метода contains, если нет - отдаем false и пишем в rsl
     * 2.если true, то дабавляем наш элемент
     * @param value элемент
     * @return возвращаем false если элемент уже имеется в коллекции
     */
    @Override
    public boolean add(T value) {
        boolean rsl = !contains(value);
        if (rsl) {
            set.add(value);
        }
        return rsl;
    }

    /**
     * метод contains проверяет уникальное ли значение в нашей коллекции Set
     * 1.1создаем переменную rsl и говорим что она false
     * 2.проходим циклом по всей коллекции Set
     * 3.если результат сравнения элементов совпадает (значит элемент уже есть), то
     * 4.возвращем true и прерываем сравнение
     * @param value проверяемое значение
     * @return false - если элемент уникальный, то есть еще не существует в коллекции
     */
    @Override
    public boolean contains(T value) {
        boolean rsl = false;
        for (T elements : set) {
            if (Objects.equals(value, elements)) {
                rsl = true;
                break;
            }
        }
        return rsl;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}
