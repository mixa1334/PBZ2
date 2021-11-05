package by.bsuir.pbz2.repository;

import by.bsuir.pbz2.connection.ConnectionPoll;
import by.bsuir.pbz2.connection.impl.ConnectionPollImpl;
import by.bsuir.pbz2.entity.AbstractEntity;
import by.bsuir.pbz2.exception.CustomException;
import by.bsuir.pbz2.repository.specification.RepositorySpecification;

import java.sql.Connection;
import java.util.List;

public abstract class AbstractRepository<T extends AbstractEntity> {
    private final ConnectionPoll poll = ConnectionPollImpl.getInstance();

    public Connection getConnection() throws CustomException {
        return poll.getConnection();
    }

    public abstract List<T> findEntity(RepositorySpecification specification) throws CustomException;

    public abstract boolean deleteEntity(T t) throws CustomException;

    public abstract boolean createEntity(T t) throws CustomException;

    public abstract boolean updateEntity(T t) throws CustomException;
}
