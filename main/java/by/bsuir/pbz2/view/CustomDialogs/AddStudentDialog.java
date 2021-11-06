package laba2Package.Views.CustomDialogs;

import laba2Package.Views.MyButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.nio.file.Paths;

public class AddStudentDialog extends CustomDialog {
    private final JButton addStudentJButton;
    private final JTextField fioJTextField;
    private final JTextField courseJTextField;
    private final JTextField groupJTextField;
    private final JTextField numberOfTasksJTextField;
    private final JTextField numberOfCompletedTasksJTextField;
    private final JTextField programmingLanguageJTextField;

    public AddStudentDialog(JFrame jFrame) {
        super(jFrame, "Добавление студента");
        JPanel jPanel = new JPanel(new GridLayout(6, 2, 10, 5));
        addStudentJButton = new MyButton("Добавить студента");
        jPanel.add(new JLabel("ФИО"));
        jPanel.add(fioJTextField = new JTextField());
        jPanel.add(new JLabel("Курс"));
        jPanel.add(courseJTextField = new JTextField());
        jPanel.add(new JLabel("Группа"));
        jPanel.add(groupJTextField = new JTextField());
        jPanel.add(new JLabel("Общее число работ"));
        jPanel.add(numberOfTasksJTextField = new JTextField());
        jPanel.add(new JLabel("Количество выполненных работ"));
        jPanel.add(numberOfCompletedTasksJTextField = new JTextField());
        jPanel.add(new JLabel("Язык программирования"));
        jPanel.add(programmingLanguageJTextField = new JTextField());

        JPanel buttonJPanel = new JPanel();
        buttonJPanel.add(addStudentJButton);

        setLayout(new BorderLayout(5, 5));

        getContentPane().add(jPanel, BorderLayout.CENTER);
        getContentPane().add(buttonJPanel, BorderLayout.SOUTH);
        pack();
        setCustomDialogLocation();
    }

    public void setActionToAddStudentJButton(ActionListener action) {
        addStudentJButton.addActionListener(action);
    }

    public void clearTextFields() {
        fioJTextField.setText("");
        courseJTextField.setText("");
        groupJTextField.setText("");
        numberOfTasksJTextField.setText("");
        numberOfCompletedTasksJTextField.setText("");
        programmingLanguageJTextField.setText("");
    }

    public String[] getValuesFromUser() {
        return new String[]{
                fioJTextField.getText(),
                courseJTextField.getText(),
                groupJTextField.getText(),
                numberOfTasksJTextField.getText(),
                numberOfCompletedTasksJTextField.getText(),
                programmingLanguageJTextField.getText(),
        };
    }
}
