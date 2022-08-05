package ru.job4j.io;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Scanner;

/**
 * Scanner
 *
 * Класс ScannerExample3 читает числа в шестнадцатеричном виде и выводит их в десятичном виде.
 * В качестве источника данных указываем временный файл, который создаем и в который записываем предварительно.
 * Метод useRadix() указывает в какой системе счисления находятся входные числа.
 * Важно! Если Scanner работает с внешними источниками его нужно использовать с try-with-resources.
 *
 * @author Alex_life
 * @version 1.0
 * @since 05.08.2022
 */
public class ScannerExample3 {
    public static void main(String[] args) throws Exception {
        var data = "A 1B FF 110";
        var file = File.createTempFile("data", null);
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file))) {
            out.write(data.getBytes());
        }
        try (var scanner = new Scanner(file).useRadix(16)) {
            while (scanner.hasNextInt()) {
                System.out.print(scanner.nextInt());
                System.out.print(" ");
            }
        }
    }
}
