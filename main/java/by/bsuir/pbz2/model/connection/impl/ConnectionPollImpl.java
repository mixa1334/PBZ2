package by.bsuir.pbz2.model.connection.impl;

import by.bsuir.pbz2.model.connection.ConnectionPoll;
import by.bsuir.pbz2.model.exception.CustomException;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionPollImpl implements ConnectionPoll {
    private final static Logger logger = LogManager.getLogger();
    private static final ConnectionPollImpl instance = new ConnectionPollImpl();
    private MysqlDataSource dataSource;

    private ConnectionPollImpl() {
        String path = "src/main/resources/database.properties";
        try (FileInputStream inputStream = new FileInputStream(path)) {
            Properties properties = new Properties();
            properties.load(inputStream);
            dataSource = new MysqlDataSource();
            dataSource.setURL(properties.getProperty("db.url"));
            dataSource.setUser(properties.getProperty("user"));
            dataSource.setPassword(properties.getProperty("password"));
        } catch (IOException e) {
            logger.log(Level.ERROR, "smth wrong with properties file -> " + path);
        }
    }

    public static ConnectionPollImpl getInstance() {
        return instance;
    }

    @Override
    public Connection getConnection() throws CustomException {
        try {
            return dataSource.getConnection();
        } catch (SQLException throwables) {
            logger.log(Level.ERROR, "impossible to create connection");
            throw new CustomException("impossible to create connection", throwables);
        }
    }
}
