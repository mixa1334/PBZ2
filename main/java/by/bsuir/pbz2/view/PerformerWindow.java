package by.bsuir.pbz2.view;

import by.bsuir.pbz2.view.buttons.CustomButton;
import by.bsuir.pbz2.view.dialogs.performer.AddEditPerformerDialog;
import by.bsuir.pbz2.view.dialogs.performer.DeletePerformerDialog;
import by.bsuir.pbz2.view.pageviewer.PerformerViewer;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Paths;

public class PerformerWindow extends JFrame {

    private final AddEditPerformerDialog addPerformerDialog;
    private final DeletePerformerDialog deletePerformerDialog;
    private final AddEditPerformerDialog editPerformerDialog;
    private final PerformerViewer performerViewer;

    public PerformerWindow() {
        super("Performers");
        setLayout(new BorderLayout(5, 5));
        positionLocSet();

        performerViewer = new PerformerViewer();
        addPerformerDialog = new AddEditPerformerDialog(this, "Add performer");
        deletePerformerDialog = new DeletePerformerDialog(this);
        editPerformerDialog = new AddEditPerformerDialog(this, "Edit performer");
        add(performerViewer, BorderLayout.CENTER);
        setJToolBar();
    }

    public AddEditPerformerDialog getAddPerformerDialog() {
        return addPerformerDialog;
    }

    public DeletePerformerDialog getDeletePerformerDialog() {
        return deletePerformerDialog;
    }

    public AddEditPerformerDialog getEditPerformerDialog() {
        return editPerformerDialog;
    }

    public PerformerViewer getPerformerViewer() {
        return performerViewer;
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
            addPerformerDialog.setCustomDialogLocation();
            addPerformerDialog.setVisible(true);
        });
        JButton deleteNoteJButton = new CustomButton(Paths.get("src//main//resources//pictures//delete-note.png").toFile());
        deleteNoteJButton.addActionListener(e -> {
            deletePerformerDialog.setCustomDialogLocation();
            deletePerformerDialog.setVisible(true);
        });
        JButton searchJButton = new CustomButton(Paths.get("src//main//resources//pictures//create-note.png").toFile());
        searchJButton.addActionListener(e -> {
            editPerformerDialog.setCustomDialogLocation();
            editPerformerDialog.setVisible(true);
        });


        jToolBar.add(editJButton);
        jToolBar.addSeparator();
        jToolBar.add(deleteNoteJButton);
        jToolBar.addSeparator();
        jToolBar.add(searchJButton);

        add(jToolBar, BorderLayout.WEST);
    }
}
