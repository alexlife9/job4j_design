package ru.job4j.tree;

import java.util.*;
/**
 * Создание элементарной структуры дерева
 *
 * @author Alex_life
 * @version 1.0
 * @since 22.07.2022
 */
public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    /**
     * Метод add находит узел по значению parent и добавляет в него дочерний узел со значением child.
     * Метод проверяет, что значения child еще нет в дереве, а parent есть. Если child есть, то метод вернет false
     * дочерний узел будет добавлен только тогда, когда он еще не присутствует в дереве.
     * @param parent - родительский узел
     * @param child - дочерний узел
     * @return true в случае успешного добавления нового дочернего узла
     *
     * 1. создаем переменную nodeParent, присваиваем ей текущее значение родительского узла
     * 2. создаем новый узел (который хотим добавить)
     * 3. если узел child равен null И узел parent НЕ равен null
     * 4. то записываем и добавляем в переменную nodeParent наш новый дочерний узел nodeChild
     */
    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        Node<E> nodeParent = findBy(parent).get();
        Node<E> nodeChild = new Node<>(child);

        if (!findBy(child).isPresent() && findBy(parent).isPresent()) {
            nodeParent.children.add(nodeChild);
        }
        return rsl;
    }

    /**
     * метод findBy использует алгоритм обхода дерева в ширину - breadth first search.
     * @param value значение узла
     * @return найденное значение узла
     */
    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.value.equals(value)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SimpleTree<?> that = (SimpleTree<?>) o;
        return Objects.equals(root, that.root);
    }

    @Override
    public int hashCode() {
        return Objects.hash(root);
    }
}