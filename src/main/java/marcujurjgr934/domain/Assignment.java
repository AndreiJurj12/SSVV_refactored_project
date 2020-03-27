package marcujurjgr934.domain;

import java.util.Objects;

public class Assignment implements HasID<String> {
    private String idAssignment;
    private String description;
    private int deadline;
    private int startline;

    public Assignment(String idAssignment, String description, int deadline, int startline) {
        this.idAssignment = idAssignment;
        this.description = description;
        this.deadline = deadline;
        this.startline = startline;
    }

    @Override
    public String getID() { return idAssignment; }

    @Override
    public void setID(String idAssignment) { this.idAssignment = idAssignment; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public int getDeadline() { return deadline; }

    public void setDeadline(int deadline) { this.deadline = deadline; }

    public int getStartline() { return startline; }

    public void setStartline(int startline) { this.startline = startline; }

    @Override
    public String toString() {
        return "Assignment{" + "id='" + idAssignment + "', description='" + description + "', deadline=" + deadline +
                ", startline=" + startline + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Assignment assignment = (Assignment) o;
        return Objects.equals(idAssignment, assignment.idAssignment);
    }

    @Override
    public int hashCode() { return Objects.hash(idAssignment); }
}
