package by.bsuir.pbz2.controller;

import by.bsuir.pbz2.model.entity.Document;
import by.bsuir.pbz2.model.repository.document.DocumentRepository;
import by.bsuir.pbz2.view.DocumentWindow;

import java.util.Map;

public class DocumentController {
    private final String ID_REGEX = "\\d+";
    private final DocumentRepository repository;
    private final DocumentWindow window;
    private Map<Integer, Document> performerMap;

    public DocumentController(DocumentRepository repository, DocumentWindow window) {
        this.repository = repository;
        this.window = window;
    }

    private void updateDate(boolean showMsg) {

    }

    private void deleteDocument() {

    }

    private void addDocument() {

    }

    private void editDocument() {

    }

    private void searchDocument() {

    }

    private void deletePerformerFromTable(Document document) {
        window.getDocumentViewer().deleteEntityFromDisplay(document);
    }

    private void addPerformerToTable(Document document) {
        window.getDocumentViewer().addEntityToDisplay(document);
    }
}
