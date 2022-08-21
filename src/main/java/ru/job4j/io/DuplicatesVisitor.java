package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.Set;

/**
 * Поиск дубликатов
 *
 * SimpleFileVisitor для быстрого обхода директории без переопределения всех методов FileVisitor
 *
 * SimpleFileVisitor уже реализует FileVisitor,
 * переопределяя все методы только с указанием на дальнейший обход CONTINUE.
 *
 * Это означает, что унаследовавшись от него мы можем переопределить только нужный нам метод.
 * Например, для выхода всех файлов в консоль мы можем написать такой короткий код: в классе DuplicatesVisitor
 *
 * @author Alex_life
 * @version 3.0
 * @since 03.08.2022
 */
public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    Set<FileProperty> duplicateFiles = new HashSet<>();
    /**
     * в методе visitFile реализована логика поиска дубликатов
     * метод visitFile обходит весь путь от указанного старта до корней папок
     *
     * 1.В коллекции Set невозможно содержать дубли
     * 2.поэтому создаем множество duplicateFiles
     * 3.и проверяем - если элемент не смогли добавить, значит он уже есть в коллекции, значит он дубль
     * 4.выводим имя файла-дубликата в консоль
     *
     * @param file - текущий файл
     * @param attrs - атрибуты файла (оставляем все по-умолчанию)
     * @return возвращаем результат работы метода - то есть все найденные дубликаты
     * @throws IOException выбрасываем эксепшн если файл не найден
     */

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(file.toFile().length(), file.getFileName().toString());
        if (!duplicateFiles.add(fileProperty)) {
            System.out.println("не смогли добавить файл в Сет, значит это дубль: " + fileProperty.getName());
        }
        return super.visitFile(file, attrs);
    }
}
