package by.bsuir.pbz2.view.dialogs.document;

import by.bsuir.pbz2.model.entity.Document;
import by.bsuir.pbz2.view.pageviewer.ViewerOfPages;

import static by.bsuir.pbz2.model.entity.sqlcolumn.SQLEntityColumn.*;

public class EventsViewer extends ViewerOfPages<Document> {
    public EventsViewer() {
        super(new String[]{DATE_OF_CREATION.toString(), DATE_OF_COMPLETION.toString(), EVENT.toString(), STATUS.toString()});
    }

    @Override
    public Object[] fieldsToDisplay(Document document) {
        return new Object[]{
                document.getDateOfCreation(),
                document.getDateOfCompletion(),
                document.getEvent(),
                document.getStatus()
        };
    }
}
