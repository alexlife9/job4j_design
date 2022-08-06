package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Консольный чат
 *
 * Описание:
 * пользователь вводит слово-фразу, программа берет случайную фразу из текстового файла и выводит в ответ.
 * программа замолкает если пользователь вводит слово «стоп», при этом он может продолжать отправлять сообщения в чат.
 * если пользователь вводит слово «еще», программа снова начинает отвечать.
 * при вводе слова «выход» программа прекращает работу.
 * запись диалога, включая слова-команды стоп/еще/выход должны быть записаны в текстовый лог.
 *
 * класс принимает в конструктор 2 параметра -
 * 1.имя файла, в который будет записан весь диалог между ботом и пользователем,
 * 2.имя файла, в котором находятся строки с ответами, которые будет использовать бот.
 *
 * @author Alex_life
 * @version 3.0
 * @since 06.08.2022
 */
public class ConsoleChat {
    private static final String OUT = "выход";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "еще";
    private final String path;
    private final String botAnswers;
    private final List<String> logList = new ArrayList<>();

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    /**
     * Метод run() содержит логику чата
     *
     * 1.создаем лист ответов answersPhrases который прочитали из входящего файла
     * 2.делаем переменную - "ответ бота"
     * 3.приглашаем юзера ввести слово
     *   и открываем ему консоль на ввод
     * 4.пока юзер на ввел в консоль "выход", означающее завершение программы, мы проверяем условия:
     *  --если слово СТОП, то пока юзер не ввел ЕЩЕ, ждем его правильный ввод
     *  при этом все фразы юзера записываем в файл
     *  --иначе (если слова другие), то выдем рандомный ответ бота прочитанный из листа ответов
     *  заносим ответ бота в лог
     *  приглашаем юзера снова говаорить
     *  заносим фразу юзера в лог
     * 5.если юзер введет слово ВЫХОД, то программа завершится и лог сохраняется в файл.
     */
    public void run() {
        List<String> answersPhrases = readPhrases();
        String answerBot;
        System.out.println("Введите слово: ");
        Scanner input = new Scanner(System.in);
        String userText = input.nextLine();
        while (!OUT.equals(userText)) {
            if (STOP.equals(userText)) {
                while (!CONTINUE.equals(userText)) {
                    System.out.println("для продолжения введите ЕЩЕ");
                    userText = input.nextLine();
                }
                logList.add("юзер : " + userText);
            } else {
                answerBot = answersPhrases.get((int) (Math.random() * answersPhrases.size()));
                logList.add("бот : " + answerBot);
                System.out.println(answerBot);
                System.out.println("Введите слово: ");
                userText = input.nextLine();
                logList.add("юзер : " + userText);
            }
        }
        saveLog(logList);
    }

    /**
     * метод readPhrases() читает фразы из файла (сам источник задаем в методе main)
     * создаем лист стрингов, который будет содержать отдельные строчки из входящего файла
     * подаем на чтение через буфер входящий файл
     * с помощью форича считываем все строки из файла и дабавляем их в лист стрингов
     * @return лист содержащий отдельные строки ответов
     */
    private List<String> readPhrases() {
        List<String> inRW = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(botAnswers, Charset.forName("WINDOWS-1251")))) {
            in.lines().forEach(inRW::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inRW;
    }

    /**
     *  метод saveLog() сохраняет лог чата в файл
     *  1.указываем путь к файлу в который будем писать лог
     *  2.печатаем в этот файл все строки которые сохранили и передали из метода run()
     *
     * @param log лог чата в виде листа стрингов из отдельных строк
     */
    private void saveLog(List<String> log) {
        try (PrintWriter printWr = new PrintWriter(
                new FileWriter(path, Charset.forName("WINDOWS-1251"), true))) {
            log.forEach(printWr::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * создали новый объект с параметрами - исходящая запись чата и входящий файл с ответами от бота
     * выполняем метод run реализующий всю логику программы (описана в начале класса ConsoleChat)
     * @param args аргументы
     */
    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./data/saveChat.txt",
                "./data/inputRandomWord.txt");
        cc.run();
    }
}
