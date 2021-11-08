package by.bsuir.pbz2.model.entity.sqlcolumn;

public enum SQLEntityColumn {
    DOCUMENT_ID,
    TYPE,
    DATE_OF_CREATION,
    CONTENT,
    EVENT,
    PERFORMER,
    DATE_OF_COMPLETION,
    STATUS,
    PERFORMER_ID,
    POSITION,
    SUBDIVISION,
    FIO;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
