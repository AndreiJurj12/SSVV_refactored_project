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
import org.junit.*;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.UUID;

import static org.junit.Assert.*;

public class ServiceTest {
    private Service service;

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
    public void saveStudent_TC1() {
        int returnCode = service.saveStudent(null, "name1", 500, "email1", "professorName1");
        assertEquals(returnCode, -1);
    }

    @Test
    public void saveStudent_TC2() {
        int returnCode = service.saveStudent("", "name1", 500, "email1", "professorName1");
        assertEquals(returnCode, -1);
    }

    @Test
    public void saveStudent_TC3() {
        int returnCode = service.saveStudent("id1", "name1", 100, "email1", "professorName1");
        assertEquals(returnCode, -1);
    }

    @Test
    public void saveStudent_TC4() {
        int returnCode = service.saveStudent("id1", "name1", 1000, "email1", "professorName1");
        assertEquals(returnCode, -1);
    }

    @Test
    public void saveStudent_TC5() {
        int returnCode = service.saveStudent("id1", null, 500, "email1", "professorName1");
        assertEquals(returnCode, -1);
    }

    @Test
    public void saveStudent_TC6() {
        int returnCode = service.saveStudent("id1", "", 500, "email1", "professorName1");
        assertEquals(returnCode, -1);
    }

    @Test
    public void saveStudent_TC7() {
        int returnCode = service.saveStudent("id1", "name1", 500, null, "professorName1");
        assertEquals(returnCode, -1);
    }

    @Test
    public void saveStudent_TC8() {
        int returnCode = service.saveStudent("id1", "name1", 500, "", "professorName1");
        assertEquals(returnCode, -1);
    }

    @Test
    public void saveStudent_TC9() {
        int returnCode = service.saveStudent("id1", "name1", 500, "email1", null);
        assertEquals(returnCode, -1);
    }

    @Test
    public void saveStudent_TC10() {
        int returnCode = service.saveStudent("id1", "name1", 500, "email1", "");
        assertEquals(returnCode, -1);
    }

    @Test
    public void saveStudent_TC11() {
        int returnCode = service.saveStudent("id1", "name1", -1, "email1", "professorName1");
        assertEquals(returnCode, -1);
    }

    @Test
    public void saveStudent_TC12() {
        int returnCode = service.saveStudent("id1", "name1", 500, "email1", "professorName1");
        assertEquals(returnCode, 0);
    }

    @Test
    public void saveStudent_TC13() {
        int returnCode = service.saveStudent("id1", "name1", 500, "email1", "professorName1");
        assertEquals(returnCode, 0);

        returnCode = service.saveStudent("id1", "name2", 500, "email2", "professorName2");
        assertEquals(returnCode, -2);
    }

    @Test
    public void saveStudent_TC14() {
        int returnCode = service.saveStudent("id1", "name1", 0, "email1", "professorName1");
        assertEquals(returnCode, -1);
    }


    @Test
    public void saveAssignmentSuccess() {
        String new_id = "1";
        int response = service.saveAssignment(new_id, "description", 7, 5);
        assertEquals(response, 0);
    }

    @Test
    public void saveAssignmentFailure()  {
        String new_id = "1";
        int response = service.saveAssignment(new_id, "description", 4, 5);
        assertEquals(response, -1);
    }

}
