package by.bsuir.pbz2.model.repository.document;

import by.bsuir.pbz2.model.entity.Document;
import by.bsuir.pbz2.model.entity.Performer;
import by.bsuir.pbz2.model.entity.sqlcolumn.SQLEntityColumn;
import by.bsuir.pbz2.model.exception.CustomException;
import by.bsuir.pbz2.model.repository.AbstractRepository;
import by.bsuir.pbz2.model.repository.specification.RepositorySpecification;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DocumentRepository extends AbstractRepository<Document> {
    private final static Logger logger = LogManager.getLogger();
    private final static DocumentRepository instance = new DocumentRepository();
    private final static String SQL_ALL_DOCUMENTS = String.format("SELECT * FROM documents JOIN performers ON %s = %s"
            , SQLEntityColumn.PERFORMER, SQLEntityColumn.PERFORMER_ID);
    private final static String SQL_DELETE_DOCUMENT_BY_ID = String.format("DELETE FROM documents WHERE %s = ?"
            , SQLEntityColumn.DOCUMENT_ID);
    private final static String SQL_INSERT_DOCUMENT = "INSERT INTO documents VALUES (?,?,?,?,?,?,?,?)";
    private final static String SQL_UPDATE_DOCUMENT = String.format("UPDATE documents SET %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ? WHERE %s = ?"
            , SQLEntityColumn.TYPE, SQLEntityColumn.DATE_OF_CREATION, SQLEntityColumn.CONTENT, SQLEntityColumn.EVENT
            , SQLEntityColumn.PERFORMER, SQLEntityColumn.DATE_OF_COMPLETION, SQLEntityColumn.STATUS, SQLEntityColumn.DOCUMENT_ID);

    private DocumentRepository() {
    }

    public static DocumentRepository getInstance() {
        return instance;
    }

    @Override
    public List<Document> findEntity(RepositorySpecification specification) throws CustomException {
        String query = SQL_ALL_DOCUMENTS + " " + specification.getSQLClauses();
        ArrayList<Document> documents = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Document document = new Document();
                document.setDocumentId(resultSet.getInt(SQLEntityColumn.DOCUMENT_ID.toString()));
                document.setTypeOfDocument(resultSet.getString(SQLEntityColumn.TYPE.toString()));
                Date dateOfCreation = resultSet.getDate(SQLEntityColumn.DATE_OF_CREATION.toString());
                document.setDateOfCreation(LocalDate.parse(dateOfCreation.toString()));
                document.setContent(resultSet.getString(SQLEntityColumn.CONTENT.toString()));
                document.setEvent(resultSet.getString(SQLEntityColumn.EVENT.toString()));
                Date dateOfCompletion = resultSet.getDate(SQLEntityColumn.DATE_OF_COMPLETION.toString());
                document.setDateOfCompletion(LocalDate.parse(dateOfCompletion.toString()));
                document.setStatus(resultSet.getBoolean(SQLEntityColumn.STATUS.toString()));

                Performer performer = new Performer();
                performer.setPerformerId(resultSet.getInt(SQLEntityColumn.PERFORMER_ID.toString()));
                performer.setPosition(resultSet.getString(SQLEntityColumn.POSITION.toString()));
                performer.setSubdivision(resultSet.getString(SQLEntityColumn.SUBDIVISION.toString()));
                performer.setFio(resultSet.getString(SQLEntityColumn.FIO.toString()));
                document.setPerformer(performer);

                logger.log(Level.INFO, "document from db -> " + document);

                documents.add(document);
            }
        } catch (SQLException throwables) {
            String message = "cant execute query -> " + query;
            logger.log(Level.ERROR, message);
            throw new CustomException(message, throwables);
        }
        logger.log(Level.INFO, "documents find -> " + documents.size());
        return documents;
    }

    @Override
    public boolean deleteEntity(Document document) throws CustomException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_DOCUMENT_BY_ID)) {
            logger.log(Level.INFO, "document to delete -> " + document);
            statement.setInt(1, document.getDocumentId());
            int result = statement.executeUpdate();
            logger.log(Level.INFO, "status after deleting -> " + result);
            return result > 0;
        } catch (SQLException throwables) {
            String message = "cant execute query -> " + SQL_DELETE_DOCUMENT_BY_ID;
            logger.log(Level.ERROR, message);
            throw new CustomException(message, throwables);
        }
    }

    @Override
    public boolean createEntity(Document document) throws CustomException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT_DOCUMENT)) {
            logger.log(Level.INFO, "document to create -> " + document);
            statement.setInt(1, document.getDocumentId());
            statement.setString(2, document.getTypeOfDocument());
            statement.setDate(3, Date.valueOf(document.getDateOfCreation()));
            statement.setString(4, document.getContent());
            statement.setString(5, document.getEvent());
            statement.setInt(6, document.getPerformer().getPerformerId());
            statement.setDate(7, Date.valueOf(document.getDateOfCompletion()));
            statement.setBoolean(8, document.getStatus());
            int result = statement.executeUpdate();
            logger.log(Level.INFO, "status after inserting -> " + result);
            return result > 0;
        } catch (SQLException throwables) {
            String message = "cant execute query -> " + SQL_INSERT_DOCUMENT;
            logger.log(Level.ERROR, message);
            throw new CustomException(message, throwables);
        }
    }

    @Override
    public boolean updateEntity(Document document) throws CustomException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_DOCUMENT)) {
            logger.log(Level.INFO, "document to update -> " + document);
            statement.setString(1, document.getTypeOfDocument());
            statement.setDate(2, Date.valueOf(document.getDateOfCreation()));
            statement.setString(3, document.getContent());
            statement.setString(4, document.getEvent());
            statement.setInt(5, document.getPerformer().getPerformerId());
            statement.setDate(6, Date.valueOf(document.getDateOfCompletion()));
            statement.setBoolean(7, document.getStatus());
            statement.setInt(8, document.getDocumentId());
            int result = statement.executeUpdate();
            logger.log(Level.INFO, "status after updating -> " + result);
            return result > 0;
        } catch (SQLException throwables) {
            String message = "cant execute query -> " + SQL_UPDATE_DOCUMENT;
            logger.log(Level.ERROR, message);
            throw new CustomException(message, throwables);
        }
    }
}
