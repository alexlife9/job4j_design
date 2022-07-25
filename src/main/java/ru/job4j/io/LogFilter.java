package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * BufferedReader - чтение файла через буферизированный поток
 *
 * @author Alex_life
 * @version 1.0
 * @since 25.07.2022
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
     * @param file входящий файл
     * @return возвращает строки которые содержат заданное значение
     */
    public List<String> filter(String file) {
        List<String> list = new ArrayList<>();
        String searchValue = "200";
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

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        System.out.print(log);
    }
}
