package by.bsuir.pbz2.repository.document;

import by.bsuir.pbz2.entity.Document;
import by.bsuir.pbz2.entity.Performer;
import by.bsuir.pbz2.exception.CustomException;
import by.bsuir.pbz2.repository.AbstractRepository;
import by.bsuir.pbz2.repository.specification.document.AllDocuments;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.List;

import static org.testng.Assert.*;

public class DocumentRepositoryTest {
    AbstractRepository<Document> repository = DocumentRepository.getInstance();
    String content;

    @Test
    public void testFindEntity() throws CustomException {

    }

    @Test
    public void testDeleteEntity() throws CustomException {
    }

    @Test(priority = 1)
    public void testCreate() throws CustomException {
        Document document = new Document();
        document.setTypeOfDocument("отказ");
        document.setDateOfCreation(LocalDate.now());
        content = "Второй этап стройтильства ТЦ";
        document.setContent(content);
        document.setEvent("Закупка метариала");
        Performer performer = new Performer();
        performer.setPerformerId(1);
        document.setPerformer(performer);
        document.setDateOfCompletion(LocalDate.of(2023, 9, 11));
        document.setStatus(false);

        boolean actual = repository.createEntity(document);

        assertTrue(actual);
    }

    @Test
    public void testUpdate() throws CustomException {
    }
}