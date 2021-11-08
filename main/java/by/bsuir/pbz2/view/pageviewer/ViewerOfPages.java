package by.bsuir.pbz2.view.pageviewer;

import by.bsuir.pbz2.view.buttons.CustomButton;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public abstract class ViewerOfPages<T> extends JPanel {
    private final int NOTES_PER_PAGE = 10;
    private final DefaultTableModel tableModel;
    private final JLabel numberOfCurrentPageLabel;
    private final JButton nextPageButton;
    private final JButton lastPageButton;
    private final JButton firstPageButton;
    private final JButton previousPageButton;
    private int currentPageNumber;
    private int allPagesCount;
    private List<T> entity;

    public ViewerOfPages(String[] columnNames) {
        numberOfCurrentPageLabel = new JLabel();
        JPanel downPanel = new JPanel();
        JPanel upPanel = new JPanel();

        nextPageButton = new CustomButton(Paths.get("src//main//resources//pictures//next-page.png").toFile());
        lastPageButton = new CustomButton(Paths.get("src//main//resources//pictures//last-page.png").toFile());
        firstPageButton = new CustomButton(Paths.get("src//main//resources//pictures//first-page.png").toFile());
        previousPageButton = new CustomButton(Paths.get("src//main//resources//pictures//previous-page.png").toFile());

        JTable jTable = new JTable();

        numberOfCurrentPageLabel.setText(currentPageNumber + "/" + allPagesCount);

        tableModel = new DefaultTableModel();

        tableModel.setColumnIdentifiers(columnNames);
        jTable.setModel(tableModel);

        setLayout(new BorderLayout(5, 5));

        downPanel.add(firstPageButton);
        downPanel.add(previousPageButton);
        downPanel.add(numberOfCurrentPageLabel);
        downPanel.add(nextPageButton);
        downPanel.add(lastPageButton);
        add(new JScrollPane(jTable), BorderLayout.CENTER);
        add(downPanel, BorderLayout.SOUTH);
        add(upPanel, BorderLayout.NORTH);
        setPreferredSize(new Dimension(1000, 600));

        entity = new ArrayList<>();

        initActions();
        resetPageView();
    }

    public void addEntityToDisplay(T entity) {
        this.entity.add(entity);
        resetPageView();
    }

    public void setEntityToDisplay(List<T> entity) {
        this.entity = entity;
        resetPageView();
    }

    public void resetPageView() {
        allPagesCount = entity.size() % NOTES_PER_PAGE == 0 ?
                entity.size() / NOTES_PER_PAGE : entity.size() / NOTES_PER_PAGE + 1;
        currentPageNumber = entity.size() == 0 ? 0 : 1;
        displayPage();
    }

    private List<T> getStudentsToDisplay() {
        return new ArrayList<>(entity.subList(
                (currentPageNumber - 1) * NOTES_PER_PAGE,
                Math.min(currentPageNumber * NOTES_PER_PAGE, entity.size())
        ));
    }

    private void displayPage() {
        tableModel.setRowCount(0);
        numberOfCurrentPageLabel.setText(currentPageNumber + "/" + allPagesCount);
        if (entity.size() == 0) return;
        getStudentsToDisplay().forEach(entity -> {
            tableModel.addRow(fieldsToDisplay(entity));
        });
    }

    abstract Object[] fieldsToDisplay(T t);

    private void initActions() {
        nextPageButton.addActionListener(e -> {
            if (entity.size() == 0) return;
            if (currentPageNumber < allPagesCount) {
                currentPageNumber++;
                displayPage();
            }
        });
        previousPageButton.addActionListener(e -> {
            if (entity.size() == 0) return;
            if (currentPageNumber > 1) {
                currentPageNumber--;
                displayPage();
            }
        });
        lastPageButton.addActionListener(e -> {
            if (entity.size() == 0) return;
            currentPageNumber = allPagesCount;
            displayPage();

        });
        firstPageButton.addActionListener(e -> {
            if (entity.size() == 0) return;
            currentPageNumber = 1;
            displayPage();
        });
    }
}