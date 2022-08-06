package ru.job4j.io;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
/**
 * Log4j. Логирование системы
 *
 * @author Alex_life
 * @version 1.0
 * @since 06.08.2022
 */
public class UsageLog4j {

    private static final Logger LOG = LogManager.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");
    }
}
