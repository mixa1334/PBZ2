package by.bsuir.pbz2.connection;

import by.bsuir.pbz2.exception.CustomException;

import java.sql.Connection;

public interface ConnectionPoll {
    Connection getConnection() throws CustomException;
}