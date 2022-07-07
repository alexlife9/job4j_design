package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Перевернуть связанный список
 *
 * @author Alex_life
 * @version 3.0
 * 1.исправил метод deleteFirst
 * 2.добавил метод revert для переворачивания списка
 * @since 07.07.2022
 */
public class ForwardLinked<T> implements Iterable<T> {

    /**
     * head - ссылка на голову списка (на первый элемент)
     * tail - ссылка на хвост списка (на последний элемент)
     */
    Node<T> head;
    Node<T> tail;

    /**
     * метод add добавляет ноду в конец списка
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
     * метод revert переворачивает односвязный список
     * 1.если в списке есть хотя бы 2 элемента
     * 2.то хвосту присваиваем голову, то есть ссылку на последний элемент переназначаем на первый элемент
     * 3.создаем локальную переменную-ссылку на текущий узел и записываем ее в головную ссылку
     * 4.в головную ссылку в свою очередь записываем null, тк этот элемент теперь будет последним элементом списка
     * 5.теперь будем идти по списку. пока текущий элемент не равен null - сохраняем ссылку на следующий элемент
     * 6.ссылке на следующий элемент текущего элемента присваиваем голову
     * 7.в head в это время находится предыдущий элемент,
     *   то есть current.next ссылался на следующий элемент, а теперь будет ссылаться на предыдущий элемент
     * 8.смещаем все указатели на 1 элемент вперед, то есть в head записываем текущий элемент
     * 9.а в current в свою очередь записываем ссылку на следующий элемент next
     *
     * в итоге в проходе используются 3 ссылки -
     * nextTemp (на следующий элемент), head (на предыдущий элемент), current (на текущий элемент)
     * у текущего элемента меняем указатель чтобы он указываел не на следующий элемент, а на предыдущий
     * и потом двиггаемся по списку меняя значение всех указателей
     *
     * @return true или false
     */
    public boolean revert() {
        if (head != null && head.next != null) {
            tail = head;
            Node<T> current = head.next;
            while (current != null) {
                Node<T> nextTemp = current.next;
                current.next = head;
                head = current;
                current = nextTemp;
            }
        }
        return false;
    }

    /**
     * метод addFirst добавляет элемент в голову списка
     * поскольку нам надо сразу в голову вставлять элемент,
     * то просто ссылаем новую ноду на уже имеющуюся ссылку на головной узел
     */
    public void addFirst(T value) {
            head = new Node<>(value, head);
    }

    /**
     * метод deleteFirst удаляет головную ноду и обнуляет ссылку на нее
     * 1.если ссылка на головную ноду null (отсутствует), то удалять нечего - выбрасываем исключение
     * 2.создаем локальную переменную tempNode и присваиваем ей ссылку на головную ноду
     * 3.создаем локальную переменную tempValue и присваиваем ей значение элемента головной ноды
     * 4.
     * @return
     */
    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<T> tempNode = head;
        T tempValue = head.value;
        head = head.next;
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
         * @param value - элемент ноды
         * @param next - ссылка на следующий узел
         */
        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
