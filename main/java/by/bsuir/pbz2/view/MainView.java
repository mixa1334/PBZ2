package by.bsuir.pbz2.view;

import by.bsuir.pbz2.view.button.CustomButton;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {
    private final DocumentWindow documentWindow;
    private final PerformerWindow performerWindow;

    public MainView() {
        super("PBZ2");
        setLayout(new BorderLayout(5, 5));
        setBounds(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 600,
                Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 400,
                500, 200);

        documentWindow = new DocumentWindow(this);
        performerWindow = new PerformerWindow();

        initButtons();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public DocumentWindow getDocumentWindow() {
        return documentWindow;
    }

    public PerformerWindow getPerformerWindow() {
        return performerWindow;
    }

    private void initButtons() {
        JButton documentButton = new CustomButton("Documents");
        JButton performerButton = new CustomButton("Performers");
        documentButton.addActionListener(e -> {
            //documentDialog.;
            //documentWindow.setVisible(true);
        });
        performerButton.addActionListener(e -> {
            performerWindow.positionLocSet();
            performerWindow.setVisible(true);
        });

        JPanel panel = new JPanel();
        panel.add(documentButton);
        panel.add(performerButton);

        add(panel, BorderLayout.CENTER);
    }
}
