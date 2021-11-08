package by.bsuir.pbz2.view.dialogs.performer;

import by.bsuir.pbz2.view.buttons.CustomButton;
import by.bsuir.pbz2.view.dialogs.CustomDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AddEditPerformerDialog extends CustomDialog {
    private final JButton performerJButton;
    private final JTextField idJTextField;
    private final JTextField positionJTextField;
    private final JTextField subdivisionJTextField;
    private final JTextField fioJTextField;

    public AddEditPerformerDialog(JFrame frame, String text) {
        super(frame, text);

        JPanel jPanel = new JPanel(new GridLayout(4, 2, 10, 5));
        performerJButton = new CustomButton(text);
        jPanel.add(new JLabel("Id"));
        jPanel.add(idJTextField = new JTextField());
        jPanel.add(new JLabel("position"));
        jPanel.add(positionJTextField = new JTextField());
        jPanel.add(new JLabel("subdivision"));
        jPanel.add(subdivisionJTextField = new JTextField());
        jPanel.add(new JLabel("fio"));
        jPanel.add(fioJTextField = new JTextField());


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
        positionJTextField.setText("");
        subdivisionJTextField.setText("");
        fioJTextField.setText("");
    }

    public String[] getValuesFromUser() {
        return new String[]{
                idJTextField.getText(),
                positionJTextField.getText(),
                subdivisionJTextField.getText(),
                fioJTextField.getText()
        };
    }
}
