package by.bsuir.pbz2.model.repository.specification.document;

import by.bsuir.pbz2.model.entity.sqlcolumn.SQLEntityColumn;
import by.bsuir.pbz2.model.repository.specification.RepositorySpecification;

import java.sql.Date;

public class ByUncompletedEvents implements RepositorySpecification {
    private static final boolean eventStatus = false;
    private Date limit;

    public ByUncompletedEvents(Date limit) {
        this.limit = limit;
    }

    @Override
    public String getSQLClauses() {
        return String.format("WHERE %s < \"%s\" AND %s = %s"
                , SQLEntityColumn.DATE_OF_COMPLETION, limit
                , SQLEntityColumn.STATUS, eventStatus);
    }
}
