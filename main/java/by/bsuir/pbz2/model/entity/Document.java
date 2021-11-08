package by.bsuir.pbz2.model.entity;

import java.time.LocalDate;
import java.util.Objects;
import java.util.StringJoiner;

public class Document extends AbstractEntity {
    private int documentId;
    private String typeOfDocument;
    private LocalDate dateOfCreation;
    private String content;
    private String event;
    private Performer performer;
    private LocalDate dateOfCompletion;
    private Boolean status;

    public Document() {
    }

    public int getDocumentId() {
        return documentId;
    }

    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }

    public String getTypeOfDocument() {
        return typeOfDocument;
    }

    public void setTypeOfDocument(String typeOfDocument) {
        this.typeOfDocument = typeOfDocument;
    }

    public LocalDate getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(LocalDate dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public Performer getPerformer() {
        return performer;
    }

    public void setPerformer(Performer performer) {
        this.performer = performer;
    }

    public LocalDate getDateOfCompletion() {
        return dateOfCompletion;
    }

    public void setDateOfCompletion(LocalDate dateOfCompletion) {
        this.dateOfCompletion = dateOfCompletion;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Document document = (Document) o;

        if (documentId != document.documentId) return false;
        if (!Objects.equals(typeOfDocument, document.typeOfDocument))
            return false;
        if (!Objects.equals(dateOfCreation, document.dateOfCreation))
            return false;
        if (!Objects.equals(content, document.content)) return false;
        if (!Objects.equals(event, document.event)) return false;
        if (!Objects.equals(performer, document.performer)) return false;
        if (!Objects.equals(dateOfCompletion, document.dateOfCompletion))
            return false;
        return Objects.equals(status, document.status);
    }

    @Override
    public int hashCode() {
        int result = (int) (documentId ^ (documentId >>> 32));
        result = 31 * result + (typeOfDocument != null ? typeOfDocument.hashCode() : 0);
        result = 31 * result + (dateOfCreation != null ? dateOfCreation.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (event != null ? event.hashCode() : 0);
        result = 31 * result + (performer != null ? performer.hashCode() : 0);
        result = 31 * result + (dateOfCompletion != null ? dateOfCompletion.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Document.class.getSimpleName() + "[", "]")
                .add("documentId=" + documentId)
                .add("typeOfDocument='" + typeOfDocument + "'")
                .add("dateOfCreation=" + dateOfCreation)
                .add("content='" + content + "'")
                .add("event='" + event + "'")
                .add("performer=" + performer)
                .add("dateOfCompletion=" + dateOfCompletion)
                .add("note='" + status + "'")
                .toString();
    }
}
