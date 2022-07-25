package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * FileInputStream - чтение данных из входящего потока
 *
 * @author Alex_life
 * @version 1.0
 * @since 25.07.2022
 */
public class ReadFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("input.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            String[] lines = text.toString().split(System.lineSeparator());
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}