package by.bsuir.pbz2.view;

import by.bsuir.pbz2.view.buttons.CustomButton;
import by.bsuir.pbz2.view.dialogs.AddStudentDialog;
import by.bsuir.pbz2.view.dialogs.CustomDialog;
import by.bsuir.pbz2.view.dialogs.DeleteStudentsDialog;
import by.bsuir.pbz2.view.dialogs.SearchStudentsDialog;
import by.bsuir.pbz2.view.pageviewer.ViewerOfPages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.nio.file.Paths;

public class PerformerDialog extends CustomDialog {
    private final JFrame MainFrame;

    private JButton saveJButton;
    private JButton loadJButton;

    private JMenuItem loadListJMenuItem;
    private JMenuItem saveListJMenuItem;

    private final AddStudentDialog addStudentDialog;
    private final DeleteStudentsDialog deleteStudentsDialog;
    private final SearchStudentsDialog searchStudentsDialog;
    private final ViewerOfPages viewerOfPages;

    public PerformerDialog(JFrame frame) {
        super(frame, "Performers");
        MainFrame = new JFrame("PBZ2");
        MainFrame.setLayout(new BorderLayout(5, 5));
        MainFrame.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 600,
                Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 400,
                1200, 800);

        viewerOfPages = new ViewerOfPages();
        addStudentDialog = new AddStudentDialog(MainFrame);
        searchStudentsDialog = new SearchStudentsDialog(MainFrame);
        deleteStudentsDialog = new DeleteStudentsDialog(MainFrame);
        MainFrame.add(viewerOfPages, BorderLayout.CENTER);

        setJToolBar();

        MainFrame.setVisible(true);
    }

    public AddStudentDialog getAddStudentDialog() {
        return addStudentDialog;
    }

    public DeleteStudentsDialog getDeleteStudentsDialog() {
        return deleteStudentsDialog;
    }

    public SearchStudentsDialog getSearchStudentsDialog() {
        return searchStudentsDialog;
    }

    public ViewerOfPages getViewerOfPages() {
        return viewerOfPages;
    }

    public JFrame getMainFrame() {
        return MainFrame;
    }

    public void setActionToLoadFileButtons(ActionListener action) {
        loadJButton.addActionListener(action);
        loadListJMenuItem.addActionListener(action);
    }

    public void setActionToSaveFileButtons(ActionListener action) {
        saveJButton.addActionListener(action);
        saveListJMenuItem.addActionListener(action);
    }

    private void setJToolBar() {
        JToolBar jToolBar = new JToolBar();
        jToolBar.setOrientation(SwingConstants.VERTICAL);

        saveJButton = new CustomButton(Paths.get("Pictures//save.png").toFile());
        loadJButton = new CustomButton(Paths.get("Pictures//load.png").toFile());
        JButton editJButton = new CustomButton(Paths.get("Pictures//edit.png").toFile());
        editJButton.addActionListener(e -> {
            addStudentDialog.setCustomDialogLocation();
            addStudentDialog.setVisible(true);
        });
        JButton deleteNoteJButton = new CustomButton(Paths.get("Pictures//delete-note.png").toFile());
        deleteNoteJButton.addActionListener(e -> {
            deleteStudentsDialog.setCustomDialogLocation();
            deleteStudentsDialog.setVisible(true);
        });
        JButton searchJButton = new CustomButton(Paths.get("Pictures//search.png").toFile());
        searchJButton.addActionListener(e -> {
            searchStudentsDialog.setCustomDialogLocation();
            searchStudentsDialog.setVisible(true);
        });

        jToolBar.add(saveJButton);
        jToolBar.addSeparator();
        jToolBar.add(loadJButton);
        jToolBar.addSeparator();
        jToolBar.add(editJButton);
        jToolBar.addSeparator();
        jToolBar.add(deleteNoteJButton);
        jToolBar.addSeparator();
        jToolBar.add(searchJButton);

        MainFrame.add(jToolBar, BorderLayout.WEST);
    }
}
