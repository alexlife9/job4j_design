package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Slf4j - вывод переменных
 *
 * Пример: ("User info name : {}, age : {}", name, age)
 * Первый параметр метода - "User info name : {}, age : {}"- это шаблон.
 * Шаблон содержит текст и отметки {}, которые заменяются на параметры.
 * Параметры указываются после шаблона через запятую - , name, age
 * Метки заменяются последовательно. Первая метка заменится первым параметром, вторая - вторым и так далее.
 * Если меток или параметров будет разное количество, логгер проигнорирует метку или параметр.
 *
 * @author Alex_life
 * @version 3.0
 * @since 06.08.2022
 */
public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Petr Arsentev";
        int age = 33;
        byte b = 8;
        short sh = 16;
        long l = 999L;
        float f = 888F;
        double d = 55.9;
        boolean bool = true;
        char ch = '*';
        LOG.debug("User info name : {}, age : {}, b : {}, sh : {}, l : {}, f : {}, d : {}, bool : {}, ch : {}",
                name, age, b, sh, l, f, d, bool, ch);
    }
}
