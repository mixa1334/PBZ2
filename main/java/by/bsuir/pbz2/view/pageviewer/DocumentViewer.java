package by.bsuir.pbz2.view.pageviewer;

import by.bsuir.pbz2.model.entity.Document;

import static by.bsuir.pbz2.model.entity.sqlcolumn.SQLEntityColumn.*;

public class DocumentViewer extends ViewerOfPages<Document> {
    public DocumentViewer() {
        super(new String[]{DOCUMENT_ID.toString(), TYPE.toString()
                , DATE_OF_CREATION.toString(), CONTENT.toString()
                , EVENT.toString(), PERFORMER.toString()
                , DATE_OF_COMPLETION.toString(), STATUS.toString()});
    }

    @Override
    public Object[] fieldsToDisplay(Document document) {
        return new Object[]{
                document.getDocumentId(),
                document.getTypeOfDocument(),
                document.getDateOfCreation(),
                document.getContent(),
                document.getEvent(),
                document.getPerformer().getPerformerId(),
                document.getDateOfCompletion(),
                document.getStatus()
        };
    }
}
