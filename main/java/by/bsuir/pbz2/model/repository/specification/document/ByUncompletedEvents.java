package by.bsuir.pbz2.model.repository.specification.document;

import by.bsuir.pbz2.model.entity.sqlcolumn.SQLEntityColumn;

import java.sql.Date;

public class ByUncompletedEvents extends ByEventForPeriod {
    private static final boolean eventStatus = false;

    public ByUncompletedEvents(Date lowerLimit, Date upperLimit) {
        super(lowerLimit, upperLimit);
    }

    @Override
    public String getSQLClauses() {
        return String.format("WHERE %s BETWEEN \"%s\" ADN \"%s\" AND %s = %s"
                , SQLEntityColumn.DATE_OF_COMPLETION, getLowerLimit(), getUpperLimit()
                , SQLEntityColumn.STATUS, eventStatus);
    }
}
