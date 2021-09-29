package ru.job4j.generics;

/**
 * Рассмотрим следующий универсальный класс, который представляет собой узел в односвязном списке
 * @param <T>
 */
public class Node<T> {
    private T data;
    private Node<T> next;

    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }

    public T getData() {
        return data;
    }
    /**
     * Поскольку общий тип T не ограничен, компилятор Java заменяет его на Object:
     * public class Node {
     *     private Object data;
     *     private Node next;
     *
     * Если же мы ограничим тип в универсальном классе следующим образом:
     * public class Node<T extends Comparable<T>>
     *     то компилятор Java заменяет параметр ограниченного типа T первым связанным классом Comparable:
     * public class Node {
     *     private Comparable data;
     *     private Node next;
     */
}
