package by.bsuir.pbz2.view.dialogs.document;

import by.bsuir.pbz2.model.entity.Document;
import by.bsuir.pbz2.view.pageviewer.ViewerOfPages;

import static by.bsuir.pbz2.model.entity.sqlcolumn.SQLEntityColumn.*;

public class UncompletedEventsViewer extends ViewerOfPages<Document> {
    public UncompletedEventsViewer() {
        super(new String[]{DATE_OF_CREATION.toString(), EVENT.toString(), DATE_OF_COMPLETION.toString()
                , FIO.toString(), POSITION.toString(), SUBDIVISION.toString()});
    }

    @Override
    public Object[] fieldsToDisplay(Document document) {
        return new Object[]{
                document.getDateOfCreation(),
                document.getEvent(),
                document.getDateOfCompletion(),
                document.getPerformer().getFio(),
                document.getPerformer().getPosition(),
                document.getPerformer().getSubdivision()
        };
    }
}
