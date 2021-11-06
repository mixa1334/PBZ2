package by.bsuir.pbz2.view.dialogs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.nio.file.Paths;

public class SearchStudentsDialog extends CustomDialog {
    private final SearchStudentJPanel searchStudentJPanel;
    private final JButton searchStudentsJButton;
    private final ViewerOfPages viewerOfPages;

    public SearchStudentsDialog(JFrame jFrame) {
        super(jFrame, "Поиск студентов");

        JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        viewerOfPages = new ViewerOfPages();
        searchStudentJPanel = new SearchStudentJPanel();
        searchStudentsJButton = new MyButton(Paths.get("Pictures//search-students.png").toFile());
        JPanel searchButtonJPanel = new JPanel();
        searchButtonJPanel.add(searchStudentsJButton);
        JPanel searchPanel = new JPanel(new BorderLayout(5, 15));
        searchPanel.add(searchButtonJPanel, BorderLayout.SOUTH);
        searchPanel.add(searchStudentJPanel, BorderLayout.CENTER);
        jPanel.add(viewerOfPages);
        jPanel.add(searchPanel);
        getContentPane().add(jPanel);
        pack();
        setCustomDialogLocation();
    }

    public ViewerOfPages getViewerOfPages() {
        return viewerOfPages;
    }

    public void setActionToDeleteStudentsJButton(ActionListener action) {
        searchStudentsJButton.addActionListener(action);
    }

    public Student.AllCriteria getSelectedCriteria() {
        return searchStudentJPanel.getSelectedCriteria();
    }

    public String getValueForSearch() {
        return searchStudentJPanel.getValueForSearch();
    }

    public void setAllProgrammingLanguages(String[] programmingLanguages) {
        searchStudentJPanel.setAllProgrammingLanguages(programmingLanguages);
    }

    public void setAllNumberOfTasks(String[] numberOfTasks) {
        searchStudentJPanel.setAllNumberOfTasks(numberOfTasks);
    }

    public void setAllNumberOfCompletedTasks(String[] numberOfCompletedTasks) {
        searchStudentJPanel.setAllNumberOfCompletedTasks(numberOfCompletedTasks);
    }
}
