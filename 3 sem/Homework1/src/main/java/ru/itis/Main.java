package ru.itis;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {
        Properties properties = new Properties();

        try {
            properties.load(new FileReader("src/main/resources/application.properties"));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        HikariConfig config = new HikariConfig();
        config.setDriverClassName(properties.getProperty("db.driver"));
        config.setJdbcUrl(properties.getProperty("db.url"));
        config.setUsername(properties.getProperty("db.user"));
        config.setPassword(properties.getProperty("db.password"));
        config.setMaximumPoolSize(Integer.parseInt(properties.getProperty("db.hikary.pool-size")));

        DataSource dataSource = new HikariDataSource(config);

        TeachersRepository teachersRepository = new TeachersRepositoryJdbcImpl(dataSource);
    }

}
