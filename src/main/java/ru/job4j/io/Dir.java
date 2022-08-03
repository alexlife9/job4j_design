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
 * @version 3.0
 * добавил проверку аргументов
 * @since 03.08.2022
 */
public class Dir {
    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Root folder is null. Usage java -jar dir.jar ROOT_FOLDER.");
        }
        File file = new File(args[0]);
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        System.out.println(String.format("размер диска С : %s Gigabytes", file.getTotalSpace() / 1073741824));
        for (File subfile : file.listFiles()) {
            System.out.println(String.format("размер файла (но не содержимого) для папки: %s составляет: %s",
                    subfile.getName(), file.length()));
        }
    }
}
