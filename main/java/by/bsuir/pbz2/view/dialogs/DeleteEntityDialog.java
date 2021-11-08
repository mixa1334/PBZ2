package by.bsuir.pbz2.view.dialogs;

import by.bsuir.pbz2.view.button.CustomButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DeleteEntityDialog extends CustomDialog {
    private final JButton performerJButton;
    private final JTextField idJTextField;

    public DeleteEntityDialog(JFrame frame, String text) {
        super(frame, text);

        JPanel jPanel = new JPanel(new GridLayout(1, 2, 10, 5));
        performerJButton = new CustomButton(text);
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
