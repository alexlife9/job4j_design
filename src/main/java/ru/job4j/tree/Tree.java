package ru.job4j.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Создание элементарной структуры дерева
 *
 * @author Alex_life
 * @version 1.0
 * @since 19.07.2022
 */
public interface Tree<E> {

    boolean add(E parent, E child);

    boolean isBinary();

    Optional<Node<E>> findBy(E value);

    /**
     * Класс Node описывает узел дерева. Узел содержит хранимое значение и ссылки на дочерние узлы.
     * @param <E>
     */
    class Node<E> {
        final E value;
        final List<Node<E>> children = new ArrayList<>();

        public Node(E value) {
            this.value = value;
        }
    }
}
