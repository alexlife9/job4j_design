package ru.job4j.collection;

/**
 * Очередь на двух стеках
 * Термин FIFO - first input first output. Первый пришел, первый ушел.
 *
 * @author Alex_life
 * @version 1.0
 * @since 09.07.2022
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
     * @return
     */
    public T pollFirst() {
        if (in.isEmpty() && out.isEmpty()){
            while (!in.isEmpty()) {
                out.push(in.pop());
            }
        }
        return out.pop();
    }

    /**
     * Метод push(T value) помещает значение в конец списка
     * @param value
     */
    public void push(T value) {
        in.push(value);
    }
}
