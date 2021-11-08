import by.bsuir.pbz2.controller.PerformerController;
import by.bsuir.pbz2.model.repository.performer.PerformerRepository;
import by.bsuir.pbz2.view.MainView;

public class MainM {
    public static void main(String[] args) {
        MainView d = new MainView();
        PerformerController controller = new PerformerController(PerformerRepository.getInstance(), d.getPerformerWindow());
        d.setVisible(true);
    }
}
