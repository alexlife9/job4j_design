package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Scanner
 *
 * Класс CSVReader читает данные из CSV файла и вывести их после фильтра в обработанном виде в другой файл
 *
 *
 * @author Alex_life
 * @version 3.0
 * @since 09.08.2022
 */
public class CSVReader {

    private static Path path;

    private static String outputInfo;

    static List<String> afterFilter = new ArrayList<>(); /*лист токенов после разбивки по разделителю*/

    /**
     * метод handle принимает аргументы, читает фходящий файл из csv и делит его на ткены по разделителю
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

        try (BufferedReader inputData = new BufferedReader(new FileReader(String.valueOf(path)))) {
            Scanner scanner = new Scanner(String.valueOf(inputData)); /*читаем сканером входящий файл*/
            String lines = inputData.readLine();
            while (lines != null) { /*пока есть строки*/
                scanner.useDelimiter(argsName.get(delimiter)); /*применяем разбивку на токены по разделителю*/
                afterFilter.add(lines); /*добавляем разбитые токены из входящего файла в лист строк*/
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * после того как прочитали файл и разбили его на токены, применяем фильтр и вычленяем необходимые нам данные
     * метод headLine формирует шапку файла исходя из заданного фильтра, переданного в аргументах
     */
    public static List<Integer> headLine(ArgsName argsName, String column) {
        int[] table; /*массив в который будем записывать отфильтрованные данные под порядковыми номерами*/
        List<String> firstLine = Arrays.asList(argsName.get("filter").split(","));
        while (afterFilter != null) {
            if (firstLine.contains("out")) {

            }
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
        outputData(..........);
        handle(argsNames);


    }
}
