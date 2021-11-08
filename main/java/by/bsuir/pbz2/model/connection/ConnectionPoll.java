package by.bsuir.pbz2.model.connection;

import by.bsuir.pbz2.model.exception.CustomException;

import java.sql.Connection;

public interface ConnectionPoll {
    Connection getConnection() throws CustomException;
}