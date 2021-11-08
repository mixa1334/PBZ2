package by.bsuir.pbz2.model.entity;

import java.util.Objects;
import java.util.StringJoiner;

public class Performer extends AbstractEntity {
    private int performerId;
    private String position;
    private String subdivision;
    private String fio;

    public Performer() {
    }

    public int getPerformerId() {
        return performerId;
    }

    public void setPerformerId(int performerId) {
        this.performerId = performerId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSubdivision() {
        return subdivision;
    }

    public void setSubdivision(String subdivision) {
        this.subdivision = subdivision;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Performer performer = (Performer) o;

        if (performerId != performer.performerId) return false;
        if (!Objects.equals(position, performer.position)) return false;
        if (!Objects.equals(subdivision, performer.subdivision))
            return false;
        return Objects.equals(fio, performer.fio);
    }

    @Override
    public int hashCode() {
        int result = (int) (performerId ^ (performerId >>> 32));
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (subdivision != null ? subdivision.hashCode() : 0);
        result = 31 * result + (fio != null ? fio.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Performer.class.getSimpleName() + "[", "]")
                .add("performerId=" + performerId)
                .add("position='" + position + "'")
                .add("subdivision='" + subdivision + "'")
                .add("fio='" + fio + "'")
                .toString();
    }
}
