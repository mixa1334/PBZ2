package by.bsuir.pbz2.view.dialogs.document;

import by.bsuir.pbz2.model.entity.Document;
import by.bsuir.pbz2.view.button.CustomButton;
import by.bsuir.pbz2.view.dialogs.CustomDialog;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SearchDocumentDialog extends CustomDialog implements ItemListener {
    public static final String BY_DOCUMENT_TYPE_CRITERIA = "by document type";
    public static final String BY_UNCOMPLETED_EVENTS_FOR_PERIOD = "by uncompleted events";
    public static final String BY_EVENT_PERIOD = "events by period";
    private final JComboBox<String> criteriaJComboBox;
    private JPanel cardLayoutInput;
    private JPanel cardLayoutView;
    private JDatePickerImpl lowerLimitDate;
    private JDatePickerImpl upperLimitDate;
    private JDatePickerImpl limitDate;
    private JTextField docTypeField;
    private UncompletedEventsViewer uncompletedEventsViewer;
    private EventsViewer eventPeriodViewer;
    private DocTypeViewer docTypeViewer;

    private final JButton searchDocumentButton;

    public SearchDocumentDialog(JFrame frame) {
        super(frame, "Search documents");

        JPanel downPanel = new JPanel(new GridLayout(2, 1, 10, 5));
        criteriaJComboBox = new JComboBox<>(new String[]{BY_DOCUMENT_TYPE_CRITERIA, BY_UNCOMPLETED_EVENTS_FOR_PERIOD, BY_EVENT_PERIOD});
        criteriaJComboBox.addItemListener(this);

        initInputLayout();
        initViewLayout();

        downPanel.add(criteriaJComboBox);
        downPanel.add(cardLayoutInput);

        JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        searchDocumentButton = new CustomButton("find");
        JPanel searchButtonJPanel = new JPanel();
        searchButtonJPanel.add(searchDocumentButton);
        JPanel searchPanel = new JPanel(new BorderLayout(5, 15));
        searchPanel.add(downPanel, BorderLayout.CENTER);
        searchPanel.add(searchButtonJPanel, BorderLayout.SOUTH);
        jPanel.add(cardLayoutView);
        jPanel.add(searchPanel);
        getContentPane().add(jPanel);
        pack();
        setCustomDialogLocation();
    }

    public void setActionToButton(ActionListener action) {
        searchDocumentButton.addActionListener(action);
    }

    public void resetView() {
        uncompletedEventsViewer.setEntityToDisplay(new ArrayList<>());
        eventPeriodViewer.setEntityToDisplay(new ArrayList<>());
        docTypeViewer.setEntityToDisplay(new ArrayList<>());
        docTypeField.setText("");
    }

    public void setDocumentsToDisplay(List<Document> documents) {
        String criteria = (String) criteriaJComboBox.getSelectedItem();
        switch (criteria) {
            case BY_DOCUMENT_TYPE_CRITERIA -> docTypeViewer.setEntityToDisplay(documents);
            case BY_EVENT_PERIOD -> eventPeriodViewer.setEntityToDisplay(documents);
            case BY_UNCOMPLETED_EVENTS_FOR_PERIOD -> uncompletedEventsViewer.setEntityToDisplay(documents);
        }
    }

    public String getSelectedCriteria() {
        return (String) criteriaJComboBox.getSelectedItem();
    }

    public Object getLowerDateLimitFromUser() {
        return lowerLimitDate.getModel().getValue();
    }

    public Object getUpperDateLimitFromUser() {
        return upperLimitDate.getModel().getValue();
    }

    public Object getDateLimitFromUser() {
        return limitDate.getModel().getValue();
    }

    public String getDocumentTypeFromUser() {
        return docTypeField.getText();
    }

    private JDatePickerImpl createDatePicker() {
        UtilDateModel model = new UtilDateModel();
        LocalDate date = LocalDate.now();
        model.setDate(date.getYear(), date.getMonthValue() - 1, date.getDayOfMonth());
        model.setSelected(true);
        JDatePanelImpl datePanel = new JDatePanelImpl(model);
        return new JDatePickerImpl(datePanel);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        CardLayout layout = (CardLayout) (cardLayoutInput.getLayout());
        layout.show(cardLayoutInput, (String) e.getItem());
        CardLayout layout2 = (CardLayout) (cardLayoutView.getLayout());
        layout2.show(cardLayoutView, (String) e.getItem());
    }

    private void initInputLayout() {
        cardLayoutInput = new JPanel(new CardLayout());
        lowerLimitDate = createDatePicker();
        upperLimitDate = createDatePicker();
        limitDate = createDatePicker();
        docTypeField = new JTextField();

        JPanel docTypePanel = new JPanel(new GridLayout(2, 1, 10, 5));
        docTypePanel.add(new Label("type"));
        docTypePanel.add(docTypeField);
        JPanel eventPeriodPanel = new JPanel(new GridLayout(2, 2, 10, 5));
        eventPeriodPanel.add(new Label("lower date limit"));
        eventPeriodPanel.add(new Label("upper date limit"));
        eventPeriodPanel.add(lowerLimitDate);
        eventPeriodPanel.add(upperLimitDate);
        JPanel uncompletedEvents = new JPanel(new GridLayout(2, 1, 10, 5));
        uncompletedEvents.add(new Label("date limit"));
        uncompletedEvents.add(limitDate);

        cardLayoutInput.add(docTypePanel, BY_DOCUMENT_TYPE_CRITERIA);
        cardLayoutInput.add(uncompletedEvents, BY_UNCOMPLETED_EVENTS_FOR_PERIOD);
        cardLayoutInput.add(eventPeriodPanel, BY_EVENT_PERIOD);
    }

    private void initViewLayout() {
        cardLayoutView = new JPanel(new CardLayout());
        docTypeViewer = new DocTypeViewer();
        uncompletedEventsViewer = new UncompletedEventsViewer();
        eventPeriodViewer = new EventsViewer();
        cardLayoutView.add(docTypeViewer, BY_DOCUMENT_TYPE_CRITERIA);
        cardLayoutView.add(uncompletedEventsViewer, BY_UNCOMPLETED_EVENTS_FOR_PERIOD);
        cardLayoutView.add(eventPeriodViewer, BY_EVENT_PERIOD);
    }
}
