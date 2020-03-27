package marcujurjgr934.service;



import marcujurjgr934.domain.Assignment;
import marcujurjgr934.domain.Grade;
import marcujurjgr934.domain.Pair;
import marcujurjgr934.domain.Student;
import marcujurjgr934.repository.AssignmentXMLRepository;
import marcujurjgr934.repository.GradeXMLRepository;
import marcujurjgr934.repository.StudentXMLRepository;
import marcujurjgr934.validation.ValidationException;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class Service {
    private StudentXMLRepository studentXmlRepo;
    private AssignmentXMLRepository assignmentXmlRepo;
    private GradeXMLRepository gradeXmlRepo;

    public Service(StudentXMLRepository studentXmlRepo, AssignmentXMLRepository assignmentXmlRepo, GradeXMLRepository gradeXmlRepo) {
        this.studentXmlRepo = studentXmlRepo;
        this.assignmentXmlRepo = assignmentXmlRepo;
        this.gradeXmlRepo = gradeXmlRepo;
    }

    public Iterable<Student> findAllStudents() { return studentXmlRepo.findAll(); }

    public Iterable<Assignment> findAllAssignments() { return assignmentXmlRepo.findAll(); }

    public Iterable<Grade> findAllGrades() { return gradeXmlRepo.findAll(); }

    /**
     * @return 0- if the given entity is saved; -1 if the validation failed; -2 if the id was already found
     **/
    public int saveStudent(String id, String name, int group) {
        Student student = new Student(id, name, group);
        try {
            Student result = studentXmlRepo.save(student);
            if (result == null) {
                return 0;
            }
            return -2;
        }
        catch (ValidationException exception) {
            return -1;
        }
    }

    /**
     * @return 0- if the given entity is saved; -1 if the validation failed; -2 if the id was already found
     **/
    public int saveAssignment(String id, String description, int deadline, int startline) {
        Assignment assignment = new Assignment(id, description, deadline, startline);
        try {
            Assignment result = assignmentXmlRepo.save(assignment);
            if (result == null) {
                return 0;
            }
            return -2;
        }
        catch (ValidationException exception) {
            return -1;
        }
    }

    /**
     * @return 0 if the given entity is saved; -1 if the validation failed; -2 if the id was already found;
     *          -3 if either the student or the assignment doesn't exist
     **/
    public int saveNota(String idStudent, String idAssignment, double gradeValue, int deliveryWeek, String feedback) {
        if (studentXmlRepo.findOne(idStudent) == null || assignmentXmlRepo.findOne(idAssignment) == null) {
            return -3;
        }
        else {
            int deadline = assignmentXmlRepo.findOne(idAssignment).getDeadline();

            if (deliveryWeek - deadline > 2) {
                gradeValue =  1;
            } else {
                gradeValue =  gradeValue - 2.5 * (deliveryWeek - deadline);
            }
            Grade grade = new Grade(new Pair<>(idStudent, idAssignment), gradeValue, deliveryWeek, feedback);
            try {
                Grade result = gradeXmlRepo.save(grade);
                if (result == null) {
                    return 0;
                }
                return -2;
            }
            catch (ValidationException exception) {
                return -1;
            }
        }
    }

    /**
     * @return 0 if the given entity is deleted; -1 if no entity is found with that id
     **/
    public int deleteStudent(String id) {
        Student result = studentXmlRepo.delete(id);

        if (result == null) {
            return -1;
        }
        return 0;
    }

    /**
     * @return 0 if the given entity is deleted; -1 if no entity is found with that id
     **/
    public int deleteAssignment(String id) {
        Assignment result = assignmentXmlRepo.delete(id);

        if (result == null) {
            return -1;
        }
        return 0;
    }

    /**
     * @return 0 if the given entity is updated; -1 if the validation failed; -2 if the id was not found;
     **/
    public int updateStudent(String id, String newName, int newGroup) {
        Student studentNou = new Student(id, newName, newGroup);
        try {
            Student result = studentXmlRepo.update(studentNou);
            if (result == null) {
                return -2;
            }
            return 0;
        }
        catch (ValidationException exception) {
            return -1;
        }
    }

    /**
     * @return 0 if the given entity is updated; -1 if the validation failed; -2 if the id was not found;
     **/
    public int updateAssignment(String id, String newDescription, int newDeadline, int newStartline) {
        Assignment assignmentNoua = new Assignment(id, newDescription, newDeadline, newStartline);
        try {
            Assignment result = assignmentXmlRepo.update(assignmentNoua);
            if (result == null) {
                return -2;
            }
            return 0;
        }
        catch (ValidationException exception) {
            return -1;
        }
    }

    /**
     * @return 0 if the given entity is updated; -1 if the validation failed; -2 if the id was not found;
     **/
    public int extendDeadline(String id, int addedWeeksDeadline) {
        Assignment assignment = assignmentXmlRepo.findOne(id);

        if (assignment != null) {
            LocalDate date = LocalDate.now();
            WeekFields weekFields = WeekFields.of(Locale.getDefault());
            int currentWeek = date.get(weekFields.weekOfWeekBasedYear());

            if (currentWeek >= 39) {
                currentWeek = currentWeek - 39;
            } else {
                currentWeek = currentWeek + 12;
            }

            if (currentWeek <= assignment.getDeadline()) {
                int newDeadline = assignment.getDeadline() + addedWeeksDeadline;
                return updateAssignment(assignment.getID(), assignment.getDescription(), newDeadline, assignment.getStartline());
            }
        }
        return -2;
    }

    public void createStudentFile(String idStudent, String idAssignment) {
        Grade grade = gradeXmlRepo.findOne(new Pair<>(idStudent, idAssignment));

        gradeXmlRepo.createFile(grade);
    }
}
