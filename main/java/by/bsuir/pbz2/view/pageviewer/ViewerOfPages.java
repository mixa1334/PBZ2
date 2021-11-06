package by.bsuir.pbz2.view.pageviewer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class ViewerOfPages extends JPanel {

    private final DefaultTableModel tableModel;
    private final JLabel numberOfCurrentPageLabel;
    private final JLabel countOfStudentsLabel;
    private final JButton nextPageButton;
    private final JButton lastPageButton;
    private final JButton firstPageButton;
    private final JButton previousPageButton;
    private final JComboBox<String> notesPerPageJComboBox;
    private ArrayList<Student> students;
    private int currentPageNumber;
    private int notesPerPage;
    private int allPagesCount;

    public ViewerOfPages() {
        this.students = new ArrayList<>();
        numberOfCurrentPageLabel = new JLabel();
        countOfStudentsLabel = new JLabel();
        JLabel notesPerPageLAbel = new JLabel();
        JPanel downPanel = new JPanel();
        JPanel upPanel = new JPanel();

        nextPageButton = new MyButton(Paths.get("Pictures//next-page.png").toFile());
        lastPageButton = new MyButton(Paths.get("Pictures//last-page.png").toFile());
        firstPageButton = new MyButton(Paths.get("Pictures//first-page.png").toFile());
        previousPageButton = new MyButton(Paths.get("Pictures//previous-page.png").toFile());

        JTable jTable = new JTable();

        notesPerPageJComboBox = new JComboBox<>(NotesPerPageEnum.getAllNames());

        notesPerPageLAbel.setText("Показывать студентов на странице  - ");
        countOfStudentsLabel.setText("Число всех студентов : " + students.size());
        numberOfCurrentPageLabel.setText(currentPageNumber + "/" + allPagesCount);

        notesPerPage = NotesPerPageEnum.FIVE.getValue();

        tableModel = new DefaultTableModel();
        String[] names = Arrays.copyOf(Student.AllCriteria.getAllName(),
                Student.AllCriteria.getAllName().length - 1);

        tableModel.setColumnIdentifiers(names);
        jTable.setModel(tableModel);

        setLayout(new BorderLayout(5, 5));

        downPanel.add(firstPageButton);
        downPanel.add(previousPageButton);
        downPanel.add(numberOfCurrentPageLabel);
        downPanel.add(nextPageButton);
        downPanel.add(lastPageButton);
        upPanel.add(notesPerPageLAbel);
        upPanel.add(notesPerPageJComboBox);
        upPanel.add(countOfStudentsLabel);
        add(new JScrollPane(jTable), BorderLayout.CENTER);
        add(downPanel, BorderLayout.SOUTH);
        add(upPanel, BorderLayout.NORTH);
        setPreferredSize(new Dimension(1000, 600));

        initActions();
        resetPageView();
    }

    public void setStudentsToDisplay(ArrayList<Student> students) {
        this.students = students;
        resetPageView();
    }

    public void resetPageView() {
        allPagesCount = students.size() % notesPerPage == 0 ?
                students.size() / notesPerPage : students.size() / notesPerPage + 1;
        currentPageNumber = students.size() == 0 ? 0 : 1;
        displayPage();
    }

    private ArrayList<Student> getStudentsToDisplay() {
        return new ArrayList<>(students.subList(
                (currentPageNumber - 1) * notesPerPage,
                Math.min(currentPageNumber * notesPerPage, students.size())
        ));
    }

    private void displayPage() {
        tableModel.setRowCount(0);
        countOfStudentsLabel.setText("Число всех студентов : " + students.size());
        numberOfCurrentPageLabel.setText(currentPageNumber + "/" + allPagesCount);
        if (students.size() == 0) return;
        getStudentsToDisplay().forEach(student -> {
            tableModel.addRow(new Object[]{
                    student.getFio(),
                    student.getCourse(),
                    student.getGroup(),
                    student.getNumberOfTasks(),
                    student.getNumberOfCompletedTasks(),
                    student.getProgrammingLanguage()
            });
        });
    }

    private void initActions() {
        nextPageButton.addActionListener(e -> {
            if (students.size() == 0) return;
            if (currentPageNumber < allPagesCount) {
                currentPageNumber++;
                displayPage();
            }
        });
        previousPageButton.addActionListener(e -> {
            if (students.size() == 0) return;
            if (currentPageNumber > 1) {
                currentPageNumber--;
                displayPage();
            }
        });
        lastPageButton.addActionListener(e -> {
            if (students.size() == 0) return;
            currentPageNumber = allPagesCount;
            displayPage();

        });
        firstPageButton.addActionListener(e -> {
            if (students.size() == 0) return;
            currentPageNumber = 1;
            displayPage();

        });
        notesPerPageJComboBox.addActionListener(e -> {
            String selectedEnum = Objects.requireNonNull(notesPerPageJComboBox.getSelectedItem()).toString();
            if (selectedEnum.equals(NotesPerPageEnum.FIVE.name)) {
                this.notesPerPage = NotesPerPageEnum.FIVE.numValue;
            } else if (selectedEnum.equals(NotesPerPageEnum.TEN.name)) {
                this.notesPerPage = NotesPerPageEnum.TEN.numValue;
            } else if (selectedEnum.equals(NotesPerPageEnum.FIFTY.name)) {
                this.notesPerPage = NotesPerPageEnum.FIFTY.numValue;
            }
            resetPageView();
        });
    }

    private enum NotesPerPageEnum {
        FIVE(5, "пять"),
        TEN(10, "десять"),
        FIFTY(50, "петьдесят");

        private final int numValue;
        private final String name;

        NotesPerPageEnum(final int numValue, final String name) {
            this.numValue = numValue;
            this.name = name;
        }

        public int getValue() {
            return numValue;
        }

        public String getName() {
            return name;
        }

        public static String[] getAllNames() {
            return new String[]{FIVE.name, TEN.name, FIFTY.name};
        }

        public static int[] getAllValues() {
            return new int[]{FIVE.numValue, TEN.numValue, FIFTY.numValue};
        }
    }
}