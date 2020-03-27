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
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class ServiceTest {
    @Test
    public void saveAssignmentSuccess() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Assignment> temaValidator = new AssignmentValidator();
        Validator<Grade> notaValidator = new GradeValidator();

        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "students.xml");
        AssignmentXMLRepository fileRepository2 = new AssignmentXMLRepository(temaValidator, "teme_test.xml");
        GradeXMLRepository fileRepository3 = new GradeXMLRepository(notaValidator, "grades.xml");

        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);

        String new_id = UUID.randomUUID().toString();

        int response = service.saveAssignment(new_id, "description", 7, 5);
        assertEquals(response, 0);
    }

    @Test
    public void saveAssignmentFailure()  {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Assignment> temaValidator = new AssignmentValidator();
        Validator<Grade> notaValidator = new GradeValidator();

        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "students.xml");
        AssignmentXMLRepository fileRepository2 = new AssignmentXMLRepository(temaValidator, "teme_test.xml");
        GradeXMLRepository fileRepository3 = new GradeXMLRepository(notaValidator, "grades.xml");

        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);

        String new_id = UUID.randomUUID().toString();
        int response = service.saveAssignment(new_id, "description", 4, 5);
        assertEquals(response, -1);
    }

}
