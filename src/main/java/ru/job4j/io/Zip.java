package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Архивировать проект
 *
 * Техническое задание.
 *
 * 1. При запуске указывается папка, которую мы хотим архивировать, например: c:\project\job4j\
 * 2. В качестве ключа передаётся расширение файлов, которые не нужно включать в архив.
 * 3. Архив должен сохранять структуру проекта. То есть содержать подпапки.
 * 4. Запуск проекта с помощью конфигурации параметров запуска:
 * java -jar pack.jar -d=c:\project\job4j_design -e=*.java -o=project.zip
 * где:
 * java -jar pack.jar - это собранный jar.
 * -d - directory - которую мы хотим архивировать.
 * -e - exclude - исключить файлы с расширением class.
 * -o - output - во что мы архивируем.
 * 5. Для работы с входными аргументами используем класс ArgsName.
 * Важно! Перед запуском проекта нужно делать валидацию аргументов, проверив что они все присутствуют.
 * Также нужно проверить, что архивируемая директория существует.
 * 6. Для архивации использовать классы ZipOutputStream.java.
 * 7. Для поиска и фильтрации файлов используем класс Search.
 *
 * @author Alex_life
 * @version 2.0
 * @since 04.08.2022
 */
public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zipFiles =
                     new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(String.valueOf(target))))) {
            for (Path source : sources) {
                zipFiles.putNextEntry(new ZipEntry(source.toFile().getPath()));
                try (BufferedInputStream out =
                             new BufferedInputStream(new FileInputStream(source.toFile()))) {
                    zipFiles.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void checkArgs(String[] chArg) {
        ArgsName argsName = ArgsName.of(chArg);
        if (chArg.length != 3) {
            throw new IllegalArgumentException("Указаны не все аргументы для запуска");
        }
        if (!Path.of(argsName.get("d")).toFile().isDirectory()) {
            throw new IllegalArgumentException("Указана некорректная директория");
        }
        if (!argsName.get("e").startsWith("*.")) {
            throw new IllegalArgumentException("Указано некорректное расширение файла");
        }
        if (!argsName.get("o").endsWith(".zip")) {
            throw new IllegalArgumentException("Указано некорректный тип файла для архивирования");
        }


    }

    public static void main(String[] args) throws IOException {
        checkArgs(args);
        ArgsName argsN = ArgsName.of(args);
        List<Path> searchFiles = Search.search(Paths.get(argsN.get(args[0])),
                path -> path.toFile().getName().endsWith(args[2]));
        new Zip().packFiles(searchFiles, new File(argsN.get(args[2])));
    }
}
