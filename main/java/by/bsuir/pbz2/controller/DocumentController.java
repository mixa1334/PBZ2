package by.bsuir.pbz2.controller;

import by.bsuir.pbz2.model.entity.Document;
import by.bsuir.pbz2.model.entity.Performer;
import by.bsuir.pbz2.model.exception.CustomException;
import by.bsuir.pbz2.model.repository.document.DocumentRepository;
import by.bsuir.pbz2.model.repository.specification.document.AllDocuments;
import by.bsuir.pbz2.view.DocumentWindow;

import javax.swing.*;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DocumentController {
    private final String ID_REGEX = "\\d+";
    private final DocumentRepository repository;
    private final DocumentWindow window;
    private Map<Integer, Document> documentMap;
    private Map<Integer, Performer> performers;

    public DocumentController(Map<Integer, Performer> map, DocumentRepository repository, DocumentWindow window) {
        this.repository = repository;
        this.window = window;
        this.performers = map;

        window.setActionToUpdateButton(a -> updateDate(true));
        window.getAddDocumentDialog().setButtonAction(a -> addDocument());
        window.getDeleteEntityDialog().setButtonAction(a -> deleteDocument());
        window.getEditDocumentDialog().setButtonAction(a -> editDocument());
        window.getSearchDocumentDialog().setActionToButton(a -> searchDocument());
        updateDate(false);
    }

    private void updateDate(boolean showMsg) {
        try {
            List<Document> documentsFromDb = repository.findEntity(new AllDocuments());
            documentMap = new HashMap<>();
            documentsFromDb.forEach(d -> documentMap.put(d.getDocumentId(), d));
            window.getDocumentViewer().setEntityToDisplay(documentsFromDb);
            if (showMsg) {
                JOptionPane.showMessageDialog(window, "successfully updated!");
            }
        } catch (CustomException e) {
            JOptionPane.showMessageDialog(window, "Smth wrong with DB");
        }
    }

    private void deleteDocument() {
        try {
            String result = window.getDeleteEntityDialog().getIdToDelete();
            window.getDeleteEntityDialog().clearTextFields();
            if (!result.matches(ID_REGEX)) {
                JOptionPane.showMessageDialog(window, "invalid id: " + result);
                return;
            }
            int id = Integer.parseInt(result);
            if (!documentMap.containsKey(id)) {
                JOptionPane.showMessageDialog(window, "invalid id to delete: " + id);
                return;
            }

            Document performer = documentMap.get(id);
            repository.deleteEntity(performer);
            documentMap.remove(id);

            deleteDocumentFromTable(performer);

            JOptionPane.showMessageDialog(window, "successfully deleted!");
        } catch (CustomException e) {
            JOptionPane.showMessageDialog(window, "Smth wrong with DB");
        }
    }

    private void addDocument() {
        try {
            Object[] results = window.getAddDocumentDialog().getValuesFromUser();
            String perID = (String) results[5];
            String docID = (String) results[0];
            if (!docID.matches(ID_REGEX)) {
                JOptionPane.showMessageDialog(window, "invalid document id: " + docID);
                return;
            }
            if (!perID.matches(ID_REGEX)) {
                JOptionPane.showMessageDialog(window, "invalid performer id: " + perID);
                return;
            }
            int performerId = Integer.parseInt(perID);
            if (!performers.containsKey(performerId)) {
                JOptionPane.showMessageDialog(window, "no such performer: " + performerId);
                return;
            }
            int id = Integer.parseInt(docID);
            if (documentMap.containsKey(id)) {
                JOptionPane.showMessageDialog(window, "id already exists: " + id);
                return;
            }

            Document document = new Document();
            document.setDocumentId(id);
            document.setTypeOfDocument((String) results[1]);
            document.setDateOfCreation(convertDate(results[2]));
            document.setContent((String) results[3]);
            document.setEvent((String) results[4]);
            document.setPerformer(performers.get(performerId));
            document.setDateOfCompletion(convertDate(results[6]));
            document.setStatus(true);

            repository.createEntity(document);
            documentMap.put(id, document);

            addDocumentToTable(document);
            window.getAddDocumentDialog().clearTextFields();

            JOptionPane.showMessageDialog(window, "successfully added!");
        } catch (CustomException e) {
            JOptionPane.showMessageDialog(window, "Smth wrong with DB");
        }
    }

    private void editDocument() {
        
    }

    private void searchDocument() {

    }

    private void deleteDocumentFromTable(Document document) {
        window.getDocumentViewer().deleteEntityFromDisplay(document);
    }

    private void addDocumentToTable(Document document) {
        window.getDocumentViewer().addEntityToDisplay(document);
    }

    private Date convertDate(Object date) {
        return new Date(((java.util.Date) date).getTime());
    }
}
