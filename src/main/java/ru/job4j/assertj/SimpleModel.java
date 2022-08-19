package ru.job4j.assertj;

/**
 * assertj - Утверждения с исключениями
 * Библиотека AssertJ позволяет проверять исключения, генерируемые проверяемым классом.
 *
 * Методы класса SimpleModel могут генерировать исключения.
 *
 *Метод getName() выбросит исключение IllegalArgumentException, если поле name не заполнено,
 * а метод setName(String word, int number) - если введены несогласованные данные (длина переменной word
 * не совпадает со значением переменной number).
 * ____Тут стоит обратить внимание на два момента:
 * 1. метод getName() не принимает аргументов при вызове,
 * а метод setName(String word, int number) требует передачи ему аргументов;
 * 2. в первом случае исключение сопровождается информационным сообщением,
 * а во втором случае - нет.
 *
 * Чтобы сократить время при отладке программы при генерации исключений лучше всегда использовать
 * информационные сообщения, с записью информации, которая привела к возникновению этого исключения.
 * Указывайте в них параметры, которые были при проверке признаны негодными для работы.
 *
 * @author Alex_life
 * @version 1.0
 * @since 20.08.2022
 */
public class SimpleModel {
    private String name = "";

    public String getName() {
        if (name.length() == 0) {
            throw new IllegalArgumentException();
        }
        return name;
    }

    public void setName(String word, int number) {
        if (word.length() != number) {
            throw new IllegalArgumentException(
                    String.format("this word: %s has length not equal to : %s", word, number)
            );
        }
        this.name = word;
    }
}
