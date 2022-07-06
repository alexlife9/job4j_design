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
 * @version 2.0
 * @since 06.07.2022
 */
public class SimpleLinkedList<E> implements LinkedList<E> {

    private static class Node<E> {
        /**
         * class Node<E> - класс для описания ноды
         * currentElement - поле хранящегося в узле значения
         * nextNode - поле указателя на следующий элемент
         * Е - тип элемента
         */
        private E currentElement;

        private Node<E> nextNode;

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
     * last - приватное поле ссылающееся на последнюю ноду
     * head - приватное поле указывающее на голову списка
     * size - кол-во элементов в коллекции
     * modCount - счетчик кол-ва изменений коллекции
     */
    private Node<E> last;

    private Node<E> head;

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
        Node<E> actualNode = last;
        Node<E> newNode = new Node<>(value, null);
        last = newNode;
        if (actualNode == null) {
            head = newNode;
        } else {
            actualNode.nextNode = last;
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
        Node<E> actualNode = head;
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
     * @return возвращает итератор
     *
     * метод hasNext проверяет существует ли следующий элемент в списке
     * actualNode - поле для местоположения указателя на текущую ноду
     * expectedModCount - поле для контроля изменений в коллекции во время работы итератора
     * 1.ссылаем actualNode на первую ноду
     * 2.ссылаем expectedModCount на счетчик изменений
     * 3.если счетчик изменений не совпадает с контрольным счетчиком - бросаем ошибку
     * 4.иначе возвращаем данные о том что указатель не равен null, значит нода под ним уже занята = элемент есть
     *
     * метод next перемещает указатель по списку
     * 1.если следующего элемента нет. то выбрасываем исключение
     * 2.иначе - сохраняем в локальную переменную значение текущей ноды
     * 3.переместить указатель actualNode на следующий узел
     * 4.возвращаем ранее сохраненное значение
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> actualNode = head;
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
                E saveNode = actualNode.currentElement;
                actualNode = actualNode.nextNode;
                return saveNode;
            }
        };
    }
}
