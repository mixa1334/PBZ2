package laba2Package.Views.CustomDialogs;

import laba2Package.Models.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class SearchStudentJPanel extends JPanel implements ItemListener {
    private final JComboBox<String> criteriaJComboBox;
    private final JPanel cardLayoutJPanel;

    private final JTextField fioJTextField;
    private final JTextField courseJTextField;
    private final JTextField numberOfToDoTasksJTextField;
    private final JTextField groupJTextField;
    private final JComboBox<String> numberOfTasksJComboBox;
    private final JComboBox<String> numberOfCompletedTasksJComboBox;
    private final JComboBox<String> programmingLanguagesJComboBox;

    public SearchStudentJPanel() {
        super(new GridLayout(2, 1, 10, 5));

        criteriaJComboBox = new JComboBox<>(Student.AllCriteria.getAllName());
        criteriaJComboBox.addItemListener(this);
        cardLayoutJPanel = new JPanel(new CardLayout());

        fioJTextField = new JTextField();
        courseJTextField = new JTextField();
        numberOfToDoTasksJTextField = new JTextField();
        groupJTextField = new JTextField();

        numberOfTasksJComboBox = new JComboBox<>();
        numberOfCompletedTasksJComboBox = new JComboBox<>();
        programmingLanguagesJComboBox = new JComboBox<>();

        cardLayoutJPanel.add(fioJTextField, Student.AllCriteria.FIO.getName());
        cardLayoutJPanel.add(courseJTextField, Student.AllCriteria.COURSE.getName());
        cardLayoutJPanel.add(groupJTextField, Student.AllCriteria.GROUP.getName());
        cardLayoutJPanel.add(numberOfToDoTasksJTextField, Student.AllCriteria.NUMBER_OF_TO_DO_TASKS.getName());
        cardLayoutJPanel.add(numberOfCompletedTasksJComboBox, Student.AllCriteria.NUMBER_OF_COMPLETED_TASKS.getName());
        cardLayoutJPanel.add(numberOfTasksJComboBox, Student.AllCriteria.NUMBER_OF_TASKS.getName());
        cardLayoutJPanel.add(programmingLanguagesJComboBox, Student.AllCriteria.PROGRAMMING_LANGUAGE.getName());
        
        add(criteriaJComboBox);
        add(cardLayoutJPanel);
    }

    public Student.AllCriteria getSelectedCriteria() {
        for (Student.AllCriteria criteria : Student.AllCriteria.values()) {
            if (criteria.getName().equals(criteriaJComboBox.getSelectedItem())) {
                return criteria;
            }
        }
        return Student.AllCriteria.FIO;
    }

    public String getValueForSearch() {
        String selectedCriteria = Objects.requireNonNull(criteriaJComboBox.getSelectedItem()).toString();
        if (selectedCriteria.equals(Student.AllCriteria.NUMBER_OF_TASKS.getName())) {
            return Objects.requireNonNull(numberOfTasksJComboBox.getSelectedItem()).toString();
        } else if (selectedCriteria.equals(Student.AllCriteria.NUMBER_OF_COMPLETED_TASKS.getName())) {
            return Objects.requireNonNull(numberOfCompletedTasksJComboBox.getSelectedItem()).toString();
        } else if (selectedCriteria.equals(Student.AllCriteria.PROGRAMMING_LANGUAGE.getName())) {
            return Objects.requireNonNull(programmingLanguagesJComboBox.getSelectedItem()).toString();
        } else if (selectedCriteria.equals(Student.AllCriteria.FIO.getName())) {
            return fioJTextField.getText();
        } else if (selectedCriteria.equals(Student.AllCriteria.GROUP.getName())) {
            return groupJTextField.getText();
        } else if (selectedCriteria.equals(Student.AllCriteria.COURSE.getName())) {
            return courseJTextField.getText();
        } else {
            return numberOfToDoTasksJTextField.getText();
        }
    }

    public void setAllProgrammingLanguages(String[] programmingLanguages) {
        if (programmingLanguages == null) return;
        programmingLanguagesJComboBox.removeAllItems();
        Arrays.stream(programmingLanguages).forEach(programmingLanguagesJComboBox::addItem);
    }

    public void setAllNumberOfTasks(String[] numberOfTasks) {
        if (numberOfTasks == null) return;
        numberOfTasksJComboBox.removeAllItems();
        Arrays.stream(numberOfTasks).forEach(numberOfTasksJComboBox::addItem);
    }

    public void setAllNumberOfCompletedTasks(String[] numberOfCompletedTasks) {
        if (numberOfCompletedTasks == null) return;
        numberOfCompletedTasksJComboBox.removeAllItems();
        Arrays.stream(numberOfCompletedTasks).forEach(numberOfCompletedTasksJComboBox::addItem);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        CardLayout layout = (CardLayout) (cardLayoutJPanel.getLayout());
        layout.show(cardLayoutJPanel, (String) e.getItem());
    }
}