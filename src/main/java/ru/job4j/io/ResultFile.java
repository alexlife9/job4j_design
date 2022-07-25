package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * FileOutputStream - запись данных в файл
 *
 * @author Alex_life
 * @version 1.0
 * @since 25.07.2022
 */
public class ResultFile {

/*наглядный вывод таблицы умножения в консоль
    public static int[][] multiple(int size) {
        int[][] rsl = new int[size][size];
        for (int row = 1; row < rsl.length; row++) {
            for (int cell = 1; cell < rsl.length; cell++) {
                rsl[row][cell] = row * cell;
                System.out.print(row * cell + " ");
            }
            System.out.println(" ");
        }
        return rsl;
    }
*/

    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("table.txt")) {
            for (int row = 1; row <= 5; row++) {
                for (int cell = 1; cell <= 5; cell++) {
                    out.write(row * cell);
                    out.write(" ".getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        /* наглядный вывод таблицы умножения в консоль
        System.out.println(multiple(5));
         */
    }
}
