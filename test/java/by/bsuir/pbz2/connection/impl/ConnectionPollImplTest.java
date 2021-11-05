package by.bsuir.pbz2.connection.impl;

import by.bsuir.pbz2.connection.ConnectionPoll;
import by.bsuir.pbz2.exception.CustomException;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class ConnectionPollImplTest {

    @Test
    public void testGetConnection() throws CustomException, SQLException {
        ConnectionPoll connectionPoll = ConnectionPollImpl.getInstance();
        Connection connection = connectionPoll.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT type FROM documents");
        List<String> actual = new ArrayList<>(5);
        while (resultSet.next()) {
            actual.add(resultSet.getString(1));
        }
        statement.close();
        connection.close();
        List<String> expected = List.of("приказ", "распоряжение");

        assertEquals(actual, expected);
    }
}