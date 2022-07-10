package ru.job4j.iterator;

import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * ListIterator
 * В качестве альтернативы "обычному" итератору, есть ListIterator.
 * Он обладает fail-safe поведением, это значит, что мы можем менять коллекцию по ходу итерирования,
 * но только с помощью самого итератора.
 *
 * @author Alex_life
 * @version 1.0
 * @since 10.07.2022
 */
public class ListUtils {

    /**
     * метод addBefore вставляет элемент перед индексом
     * @param list - коллекция
     * @param index - индекс элемента
     * @param value - элемент
     * @param <T> - тип элементов в коллекции
     */
    public static <T> void addBefore(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (iterator.nextIndex() == index) {
                iterator.add(value);
                break;
            }
            iterator.next();
        }
    }

    /**
     * метод addAfter вставляет элемент после индекса
     * @param list - коллекция
     * @param index - индекс элемента
     * @param value - элемент
     * @param <T> - тип элементов в коллекции
     */
    public static <T> void addAfter(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            iterator.next();
            if (iterator.previousIndex() == index) {
                iterator.add(value);
                break;
            }
            iterator.next();
        }
    }

    /**
     * метод removeIf() удаляет все элементы, которые удовлетворяют предикату
     * 1.создаем элемент и присваиваем ему listIterator нашей коллекции
     * 2.пока указатель hasNext есть в коллекции
     * 3.при выполнении заданной функции предиката (с параметром что следующий элемент существует)
     * 4.производим удаление элемента
     * @param list - коллекция
     * @param filter - условие для удаления элементов
     * @param <T> - тип элементов в коллекции
     */
    public static <T> void removeIf(List<T> list, Predicate<T> filter) {
        ListIterator<T> elem = list.listIterator();
        while (elem.hasNext()) {
            if (filter.test(elem.next())) {
                elem.remove();
            }
        }
    }

    /**
     * метод replaceIf() заменяет все элементы, которые удовлетворяют предикату
     * 1.создаем элемент и присваиваем ему listIterator нашей коллекции
     * 2.пока указатель hasNext есть в коллекции
     * 3.при выполнении заданной функции предиката (с параметром что следующий элемент существует)
     * 4.производим удаление элемента
     * 5.на место удаленного элемента добавляем другой заданный элемент
     * @param list - коллекция
     * @param filter - условие замены элементов
     * @param value - новый элемент на который меняем старый элемент
     * @param <T> - тип элементов в коллекции
     */
    public static <T> void replaceIf(List<T> list, Predicate<T> filter, T value) {
        ListIterator<T> elem = list.listIterator();
        while (elem.hasNext()) {
            if (filter.test(elem.next())) {
                elem.remove();
                elem.add(value);
            }
        }
    }

    /**
     * метод removeAll() удаляет из списка те элементы, которые есть в elements
     * 1.создаем элемент и присваиваем ему listIterator нашей коллекции
     * 2.пока указатель hasNext есть в коллекции и указавает на наш элемент
     * 3.если сравнение следующего элемента нашей коллекции совпадает с элементом из списка элементов на удаление
     * 4.то производим удаление элемента
     *
     * @param list - коллекция
     * @param elements - список элементов на удаление
     * @param <T> - тип элементов в коллекции
     */
    public static <T> void removeAll(List<T> list, List<T> elements) {
        ListIterator<T> elem = list.listIterator();
        while (elem.hasNext()) {
            if (elements.contains(elem.next())) {
                elem.remove();
            }
        }
    }
}
