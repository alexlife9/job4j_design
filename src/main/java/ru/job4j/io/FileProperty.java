package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

/**
 * Поиск дубликатов
 *
 * Программа, которая ищет дубликаты файлов в папках и подпапках.
 * Дубликаты – это два файла с одинаковым именем и размером.
 * Результат нужно выводить на консоль.
 *
 * Класс FileProperty описыавет модель данных файла, которая описывается двумя свойствами: размером и именем.
 *
 * @author Alex_life
 * @version 1.0
 * @since 03.08.2022
 */
public class FileProperty {

    private long size;

    private String name;

    public FileProperty(long size, String name) {
        this.size = size;
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FileProperty that = (FileProperty) o;
        return size == that.size && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, name);
    }

    public static void main(String[] args)  throws IOException {
        Files.walkFileTree(Path.of("./"), new DuplicatesVisitor());
    }
}