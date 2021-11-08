package by.bsuir.pbz2.model.repository.document;

import by.bsuir.pbz2.model.entity.Document;
import by.bsuir.pbz2.model.entity.Performer;
import by.bsuir.pbz2.model.exception.CustomException;
import by.bsuir.pbz2.model.repository.AbstractRepository;
import by.bsuir.pbz2.model.repository.specification.RepositorySpecification;
import by.bsuir.pbz2.model.repository.specification.document.ByDocumentTypeSortedByDate;
import org.testng.annotations.Test;

import java.time.LocalDate;

import static org.testng.Assert.*;

public class DocumentRepositoryTest {
    AbstractRepository<Document> repository = DocumentRepository.getInstance();
    String type;
    Document toDelete;

    @Test(priority = 2)
    public void testFindEntity() throws CustomException {
        RepositorySpecification specification = new ByDocumentTypeSortedByDate(type);
        toDelete = repository.findEntity(specification).get(0);
        String actual = toDelete.getTypeOfDocument();

        assertEquals(actual, type);
    }

    @Test(priority = 4)
    public void testDeleteEntity() throws CustomException {
        boolean actual = repository.deleteEntity(toDelete);

        assertTrue(actual);
    }

    @Test(priority = 1)
    public void testCreate() throws CustomException {
        Document document = new Document();
        document.setDocumentId(111);
        type = "отказ";
        document.setTypeOfDocument(type);
        document.setDateOfCreation(LocalDate.now());
        document.setContent("Второй этап стройтильства ТЦ");
        document.setEvent("Закупка метариала");
        Performer performer = new Performer();
        performer.setPerformerId(1);
        document.setPerformer(performer);
        document.setDateOfCompletion(LocalDate.of(2023, 9, 11));
        document.setStatus(false);

        boolean actual = repository.createEntity(document);

        assertTrue(actual);
    }

    @Test(priority = 3)
    public void testUpdate() throws CustomException {
        toDelete.setStatus(true);
        toDelete.setEvent("уволить всех!");
        boolean actual = repository.updateEntity(toDelete);

        assertTrue(actual);
    }
}