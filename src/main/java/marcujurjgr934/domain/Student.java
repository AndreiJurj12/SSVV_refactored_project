package marcujurjgr934.domain;

import java.util.Objects;

public class Student implements HasID<String> {
    private String idStudent;
    private String name;
    private int group;
    private String email;
    private String professorName;

    public Student(String idStudent, String name, int group, String email, String professorName) {
        this.idStudent = idStudent;
        this.name = name;
        this.group = group;
        this.email = email;
        this.professorName = professorName;
    }

    @Override
    public String getID() { return idStudent; }

    @Override
    public void setID(String idStudent) { this.idStudent = idStudent; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(idStudent, student.idStudent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idStudent);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Student{" +
                "idStudent='" + idStudent + '\'' +
                ", name='" + name + '\'' +
                ", group=" + group +
                ", email='" + email + '\'' +
                ", professorName='" + professorName + '\'' +
                '}';
    }
}

