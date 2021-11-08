package by.bsuir.pbz2.view.pageviewer;

import by.bsuir.pbz2.model.entity.Performer;

import static by.bsuir.pbz2.model.entity.sqlcolumn.SQLEntityColumn.*;

public class PerformerViewer extends ViewerOfPages<Performer> {
    public PerformerViewer() {
        super(new String[]{PERFORMER_ID.toString(), POSITION.toString()
                , SUBDIVISION.toString(), FIO.toString()});
    }

    @Override
    Object[] fieldsToDisplay(Performer performer) {
        return new Object[]{
                performer.getPerformerId(),
                performer.getPosition(),
                performer.getSubdivision(),
                performer.getFio()
        };
    }
}
