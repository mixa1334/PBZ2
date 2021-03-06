package by.bsuir.pbz2.model.repository.specification.document;

import by.bsuir.pbz2.model.entity.sqlcolumn.SQLEntityColumn;
import by.bsuir.pbz2.model.repository.specification.RepositorySpecification;

public class ByDocumentTypeSortedByDate implements RepositorySpecification {
    private String typeOfDocument;

    public ByDocumentTypeSortedByDate(String typeOfDocument) {
        this.typeOfDocument = typeOfDocument;
    }

    public String getTypeOfDocument() {
        return typeOfDocument;
    }

    public void setTypeOfDocument(String typeOfDocument) {
        this.typeOfDocument = typeOfDocument;
    }

    @Override
    public String getSQLClauses() {
        return String.format("WHERE %s LIKE \"%s\" ORDER BY %s", SQLEntityColumn.TYPE, typeOfDocument, SQLEntityColumn.DATE_OF_CREATION);
    }
}
