package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * BufferedReader - чтение файла через буферизированный поток
 *
 * @author Alex_life
 * @version 1.0
 * @since 25.07.2022
 */
public class BufferedReadFile {
    public static void main(String[] args) {
        try (BufferedReader in = new BufferedReader(new FileReader("inputBuf.txt"))) {
            in.lines().forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
