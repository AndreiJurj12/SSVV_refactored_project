package marcujurjgr934.service;

import marcujurjgr934.domain.Assignment;
import marcujurjgr934.domain.Grade;
import marcujurjgr934.domain.Pair;
import marcujurjgr934.domain.Student;
import marcujurjgr934.repository.AssignmentXMLRepository;
import marcujurjgr934.repository.GradeXMLRepository;
import marcujurjgr934.repository.StudentRepository;
import marcujurjgr934.repository.StudentXMLRepository;
import marcujurjgr934.validation.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ServiceIntegrationTest {
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


        // add sample data for addGrade
        int returnCode = service.saveStudent(idStudentForGrade, "name1", 910, "email", "professor");
        assertEquals(0, returnCode);
        returnCode = service.saveAssignment(idAssignmentForGrade, "description", 7, 5);
        assertEquals(0, returnCode);
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
        String id = "id1";
        String name = "John";
        int group = 920;
        String email = "john@gmail.com";
        String professorName = "Teacher1";

        int returnCode = service.saveStudent(id, name, group, email, professorName);
        assertEquals(0, returnCode);
    }


    @Test
    public void saveAssignmentSuccess() {
        String new_id = "1";
        int response = service.saveAssignment(new_id, "description", 7, 5);
        assertEquals(response, 0);
    }

    @Test
    public void saveGradeSuccess() {
        /*We have special data already for student and assignment*/
        String idStudent = this.idStudentForGrade;
        String idAssignment = this.idAssignmentForGrade;

        int response = service.saveNota(idStudent, idAssignment, 7.5, 6, "feedback");
        assertEquals(0, response);

        List<Grade> currentGrades = new ArrayList<Grade>((Collection<? extends Grade>) service.findAllGrades());
        assertEquals(1, currentGrades.size());
        Pair<String, String> expectedId = new Pair<>(this.idStudentForGrade, this.idAssignmentForGrade);
        assertEquals(expectedId, currentGrades.get(0).getID());
    }

    @Test
    public void saveStudentAssignmentGradeIntegration() {
        saveStudentSuccess();
        saveAssignmentSuccess();
        saveGradeSuccess();
    }
}
