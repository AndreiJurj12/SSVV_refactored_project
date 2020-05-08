package marcujurjgr934.service;

import marcujurjgr934.domain.Assignment;
import marcujurjgr934.domain.Grade;
import marcujurjgr934.domain.Student;
import marcujurjgr934.repository.AssignmentXMLRepository;
import marcujurjgr934.repository.GradeXMLRepository;
import marcujurjgr934.repository.StudentXMLRepository;
import marcujurjgr934.validation.AssignmentValidator;
import marcujurjgr934.validation.GradeValidator;
import marcujurjgr934.validation.StudentValidator;
import marcujurjgr934.validation.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ServiceIncrementalIntegrationTest {
    private Service service;

    private String idStudentForGrade = "idSG1";
    private String idAssignmentForGrade = "idSA1";

    @Before
    public void initializeFields () {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Assignment> temaValidator = new AssignmentValidator();
        Validator<Grade> notaValidator = new GradeValidator();

        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "students_test.xml");
        AssignmentXMLRepository fileRepository2 = new AssignmentXMLRepository(temaValidator, "assignments_test.xml");
        GradeXMLRepository fileRepository3 = new GradeXMLRepository(notaValidator, "grades_test.xml");

        service = new Service(fileRepository1, fileRepository2, fileRepository3);
    }

    @After
    public void cleanXMLFiles() {
        try {
            PrintWriter printWriter = new PrintWriter("students_test.xml");
            printWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>");
            printWriter.write("<entities></entities>");
            printWriter.close();

            printWriter = new PrintWriter("assignments_test.xml");
            printWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>");
            printWriter.write("<entities></entities>");
            printWriter.close();

            printWriter = new PrintWriter("grades_test.xml");
            printWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>");
            printWriter.write("<entities></entities>");
            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void saveStudentSuccess()  {
        String idStudent = this.idStudentForGrade;
        String name = "John";
        int group = 920;
        String email = "john@gmail.com";
        String professorName = "Teacher1";

        int returnCode = service.saveStudent(idStudent, name, group, email, professorName);
        assertEquals(0, returnCode);
    }

    @Test
    public void saveAssignmentSuccessWithStudentSuccess() {
        saveStudentSuccess();

        String idAssignment = this.idAssignmentForGrade;
        String description = "Description";
        int startline = 5;
        int deadline = 7;

        int returnCode = service.saveAssignment(idAssignment, description, deadline, startline);
        assertEquals(0, returnCode);
    }

    @Test
    public void saveGradeSuccessWithAssignmentAndStudentSuccess() {
        saveAssignmentSuccessWithStudentSuccess();

        String idStudent = this.idStudentForGrade;
        String idAssignment = this.idAssignmentForGrade;
        double gradeValue = 7.5;
        int deliveryWeek = 7;
        String feedback = "feedback";

        int returnCode = service.saveNota(idStudent, idAssignment, gradeValue, deliveryWeek,feedback);
        assertEquals(0, returnCode);

        List<Grade> currentGrades = new ArrayList<Grade>((Collection<? extends Grade>) service.findAllGrades());
        assertEquals(1, currentGrades.size());

        Grade savedGrade = currentGrades.get(0);
        assertEquals(idStudent, savedGrade.getID().getFirst());
        assertEquals(idAssignment, savedGrade.getID().getSecond());

        double epsilon = 0.001;
        assertEquals(gradeValue, savedGrade.getGradeValue(), epsilon);

        assertEquals(deliveryWeek, savedGrade.getDeliveryWeek());
        assertEquals(feedback, savedGrade.getFeedback());
    }
}
