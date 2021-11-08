package by.bsuir.pbz2.model.repository.specification.document;

import by.bsuir.pbz2.model.entity.sqlcolumn.SQLEntityColumn;
import by.bsuir.pbz2.model.repository.specification.RepositorySpecification;

import java.sql.Date;

public class ByEventForPeriod implements RepositorySpecification {
    private Date lowerLimit;
    private Date upperLimit;

    public ByEventForPeriod(Date lowerLimit, Date upperLimit) {
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
    }

    public Date getLowerLimit() {
        return lowerLimit;
    }

    public void setLowerLimit(Date lowerLimit) {
        this.lowerLimit = lowerLimit;
    }

    public Date getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(Date upperLimit) {
        this.upperLimit = upperLimit;
    }

    @Override
    public String getSQLClauses() {
        return String.format("WHERE %s BETWEEN \"%s\" ADN \"%s\"", SQLEntityColumn.DATE_OF_COMPLETION, lowerLimit, upperLimit);
    }
}
