package by.bsuir.pbz2;

import by.bsuir.pbz2.controller.DocumentController;
import by.bsuir.pbz2.controller.PerformerController;
import by.bsuir.pbz2.model.repository.document.DocumentRepository;
import by.bsuir.pbz2.model.repository.performer.PerformerRepository;
import by.bsuir.pbz2.view.MainView;

public class App {
    public static void main(String[] args) {
        MainView mainView = new MainView();
        PerformerController performerController = new PerformerController(PerformerRepository.getInstance(), mainView.getPerformerWindow());
        DocumentController documentController = new DocumentController(performerController.getPerformerMap(), DocumentRepository.getInstance(), mainView.getDocumentWindow());
        mainView.setVisible(true);
    }
}
