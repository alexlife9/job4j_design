package ru.job4j.collection;

import java.util.NoSuchElementException;

/**
 * Очередь на двух стеках
 * Термин FIFO - first input first output. Первый пришел, первый ушел.
 *
 * @author Alex_life
 * @version 3.0
 * 1.добавил проверку что на пустоту исходящего стека
 * @since 10.07.2022
 */
public class SimpleQueue<T> {
    /**
     * in - первый стэк для входящей очереди
     * out - второй стэк для выходящей очереди
     */
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    /**
     * Метод pollFirst() возвращает ПЕРВОЕ значение и удаляет его из коллекции.
     * 1.если оба стека пустые, то выбрасываем исключение
     * 2.если выходящий стек пустой, то  пока входящий стек не пустой -
     * во второй стек добавляем значение элемента из первого и сразу удаляем его из первого стека
     * @return возвращаем первый элемент во втором стеке
     */
    public T pollFirst() {
        if (in.isEmpty() && out.isEmpty()) {
            throw new NoSuchElementException();
        }
        if (out.isEmpty()) {
            while (!in.isEmpty()) {
                out.push(in.pop());
            }
        }
        return out.pop();
    }

    /**
     * Метод push(T value) помещает значение в конец списка
     * @param value значение элемента
     */
    public void push(T value) {
        in.push(value);
    }
}
