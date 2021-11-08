package by.bsuir.pbz2.view.dialogs.performer;

import by.bsuir.pbz2.view.buttons.CustomButton;
import by.bsuir.pbz2.view.dialogs.CustomDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DeletePerformerDialog extends CustomDialog {
    private final JButton performerJButton;
    private final JTextField idJTextField;

    public DeletePerformerDialog(JFrame frame) {
        super(frame, "Delete performer");

        JPanel jPanel = new JPanel(new GridLayout(1, 2, 10, 5));
        performerJButton = new CustomButton("Delete performer");
        jPanel.add(new JLabel("Id"));
        jPanel.add(idJTextField = new JTextField());


        JPanel buttonJPanel = new JPanel();
        buttonJPanel.add(performerJButton);

        setLayout(new BorderLayout(5, 5));

        getContentPane().add(jPanel, BorderLayout.CENTER);
        getContentPane().add(buttonJPanel, BorderLayout.SOUTH);
        pack();
        setCustomDialogLocation();
    }

    public void setButtonAction(ActionListener action) {
        performerJButton.addActionListener(action);
    }

    public void clearTextFields() {
        idJTextField.setText("");
    }

    public String getIdToDelete() {
        return idJTextField.getText();
    }
}
