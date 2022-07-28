package ru.job4j.io;

import java.io.File;

/**
 * Изучение java.io.File
 *
 * Главным элементом файловой системы является объект java.io.File.
 * File может быть и текстовым документом и директорией.
 *
 * Класс Dir проверяет, что директория projects - это директория и выводит ее содержимое
 * метод exists проверяет что файл существует.
 * метод isDirectory проверяет что этот файл является директорией
 * с помощью цикла выводим всё содержимое директории
 *
 * @author Alex_life
 * @version 1.0
 * @since 29.07.2022
 */
public class Dir {
    public static void main(String[] args) {
        File file = new File("c:\\projects");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        System.out.println(String.format("размер диска С : %s", file.getTotalSpace() / 1073741824 + " Gigabytes"));
        for (File subfile : file.listFiles()) {
            System.out.println(subfile.getName() + " - размер файла (но не содержимого): " + file.length() + " Byte");
        }
    }
}
