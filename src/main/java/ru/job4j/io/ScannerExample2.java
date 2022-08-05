package ru.job4j.io;


import java.io.ByteArrayInputStream;
import java.util.Scanner;
/**
 * Scanner
 *
 * Класс ScannerExample2 из потока данных вычленяет адреса почты, которые разделены между собой “, ”
 * Здесь в качестве источника указали одну из реализаций InputStream – ByteArrayInputStream.
 * В качестве разделителя с помощью метода userDelimiter() мы указали нужный разделитель.
 * В данном случае можно было воспользоваться методом String.split(), но когда дело доходит до чтения файлов,
 * то Scanner позволяет использовать преимущества буферизированных потоков и возможности по разбиению на токены.
 *
 * @author Alex_life
 * @version 1.0
 * @since 05.08.2022
 */
public class ScannerExample2 {
    public static void main(String[] args) {
        var data = "empl1@mail.ru, empl2@mail.ru, empl3@mail.ru";
        var scanner = new Scanner(new ByteArrayInputStream(data.getBytes()))
                .useDelimiter(", ");
        while (scanner.hasNext()) {
            System.out.println(scanner.next());
        }
    }
}
