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
