package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Анализ доступности сервера
 *
 * @author Alex_life
 * @version 1.0
 * @since 27.07.2022
 */
public class Analizy {

    /**
     * Метод unavailable() находит диапазоны когда сервер не работал и записывает их в файл
     * Сервер не работал, если status = 400 или 500. Диапазоном считается последовательность статусов 400 и 500
     *
     * 1.создаем начало и конец записи строчки лога
     * 2.создаем список временных интервалов, в который будут писаться интервалы времени
     * 3.подаем на вход исходный файл
     * 4.читаем в нем каждую линию и проверяем условия пока линия не пуста
     * 5.если начало лога еще пустое И линия содержит (400 или 500)
     * то делим эту линию по пробелу в строке
     * и первую часть после применения сплита записываем в начало временного интервала
     * 6.если линия содержит (400 или 500) И в старт уже записано значение 200,
     * то делим эту линию по пробелу в строке
     * и первую часть записываем как конец интервала
     * 7.после всех проверок добавляем получившиеся интервалы в timesLogs
     * 8.проходим по ним форичем и записываем отфильтрованные данные в файл target
     *
     * @param source исходный файл с данными лога
     * @param target файл в который сохраняем результаты анализа в формате "начала периода;конец периода"
     */
    public static void unavailable(String source, String target) {
        String startLogs = null;
        String endLogs = null;
        String[] array;
        List<String> timesLogs = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            String line = in.readLine();
            while (line != null) {
                if (startLogs == null && (line.contains("400") || line.contains("500"))) {
                    line.split(" ");
                    array = line.split(" ");
                    startLogs = array[0];
                }
                if ((line.contains("400") || line.contains("500")) && startLogs.contains("200")){
                    array = line.split(" ");
                    endLogs = array[0];
                }
            }
            timesLogs.add(startLogs + ";" + endLogs);


        } catch (IOException e) {
            e.printStackTrace();
        }

        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            timesLogs.forEach(out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        unavailable("./data/server.log", "./data/unavailable.csv");
    }
}
