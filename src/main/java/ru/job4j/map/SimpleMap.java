package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Реализация собственной структуры данных - HashMap
 *
 * @author Alex_life
 * @version 2.0
 * @since 17.07.2022
 */
public class SimpleMap<K, V> implements Map<K, V> {

    /**
     * LOAD_FACTOR - коэффициент загрузки хеш-таблицы
     * capacity - емкость хеш-таблицы
     * size - кол-во пар "ключ-значение" в хеш-таблице
     * threshold - предельное количество элементов, при достижении которого размер хэш-таблицы увеличивается вдвое
     * modCount - счетчик изменений в хеш-таблице
     */
    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int size = 0;

    private int threshold = (int) (capacity * LOAD_FACTOR);

    private int modCount = 0;

    /**
     * хеш-таблица table, реализованная на основе массива, для хранения пар «ключ-значение» в виде узлов.
     * Здесь хранятся наши Node
     */
    private final MapEntry<K, V>[] table = new MapEntry[capacity];

    /**
     * метод put вставляет новые элементы в хеш-таблицу. В случае отсутствия места возвращает false.
     *
     * @param key - ключ элемента
     * @param value - значение элемента
     * @return true если вставка прошла успешно
     */
    @Override
    public boolean put(K key, V value) {
        expand();
        boolean rsl = false;
        int i = indexFor(hash(key.hashCode()));
        if (table[i] == null) {
            table[i] = new MapEntry<>(key, value);
            size++;
            modCount++;
            rsl = true;
        }
        return rsl;
    }

    /**
     * метод hash вычисляет хэш ключа бакета
     * @param hashCode на вход подаем хэшкод уже вычисленный внутренней реализацией
     * @return хэш
     */
    private int hash(int hashCode) {
        return hashCode ^ hashCode >>> 16;
    }

    /**
     * метод indexFor для вычисления индекса бакета
     * @param hash хэш элемента
     * @return возвращает индекс бакета
     */
    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    /**
     * метод expand увеличивает capacity вдвое
     * если кол-во объектов больше или равно предельному значению капасити, то увеличиваем емкость вдвое
     */
    private void expand() {
        if (size >= threshold) {
            capacity = capacity * 2;
        }
    }

    /**
     * метод get для доступа к значению элемента по ключу
     * @param key ключ ноды
     * @return возвращает найденное значение
     */
    @Override
    public V get(K key) {
        int i = indexFor(hash(key.hashCode()));
        if (key == null) {
            return null;
        }
        return table[i].value;
    }

    /**
     * метод remove удаляет ноду по ключу
     * @param key ключ
     * @return true если успешно
     */
    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        int i = indexFor(hash(key.hashCode()));
        if (key == null) {
            return false;
        } else {
            table[i] = null;
            size--;
            modCount++;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            /**
             * cursor - указатель итератора
             * expectedModCount - сохраняем значение счетчика изменений на момент запуска итератора
             * если счетчики не равны (значит наша хэштаблица изменена), то выбрасываем исключение
             * Это называется fail-fast поведением
             *
             * увеличиваем счетчик указателя по бакетам, пока они равны null И пока есть след-й элемент
             * возвращаем true если счетчик указателя меньше кол-ва непустых бакетов, значит след-й элемент есть
             */
            private int cursor = 0;
            private final int expectedModCount = modCount;
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (table[cursor] == null && cursor < size) {
                    cursor++;
                }
                return cursor < size;
            }

            /**
             * метод next возвращает ключ элемента, если бакет под указателем не пустой
             * @return возвращает ключ найденного под курсором элемента и сдвигает курсор на следующую ячейку
             */
            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[cursor++].key;
            }
        };
    }

    /**
     * класс описывающий Ноду
     * в каждом узле есть:
     * hash текущего элемента
     * @param <K> ключ элемента
     * @param <V> значение элемента
     * next - ссылка на следющую ноду. Если след ноды нет, то next = null
     */
    private static class MapEntry<K, V> {

        int hash;
        K key;
        V value;
        MapEntry<K, V> next;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }

}
