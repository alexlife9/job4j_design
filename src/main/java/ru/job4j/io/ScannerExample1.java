package ru.job4j.io;

import java.io.CharArrayReader;
import java.util.Scanner;

/**
 * Scanner
 *
 * Класс ScannerExample1 из потока данных вычленяет только числа
 * В качестве источника указали одну из реализаций Reader - CharArrayReader.
 * Данная реализация позволяет читать данные из массива символов, т.е. из временной памяти.
 *
 * Важно! В примере шаблон разделителя не указан, поэтому используется тот, что по умолчанию,
 * а именно символы перехода на новую строку и знак *
 *
 * @author Alex_life
 * @version 1.0
 * @since 05.08.2022
 */
public class ScannerExample1 {
    public static void main(String[] args) {
        var ls = System.lineSeparator();
        var data = String.join(ls,
                "1 2 3",
                         "4 5 6",
                         "7 8 9"
        );
        var scanner = new Scanner(new CharArrayReader(data.toCharArray()));
        while (scanner.hasNextInt()) {
            System.out.print(scanner.nextInt());
            System.out.print("*");
        }
    }
}
