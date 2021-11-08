package by.bsuir.pbz2.view;

import by.bsuir.pbz2.view.buttons.CustomButton;

import javax.swing.*;
import java.awt.*;

public class MainView {
    private final JFrame MainFrame;

    private final DocumentWindow documentWindow;
    private final PerformerWindow performerWindow;

    public MainView() {
        MainFrame = new JFrame("PBZ2");
        MainFrame.setLayout(new BorderLayout(5, 5));
        MainFrame.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 600,
                Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 400,
                500, 200);

        documentWindow = new DocumentWindow(MainFrame);
        performerWindow = new PerformerWindow();

        initButtons();

        MainFrame.setVisible(true);

        MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public DocumentWindow getDocumentWindow() {
        return documentWindow;
    }

    public PerformerWindow getPerformerWindow() {
        return performerWindow;
    }

    public JFrame getMainFrame() {
        return MainFrame;
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

        MainFrame.add(panel, BorderLayout.CENTER);
    }
}
