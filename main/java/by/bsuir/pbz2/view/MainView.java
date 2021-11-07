package by.bsuir.pbz2.view;

import by.bsuir.pbz2.view.buttons.CustomButton;

import javax.swing.*;
import java.awt.*;

public class MainView {
    private final JFrame MainFrame;

    private final DocumentDialog documentDialog;
    private final PerformerDialog performerDialog;

    public MainView() {
        MainFrame = new JFrame("PBZ2");
        MainFrame.setLayout(new BorderLayout(5, 5));
        MainFrame.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 600,
                Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 400,
                1200, 800);

        documentDialog = new DocumentDialog(MainFrame);
        performerDialog = new PerformerDialog(MainFrame);

        setJToolBar();

        MainFrame.setVisible(true);
    }

    public DocumentDialog getDocumentDialog() {
        return documentDialog;
    }

    public PerformerDialog getPerformerDialog() {
        return performerDialog;
    }

    public JFrame getMainFrame() {
        return MainFrame;
    }

    private void setJToolBar() {
        JToolBar jToolBar = new JToolBar();
        jToolBar.setOrientation(SwingConstants.VERTICAL);

        JButton documentButton = new CustomButton("Documents");
        JButton performerButton = new CustomButton("Performers");
        documentButton.addActionListener(e -> {
            documentDialog.setCustomDialogLocation();
            documentDialog.setVisible(true);
        });
        performerButton.addActionListener(e -> {
            performerDialog.setCustomDialogLocation();
            performerDialog.setVisible(true);
        });

        jToolBar.add(documentButton);
        jToolBar.addSeparator();
        jToolBar.add(performerButton);

        MainFrame.add(jToolBar, BorderLayout.WEST);
    }
}
