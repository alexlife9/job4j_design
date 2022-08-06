package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Slf4j - вывод exception
 *
 * @author Alex_life
 * @version 1.0
 * @since 06.08.2022
 */
public class UsageLog4jEx2 {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        try {
            throw new Exception("Not supported code");
        /*} catch (Exception e) {
            e.printStackTrace();

            перенаправляем ексепшн на запись в лог
            Первый параметр - это сообщение, почему тут может быть исключение.
            Второй параметр - это объект исключения.
        }*/
        } catch (Exception e) {
            LOG.error("Exception in log example", e);
        }
        /*
        Примеры, как неправильно использовать логирование:

        1. Дублирование ошибки. В консоли будет дублирующая информация.
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("Exception in log example", e);

        2. Исключение без stack trace. В этом случае нельзя понять кто вызывал этот код. Не можем отследить причину.
        } catch (Exception e) {
            LOG.error(e.getMessage());

        3. Перепутанные параметры. Тут объект-исключение конвертируется в String. Это приводит к потере stack trace.
        } catch (Exception e) {
            LOG.error("Error {}, User {}", e, "Petr");

        4. Вывод в лог и генерация нового исключения. Это приводит к дублированию исключения.
        Либо в лог, либо генерация!
        } catch (Exception e) {
            LOG.error("Error", e);
            throw e;
        */

    }

}
