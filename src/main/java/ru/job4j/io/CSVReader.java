package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Scanner
 *
 * Класс CSVReader читает данные из CSV файла и вывести их после фильтра в обработанном виде в другой файл
 *
 *
 * @author Alex_life
 * @version 1.0
 * @since 05.08.2022
 */
public class CSVReader {

    private static Path path;

    private static String outputInfo;

    /**
     * метод handle принимает аргументы, читает фходящий файл из csv и применяет фильтр
     *
     * java -jar target/csvReader.jar -path=file.csv -delimiter=";"  -out=stdout -filter=name,age
     * @param argsName - аргументы для запуска
     */
    public static void handle(ArgsName argsName) throws Exception {
        Path path = Path.of(argsName.get("path"));
        String delimiter = argsName.get("delimiter");
        String filter = argsName.get("filter");
        String out = argsName.get("out");
        checkArgs(path, delimiter, filter, out);
        List<String> outputInfo = new ArrayList<>();
        try (BufferedReader inputData = new BufferedReader(new FileReader(String.valueOf(path)))) {
            outputInfo.add(String.valueOf(inputData));


            outputData(outputInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * метод outputData выбирает куда выводить отфильтрованные данные - в консоль или в файл
     * @param outList
     * @throws IOException
     */
    private static void outputData(List<String> outList) throws IOException {
        if (!outputInfo.equals("stdout")) {
            Path out = Paths.get(outputInfo);
            Files.write(out, outList);
        } else {
            for (String str : outList) {
                System.out.println(str);
            }
        }
    }

    /**
     * метод checkArgs проверяет корректность переданных аргументов
     * -path=file.csv -delimiter=";"  -out=stdout -filter=name,age
     *
     * @param path
     * @param delimiter
     * @param filter
     * @param out если =stdout, то вывод надо делать в консоль, либо явно указать путь к файлу, куда нужно вывести
     */
    private static void checkArgs(Path path, String delimiter, String filter, String out) {
        if (!Files.exists(path)) {
            throw new IllegalArgumentException("Указана несуществующий файл");
        }
        if (!delimiter.endsWith(";")) {
            throw new IllegalArgumentException("Указан некорректный разделитель файла");
        }
        if (!path.endsWith(".csv")) {
            throw new IllegalArgumentException("Указано некорректное расширение входящего файла");
        }
        if (!filter.contains(",")) {
            throw new IllegalArgumentException("Не указаны разделители требуемых фильтров");
        }
        if (!out.equals("stdout") || !out.endsWith(".txt")) {
            throw new IllegalArgumentException("Некорректно указан вывод результатов программы");
        }
    }

    /**
     * java -jar target/csvReader.jar -path=file.csv -delimiter=";"  -out=stdout -filter=name,age
     * @param args аргументы для запуска
     */
    public static void main(String[] args) throws Exception {
        ArgsName argsNames = ArgsName.of(args);
        handle(argsNames);


    }
}
