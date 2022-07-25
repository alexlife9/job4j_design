package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * BufferedReader - чтение файла через буферизированный поток
 *
 * @author Alex_life
 * @version 2.0
 * добавил метод save сохраняющий в файл найденные с помощью метода filter строки
 * @since 26.07.2022
 */
public class LogFilter {
    /**
     * метод filter читает файл и ищет в нем заданное значение
     * 1.создаем лист стрингов, куда будем складывать строки, удовлетворяющие поиску
     * 2.задаем значение для поиска - в примере надо найти строки содержащие "404"
     * 3.указываем позицию искомого значения - в примере предпоследнее место - значит длина массива минус 2.
     * 4.принимаем входящий файл в переменную in, через буферизированный поток
     * 5.с помощью метода readLine записываем из файла всю информацию построчно в переменную line
     * 6.создаем массив стрингов для удобного распихивания слов по индексам
     * 7.пока линии не закончились - режем отдельные слова в строках с параметром реза "пробел"
     * 8.сравниваем значение которое ищем с массивом строк (с учетов позиции на которой находится наш искомое слово)
     * 9.если найдено, то добавляем всю строчку в лист стрингов
     * 10.иначе продолжаем поиск на следующей строке в файле
     * @param file входящий файл log.txt
     * @return возвращает строки которые содержат заданное значение
     */
    public List<String> filter(String file) {
        List<String> list = new ArrayList<>();
        String searchValue = "404";
        int position = -2;

        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            String line = in.readLine();
            String[] array;
            while (line != null) {
                array = line.split(" ");
                if (searchValue.equals(array[array.length + position])) {
                    list.add(line + System.lineSeparator());
                }
                line = in.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * метод save сохраняет в файл список отфильтрованных строк по заданному условию
     * 1.передаем в буфер исходный поток file
     * 2.в переменной out собираем отфильтрованные строки
     * 3.записываем их в файл log.txt
     * @param log отфильтрованный список по заданному условию
     * @param file начальный входящий файл на фильтрацию log.txt
     */
    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)
                ))) {
            out.println(log);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        save(log, "404.txt");
    }
}
