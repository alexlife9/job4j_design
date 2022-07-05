package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Создать контейнер на базе односвязанного списка
 * Односвязанный список состоит из Nodes. Каждая Нода содержит в себе ссылку на следующий узел.
 *
 * @author Alex_life
 * @version 1.0
 * @since 04.07.2022
 */
public class SimpleLinkedList<E> implements LinkedList<E> {

    private static class Node<E> {
        /**
         * class Node<E> - класс для описания ноды
         * currentElement - поле текущего элемента списка
         * nextNode - поле указателя на следующий элемент
         * prevNode - поле указателя на предыдущий элемент
         * Е - тип элемента
         */
        private E currentElement;

        private Node<E> nextNode;

        private Node<E> prevNode;

        /**
         * конструктор односвязной Ноды
         * @param currentElement
         * @param nextNode
         */
        Node(E currentElement, Node<E> nextNode) {
            this.currentElement = currentElement;
            this.nextNode = nextNode;
        }
    }

    /**
     * nextNode - приватное поле ссылающееся на последнюю ноду
     * prevNode - приватное поле ссылающееся на предыдущую ноду
     * size - кол-во элементов в коллекции
     * modCount - счетчик кол-ва изменений коллекции
     */
    private Node<E> nextNode;

    private Node<E> prevNode;

    private int size = 0;

    private int modCount = 0;

    /**
     * метод add добавляет новую ноду в конец списка
     * @param value значение ноды
     *
     * 1.увеличиваем счетчик модификаций
     * 2.создаем переменную actualNode, которую приравниваем к указателю на последнюю ноду
     * 3.создаем экземпляр ноды newNode с заданным значениям и с размещением в конце списка
     * 4.заменяем ссылку на последнюю ноду на новую созданную ноду
     * 5.если значение текущей ноды null (что говорит о том что список пуст), то записываем ее в начало списка
     * 6.иначе записываем значение новой ноды в конец списка
     * 7.увеличиваем размер списка
     */
    @Override
    public void add(E value) {
        modCount++;
        Node<E> actualNode = nextNode;
        Node<E> newNode = new Node<>(value, actualNode);
        nextNode = newNode;
        if (actualNode == null) {
            prevNode = newNode;
        } else {
            actualNode.nextNode = nextNode;
        }
        size++;
    }

    /**
     * В методах, где используется индекс нужно делать валидацию. Индекс должен находиться в рамках
     * добавленных элементов. Например, вы добавили 3 элемента. Каким может быть индекс? [0, 2].
     * Для проверки индекса используйте метод Objects.checkIndex()
     *
     * Метод get перебирает элементы до указанного индекса и возвращает значение из найденной ноды
     * @param index индекс ноды из которой надо вытащить значение
     * @return возвращает значение из найденной ноды
     */
    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> actualNode = prevNode;
        for (int i = 0; i < index; i++) {
            actualNode = actualNode.nextNode;
        }
        return actualNode.currentElement;
    }


    /**
     * Итератор должен реализовывать fail-fast поведение, т.е. если с момента создания итератора
     * коллекция подверглась структурному изменению, итератор должен кидать ConcurrentModificationException.
     * Это достигается через введение счетчика изменений - modCount. Каждая операция,
     * которая структурно модифицирует коллекцию, должна инкрементировать этот счетчик.
     * В свою очередь, итератор запоминает значение этого счетчика на момент своего создания (expectedModCount),
     * а затем, на каждой итерации, сравнивает сохраненное значение с текущим значением поля modCount,
     * если они отличаются, то генерируется исключение.
     * @return
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> actualNode;
            private int expectedModCount = modCount;
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return actualNode != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return actualNode.currentElement;
            }
        };
    }
}
