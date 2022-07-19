package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Реализация собственной структуры данных - HashMap
 *
 * @author Alex_life
 * @version 6.0
 * @since 19.07.2022
 */
public class SimpleMap<K, V> implements Map<K, V> {

    /**
     * LOAD_FACTOR - коэффициент загрузки хеш-таблицы
     * capacity - емкость хеш-таблицы
     * size - кол-во пар "ключ-значение" в хеш-таблице
     * modCount - счетчик изменений в хеш-таблице
     */
    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int size = 0;

    private int modCount = 0;

    /**
     * хеш-таблица table, реализованная на основе массива, для хранения пар «ключ-значение» в виде узлов.
     * Здесь хранятся наши Node
     */
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    /**
     * метод put вставляет новые элементы в хеш-таблицу. В случае занятого бакета места возвращает false.
     * 1.проверяем нужно ли увеличить емкость таблицы
     * 2.если бакет куда мы хотим вставить элемент равен null, то значит он свободен и можно вставлять
     * 3.увеличиваем счетчик элементов и счетчик кол-ва изменений
     * 4.обязательно высвращаем успех операции
     * 5.иначе false
     *
     * @param key - ключ элемента
     * @param value - значение элемента
     * @return true если вставка прошла успешно
     */
    @Override
    public boolean put(K key, V value) {
        expand();
        boolean rsl;
        int i = 0;
        if (key != null) {
            i = iB(key);
        }
        if (table[i] == null) {
            table[i] = new MapEntry<>(key, value);
            rsl = true;
        } else {
            rsl = false;
        }
        size++;
        modCount++;
        return rsl;
    }

    /**
     * метод hash вычисляет хэш ключа бакета
     * @param hashCode на вход подаем хэшкод уже вычисленный внутренней реализацией
     * @return хэш реализованный нашим собственным способом. хэш-формула может быть разная
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
     * индекс бакета с искомым ключом
     * @param key ключ
     * @return вычесленный индекс для бакета
     */
    public int iB(K key) {
        return indexFor(hash(key.hashCode()));
    }

    /**
     * метод expand увеличивает capacity вдвое
     * 1.если кол-во объектов больше или равно предельному значению капасити, то увеличиваем емкость таблицы вдвое
     * 2.создаем таблицу tableNew с текущей емкостью
     * 3.для каждого элемента в старой таблице (если элемент не равен null)
     * вычислем индекс нового бакета и кладем его на новое место
     * 4.после того как прошли все элементы сохраняем новую таблицу под старым именем table
     */
    private void expand() {
        if (size / capacity >= (int) LOAD_FACTOR) {
            capacity = capacity * 2;
            MapEntry<K, V>[] tableNew = new MapEntry[capacity];
            for (int i = 0; i < table.length; i++) {
                if (!Objects.isNull(i) && table[i] != null) {
                    tableNew[indexFor(hash(table[i].key.hashCode()))] = table[i];
                }
            }
            modCount++;
            table = tableNew;
        }
    }

    /**
     * метод get для доступа к значению элемента по ключу
     * 1.если бакет куда мы хотим вставить элемент НЕ равен null И ключ этого бакета эквивалентен искомому ключю,
     * 2.то возвращаем значение этого найденного элемента в найденном бакете
     * 3.если не нашли - возвращаем null
     * @param key ключ ноды
     * @return возвращает найденное значение либо null если не нашли
     */
    @Override
    public V get(K key) {
        V rsl = null;
        int i = 0;
        if (key != null) {
            i = iB(key);
        }
        if (table[i] != null && table[i].key.hashCode() == key.hashCode() && Objects.equals(table[i].key, key)) {
            rsl = table[i].value;
        }
        return rsl;
    }

    /**
     * метод remove удаляет ноду по ключу
     * рабоатет аналогично методу get
     * только вместо возврата значения мы обнуляем найденный бакет
     * и уменьшаем счетчик элементов в случае удачи и увеличиваем счетчик изменений
     * @param key ключ
     * @return true если успешно
     */
    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        int i = 0;
        if (key != null) {
            i = iB(key);
        }
        if (table[i] != null && table[i].key.hashCode() == key.hashCode() && Objects.equals(table[i].key, key)) {
            table[i] = null;
            size--;
            modCount++;
            rsl = true;
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
             * возвращаем true если счетчик указателя меньше емкости таблицы, значит след-й элемент есть
             */
            private int cursor = 0;
            private final int expectedModCount = modCount;
            @Override
            public boolean hasNext() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                while (table.length != cursor && table[cursor] == null) {
                    cursor++;
                }
                return cursor < table.length - 1;
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
