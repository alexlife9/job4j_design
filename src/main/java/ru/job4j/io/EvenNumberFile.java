package ru.job4j.io;

import java.io.FileInputStream;

/**
 * FileInputStream - чтение данных из входящего потока
 *
 * @author Alex_life
 * @version 1.0
 * @since 25.07.2022
 */
public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            String[] lines = text.toString().split(System.lineSeparator());
            for (String line : lines) {
                if (Integer.parseInt(line) % 2 == 0) {
                    System.out.println(line + " - четное число");
                } else {
                    System.out.println(line + " - нечетное число");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
