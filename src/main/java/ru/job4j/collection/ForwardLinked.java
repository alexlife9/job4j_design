package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Удалить head в односвязном списке
 *
 * @author Alex_life
 * @version 1.0
 * @since 06.07.2022
 */
public class ForwardLinked<T> implements Iterable<T> {

    /**
     * приватное поле указывающее на голову списка
     */
    private Node<T> head;

    /**
     * метод add добавляет ноду в список
     * 1.создаем локальную переменную node и присваиваем ей ноду с нужным значением и со ссылкой null на следющую ноду
     * 2.если головная нода пустая (значит и список пустой), то присваиваем ей значение новой созданной ноды
     * 3.создаем локальную переменную tail последнего узла и присваиваем ей головной узел
     * 4.перебираем список пока следующий узел не окажется равным null
     * 5.после этого присваиваем последний узел хвосту списка
     * @param value элемент ноды
     */
    public void add(T value) {
        Node<T> node = new Node<>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    /**
     * метод deleteFirst удаляет головную ноду и обнуляет ссылку на нее
     * 1.создаем локальную переменную node и присваиваем ей ссылку на головную ноду
     * 2.создаем локальную переменную tempValue и присваиваем ей значение головной ноды
     * 3.если ссылка на головную ноду null (отсутствует), то удалять нечего - выбрасываем исключение
     * 4.иначе обнуляем ссылку на головную ноду и ее содержимое
     * @return
     */
    public T deleteFirst() {
        Node<T> tempNode = head;
        T tempValue = head.value;
        if (head == null) {
            throw new NoSuchElementException();
        }
        head = tempNode.next;
        tempNode.next = null;
        tempNode.value = null;
        return tempValue;
    }

    /**
     * итератор для перебора элементов списка
     * @return итератор
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            /**
             * поле node указывает на текущий узел, на котором находится итератор
             */
            Node<T> node = head;

            /**
             * метод hasNext проверяет существует ли следующий элемент в списке
             * @return true если существует
             */
            @Override
            public boolean hasNext() {
                return node != null;
            }

            /**
             * метод next перемещает указатель по списку
             * если следующего элемента нет. то выбрасываем исключение
             * иначе сохраняем в локальную переменную текущее значение ноды
             * перемещаем указатель node на следующий узел
             * @return возвращаем ранее сохраненное значение
             */
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    /**
     * класс описывающий ноду
     * @param <T> тип элемента
     */
    private static class Node<T> {

        /**
         * T value - поле элемента узла ноды
         * Node<T> next - поле следующего узла ноды
         */
        T value;
        Node<T> next;

        /**
         * конструктор ноды
         * @param value - значение элемента
         * @param next - ссылка на следующий узел
         */
        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
