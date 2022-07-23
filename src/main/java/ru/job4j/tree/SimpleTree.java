package ru.job4j.tree;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Optional;
import java.util.Queue;
import java.util.function.Predicate;

/**
 * Создание элементарной структуры дерева
 *
 * @author Alex_life
 * @version 5.0
 * вынес в метод findByPredicate общую логику методов isBinary и findBy
 * @since 23.07.2022
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

        if (findBy(child).isEmpty() && findBy(parent).isPresent()) {
            nodeParent.children.add(nodeChild);
            rsl = true;
        }
        return rsl;
    }

    /**
     * в этот метод вынесли алгоритм поиска заданного узла
     * @param condition - параметр условия поиска
     * @return возвращаем искомый узел если условие выполнено
     *
     * 1. наш узел rsl изначально пустой Optional.empty()
     * 2. создаем новый связный список
     * 3. голова списка начинается с root
     * 4. пока наш список не пустой идем по циклу и
     * 5. извлекаем из списка узлы и записываем их в переменую el
     * 6. подставляем любое условие condition и проверем верно ли оно для найденного элемента
     * 7. если условие выполняется, то записываем этот узел в список rsl и останавливаем проверку
     * 8. после чего записываем все найденные узлы в постоянный список children и начинаем проверку while снова
     */
    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (condition.test(el)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }

    /**
     * метод проверяет количество дочерних узлов. если их больше 2, то дерево не бинарное
     * @return true если узлов не больше 2
     */
    @Override
    public boolean isBinary() {
        Predicate<Node<E>> predicate = E -> E.children.size() > 2;
        return findByPredicate(predicate).isEmpty();
    }

    /**
     * метод findBy использует алгоритм обхода дерева в ширину - breadth first search.
     * @param value значение узла
     * @return найденное значение узла
     */
    @Override
    public Optional<Node<E>> findBy(E value) {
        Predicate<Node<E>> predicate = E -> E.value.equals(value);
        return findByPredicate(predicate);
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