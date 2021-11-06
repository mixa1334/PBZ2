package by.bsuir.pbz2.repository.performer;

import by.bsuir.pbz2.entity.Performer;
import by.bsuir.pbz2.exception.CustomException;
import by.bsuir.pbz2.repository.AbstractRepository;
import by.bsuir.pbz2.repository.specification.document.AllDocuments;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PerformerRepositoryTest {
    AbstractRepository<Performer> repository = PerformerRepository.getInstance();
    Performer toDelete;
    int id;

    @Test(priority = 2)
    public void testFindEntity() throws CustomException {
        toDelete = repository.findEntity(new AllDocuments()).get(0);
        int actual = toDelete.getPerformerId();

        assertEquals(actual, id);
    }

    @Test(priority = 4)
    public void testDeleteEntity() throws CustomException {
        boolean actual = repository.deleteEntity(toDelete);

        assertTrue(actual);
    }

    @Test(priority = 1)
    public void testCreateEntity() throws CustomException {
        Performer performer = new Performer();
        id = 0;
        performer.setPerformerId(id);
        performer.setPosition("СЕО");
        performer.setSubdivision("инженерное");
        performer.setFio("Кравцов Михаил Сергеевич");
        boolean actual = repository.createEntity(performer);

        assertTrue(actual);
    }

    @Test(priority = 3)
    public void testUpdateEntity() throws CustomException {
        toDelete.setSubdivision("главнокомандующий");
        boolean actual = repository.updateEntity(toDelete);

        assertTrue(actual);
    }
}