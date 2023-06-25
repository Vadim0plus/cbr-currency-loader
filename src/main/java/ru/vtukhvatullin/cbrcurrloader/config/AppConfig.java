package ru.vtukhvatullin.cbrcurrloader.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppConfig {
    public static final String CBR_CURR_URL = "CBR_CURR_URL";
    public static final String DB_HOST_URL = "DB_HOST_URL";
    public static final String DB_USER = "DB_USER";
    public static final String DB_PASSWORD = "DB_PASSWORD";
    public static final String JOB_INTERVAL = "JOB_INTERVAL";

    public static String cbrCurrencyUrl;
    public static String dbHostUrl;
    public static String dbUser;
    public static String dbPassword;
    public static int jobInterval;

    static {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try (InputStream is = loader.getResourceAsStream("application.properties")) {
            Properties properties = new Properties();
            properties.load(is);

            /*cbrCurrencyUrl = System.getenv(CBR_CURR_URL) != null ? System.getenv(CBR_CURR_URL) : properties.getProperty("cbrCurrencyUrl");
            dbHostUrl = System.getenv(DB_HOST_URL) != null ? System.getenv(DB_HOST_URL) : properties.getProperty("dbHostUrl");
            dbUser = System.getenv(DB_USER) != null ? System.getenv(DB_USER) : properties.getProperty("dbUser");
            dbPassword = System.getenv(DB_PASSWORD) != null ? System.getenv(DB_PASSWORD) : properties.getProperty("dbPassword");
            jobInterval = Integer.parseInt(System.getenv(JOB_INTERVAL) != null ? System.getenv(JOB_INTERVAL) : properties.getProperty("jobInterval"));*/
            cbrCurrencyUrl = properties.getProperty("cbrCurrencyUrl");
            dbHostUrl = properties.getProperty("dbHostUrl");
            dbUser = properties.getProperty("dbUser");
            dbPassword = properties.getProperty("dbPassword");
            jobInterval = Integer.parseInt(properties.getProperty("jobInterval"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
