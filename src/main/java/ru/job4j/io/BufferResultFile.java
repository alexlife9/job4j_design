package ru.job4j.io;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * BufferedOutputStream - запись в файл через буферизированный поток
 *
 * Исходный поток ввода - это файл FileOutputStream. Он записывает данные по байтам.
 * Блокирует всю программу, пока запись не закончится. Это плохо.
 *
 * Первая обертка - это BufferedOutputStream. Это буфер, который собирает переданные в него байты.
 * Аккумулирует их и постепенно отдает их в FileOutputStream.
 * В этом случае программа не блокируется до тех пока в буфере есть место.
 *
 * Вторая обертка над буфером - это PrintWriter. Мы знаем, что будем записывать текст.
 * В Java есть удобное АПИ для этого, например, PrintWriter поддерживает метод println() для записи данных
 * с последующим переходом на новую строку.
 * Запись можно производить целыми строками.
 *
 * @author Alex_life
 * @version 1.0
 * @since 26.07.2022
 */
public class BufferResultFile {
    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream("resultBuf.txt")
                ))) {
            out.println("Hello, world!");

            /**
             * Форматированный вывод
             * Так же PrintWriter (также как и PrintStream) поддерживают форматированный вывод
             * Например, данный код выведет "Hello" и следующий за ним символ перевода строки: out.println("Hello");
             * Если нужно подставлять переменные, то можно использовать следующий метод:
             * out.printf("%s%n", "Some string");
             * out.printf("%d%n", 10);
             * out.printf("%f%n", 1.5f);
             * Спец. символ %n обозначает переход на новую строку и является укороченной версией такого кода:
             * out.printf("%s", System.lineSeparator())
             *
             * Форматированный вывод нужно использовать вместо конкатенации строк.
             * Например, у нас есть 5 строк по 10 символов. Если вы делаете конкатенацию.
             * Чтобы сделать ее нужно выполнить 20 + 30 + 40 + 50 = 140 шагов, если ваш алгоритм делает 1000 шагов,
             * то с конкатенацией он работает намного медленнее, не смотря на то, что компилятор делает преобразования.
             */
            out.printf("%s%n", "Some string");
            out.printf("%d%n", 10);
            out.printf("%f%n", 1.5f);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
