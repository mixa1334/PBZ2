package by.bsuir.pbz2.repository.performer;

import by.bsuir.pbz2.entity.Performer;
import by.bsuir.pbz2.entity.sqlcolumn.SQLEntityColumn;
import by.bsuir.pbz2.exception.CustomException;
import by.bsuir.pbz2.repository.AbstractRepository;
import by.bsuir.pbz2.repository.specification.RepositorySpecification;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PerformerRepository extends AbstractRepository<Performer> {
    private final static Logger logger = LogManager.getLogger();
    private final static PerformerRepository instance = new PerformerRepository();
    private final static String SQL_ALL_PERFORMERS = "SELECT * FROM performers";
    private final static String SQL_DELETE_PERFORMER_BY_ID = String.format("DELETE FROM performers WHERE %s = ?"
            , SQLEntityColumn.PERFORMER_ID);
    private final static String SQL_INSERT_PERFORMER = "INSERT INTO performers VALUES (?,?,?,?)";
    private final static String SQL_UPDATE_PERFORMER = String.format("UPDATE performers SET %s = ?, %s = ?, %s = ? WHERE %s = ?"
            , SQLEntityColumn.POSITION, SQLEntityColumn.SUBDIVISION, SQLEntityColumn.FIO, SQLEntityColumn.PERFORMER_ID);

    private PerformerRepository() {
    }

    public static PerformerRepository getInstance() {
        return instance;
    }

    @Override
    public List<Performer> findEntity(RepositorySpecification specification) throws CustomException {
        String query = SQL_ALL_PERFORMERS + specification.getSQLClauses();
        List<Performer> performers = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Performer performer = new Performer();
                performer.setPerformerId(resultSet.getInt(SQLEntityColumn.PERFORMER_ID.toString()));
                performer.setPosition(resultSet.getString(SQLEntityColumn.POSITION.toString()));
                performer.setSubdivision(resultSet.getString(SQLEntityColumn.SUBDIVISION.toString()));
                performer.setFio(resultSet.getString(SQLEntityColumn.FIO.toString()));
                performers.add(performer);
            }
        } catch (SQLException throwables) {
            String message = "cant execute query -> " + query;
            logger.log(Level.ERROR, message);
            throw new CustomException(message, throwables);
        }
        logger.log(Level.INFO, "performers from db -> " + performers);
        return performers;
    }

    @Override
    public boolean deleteEntity(Performer performer) throws CustomException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_PERFORMER_BY_ID)) {
            logger.log(Level.INFO, "performer to delete -> " + performer);
            statement.setInt(1, performer.getPerformerId());
            int result = statement.executeUpdate();
            logger.log(Level.INFO, "rows edited -> " + result);
            return result > 0;
        } catch (SQLException throwables) {
            String message = "cant execute query -> " + SQL_DELETE_PERFORMER_BY_ID;
            logger.log(Level.ERROR, message);
            throw new CustomException(message, throwables);
        }
    }

    @Override
    public boolean createEntity(Performer performer) throws CustomException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT_PERFORMER)) {
            logger.log(Level.INFO, "performer to create -> " + performer);
            statement.setInt(1, performer.getPerformerId());
            statement.setString(2, performer.getPosition());
            statement.setString(3, performer.getSubdivision());
            statement.setString(4, performer.getFio());
            int result = statement.executeUpdate();
            logger.log(Level.INFO, "rows edited -> " + result);
            return result > 0;
        } catch (SQLException throwables) {
            String message = "cant execute query -> " + SQL_INSERT_PERFORMER;
            logger.log(Level.ERROR, message);
            throw new CustomException(message, throwables);
        }
    }

    @Override
    public boolean updateEntity(Performer performer) throws CustomException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_PERFORMER)) {
            logger.log(Level.INFO, "performer to update -> " + performer);
            statement.setString(1, performer.getPosition());
            statement.setString(2, performer.getSubdivision());
            statement.setString(3, performer.getFio());
            statement.setInt(4, performer.getPerformerId());
            int result = statement.executeUpdate();
            logger.log(Level.INFO, "rows edited -> " + result);
            return result > 0;
        } catch (SQLException throwables) {
            String message = "cant execute query -> " + SQL_UPDATE_PERFORMER;
            logger.log(Level.ERROR, message);
            throw new CustomException(message, throwables);
        }
    }
}
