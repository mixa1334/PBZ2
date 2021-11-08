package by.bsuir.pbz2.view.dialogs.document;

import by.bsuir.pbz2.view.button.CustomButton;
import by.bsuir.pbz2.view.dialogs.CustomDialog;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class AddEditDocumentDialog extends CustomDialog {
    private final JButton documentJButton;
    private final JTextField idJTextField;
    private final JTextField docTypeJTextField;
    private final JDatePickerImpl dateOfCreationPicker;
    private final JTextField contentJTextField;
    private final JTextField eventJTextField;
    private final JTextField performerIdJTextField;
    private final JDatePickerImpl dateOfCompletionPicker;
    private final JComboBox<Boolean> statusJComboBox;

    public AddEditDocumentDialog(JFrame frame, String text) {
        super(frame, text);

        JPanel jPanel = new JPanel(new GridLayout(8, 2, 10, 5));
        documentJButton = new CustomButton(text);
        jPanel.add(new JLabel("Id"));
        jPanel.add(idJTextField = new JTextField());
        jPanel.add(new JLabel("document type"));
        jPanel.add(docTypeJTextField = new JTextField());
        jPanel.add(new JLabel("date of creation"));
        jPanel.add(dateOfCreationPicker = createDatePicker());
        jPanel.add(new JLabel("content"));
        jPanel.add(contentJTextField = new JTextField());
        jPanel.add(new JLabel("event"));
        jPanel.add(eventJTextField = new JTextField());
        jPanel.add(new JLabel("performer id"));
        jPanel.add(performerIdJTextField = new JTextField());
        jPanel.add(new JLabel("date of completion"));
        jPanel.add(dateOfCompletionPicker = createDatePicker());
        jPanel.add(new JLabel("event status"));
        jPanel.add(statusJComboBox = new JComboBox<>(new Boolean[]{true, false}));


        JPanel buttonJPanel = new JPanel();
        buttonJPanel.add(documentJButton);

        setLayout(new BorderLayout(5, 5));

        getContentPane().add(jPanel, BorderLayout.CENTER);
        getContentPane().add(buttonJPanel, BorderLayout.SOUTH);
        pack();
        setCustomDialogLocation();
    }

    public void setButtonAction(ActionListener action) {
        documentJButton.addActionListener(action);
    }

    public void clearTextFields() {
        idJTextField.setText("");
        docTypeJTextField.setText("");
        contentJTextField.setText("");
        eventJTextField.setText("");
        performerIdJTextField.setText("");
    }

    public Object[] getValuesFromUser() {
        return new Object[]{
                idJTextField.getText(),
                docTypeJTextField.getText(),
                dateOfCreationPicker.getModel().getValue(),
                contentJTextField.getText(),
                eventJTextField.getText(),
                performerIdJTextField.getText(),
                dateOfCompletionPicker.getModel().getValue(),
                statusJComboBox.getSelectedItem()
        };
    }

    private JDatePickerImpl createDatePicker() {
        UtilDateModel model = new UtilDateModel();
        LocalDate date = LocalDate.now();
        model.setDate(date.getYear(), date.getMonthValue(), date.getDayOfMonth());
        model.setSelected(true);
        JDatePanelImpl datePanel = new JDatePanelImpl(model);
        return new JDatePickerImpl(datePanel);
    }
}
