package by.bsuir.pbz2.view;

import by.bsuir.pbz2.view.button.CustomButton;
import by.bsuir.pbz2.view.dialogs.document.AddEditDocumentDialog;
import by.bsuir.pbz2.view.dialogs.document.SearchDocumentDialog;
import by.bsuir.pbz2.view.dialogs.DeleteEntityDialog;
import by.bsuir.pbz2.view.pageviewer.DocumentViewer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.nio.file.Paths;

public class DocumentWindow extends JFrame {
    private final CustomButton updateDocumentsJButton;

    private final AddEditDocumentDialog addDocumentDialog;
    private final AddEditDocumentDialog editDocumentDialog;
    private final DeleteEntityDialog deleteEntityDialog;
    private final SearchDocumentDialog searchDocumentDialog;
    private final DocumentViewer documentViewer;

    public DocumentWindow() {
        super("Documents");
        setLayout(new BorderLayout(5, 5));
        positionLocSet();

        updateDocumentsJButton = new CustomButton(Paths.get("src//main//resources//pictures//update.png").toFile());

        documentViewer = new DocumentViewer();

        addDocumentDialog = new AddEditDocumentDialog(this, "Add document");
        editDocumentDialog = new AddEditDocumentDialog(this, "Edit document");
        deleteEntityDialog = new DeleteEntityDialog(this, "Delete document");
        searchDocumentDialog = new SearchDocumentDialog(this);

        add(documentViewer, BorderLayout.CENTER);
        setJToolBar();
    }

    public AddEditDocumentDialog getAddDocumentDialog() {
        return addDocumentDialog;
    }

    public AddEditDocumentDialog getEditDocumentDialog() {
        return editDocumentDialog;
    }

    public DeleteEntityDialog getDeleteEntityDialog() {
        return deleteEntityDialog;
    }

    public SearchDocumentDialog getSearchDocumentDialog() {
        return searchDocumentDialog;
    }

    public DocumentViewer getDocumentViewer() {
        return documentViewer;
    }

    public void setActionToUpdateButton(ActionListener action) {
        updateDocumentsJButton.addActionListener(action);
    }

    public void positionLocSet() {
        setBounds(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 600,
                Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 400,
                1200, 800);
    }

    private void setJToolBar() {
        JToolBar jToolBar = new JToolBar();
        jToolBar.setOrientation(SwingConstants.VERTICAL);

        JButton editJButton = new CustomButton(Paths.get("src//main//resources//pictures//edit.png").toFile());
        editJButton.addActionListener(e -> {
            editDocumentDialog.setCustomDialogLocation();
            editDocumentDialog.setVisible(true);
        });
        JButton deleteNoteJButton = new CustomButton(Paths.get("src//main//resources//pictures//delete-note.png").toFile());
        deleteNoteJButton.addActionListener(e -> {
            deleteEntityDialog.setCustomDialogLocation();
            deleteEntityDialog.setVisible(true);
        });
        JButton createJButton = new CustomButton(Paths.get("src//main//resources//pictures//create-note.png").toFile());
        createJButton.addActionListener(e -> {
            addDocumentDialog.setCustomDialogLocation();
            addDocumentDialog.setVisible(true);
        });
        JButton searchJButton = new CustomButton(Paths.get("src//main//resources//pictures//search.png").toFile());
        searchJButton.addActionListener(e -> {
            searchDocumentDialog.setCustomDialogLocation();
            searchDocumentDialog.setVisible(true);
        });


        jToolBar.add(editJButton);
        jToolBar.addSeparator();
        jToolBar.add(deleteNoteJButton);
        jToolBar.addSeparator();
        jToolBar.add(createJButton);
        jToolBar.addSeparator();
        jToolBar.add(searchJButton);
        jToolBar.addSeparator();
        jToolBar.add(updateDocumentsJButton);

        add(jToolBar, BorderLayout.WEST);
    }
}