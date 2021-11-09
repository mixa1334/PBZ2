package by.bsuir.pbz2.view.dialogs.document;

import by.bsuir.pbz2.model.entity.Document;
import by.bsuir.pbz2.view.pageviewer.ViewerOfPages;

import static by.bsuir.pbz2.model.entity.sqlcolumn.SQLEntityColumn.*;

public class DocTypeViewer extends ViewerOfPages<Document> {
    public DocTypeViewer() {
        super(new String[]{TYPE.toString(), DATE_OF_CREATION.toString(), CONTENT.toString(), STATUS.toString()});
    }

    @Override
    public Object[] fieldsToDisplay(Document document) {
        return new Object[]{
                document.getTypeOfDocument(),
                document.getDateOfCreation(),
                document.getContent(),
                document.getStatus()
        };
    }
}
