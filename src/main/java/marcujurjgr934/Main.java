package marcujurjgr934;


import marcujurjgr934.console.UI;
import marcujurjgr934.domain.Assignment;
import marcujurjgr934.domain.Grade;
import marcujurjgr934.domain.Student;
import marcujurjgr934.repository.AssignmentXMLRepository;
import marcujurjgr934.repository.GradeXMLRepository;
import marcujurjgr934.repository.StudentXMLRepository;
import marcujurjgr934.service.Service;
import marcujurjgr934.validation.AssignmentValidator;
import marcujurjgr934.validation.GradeValidator;
import marcujurjgr934.validation.StudentValidator;
import marcujurjgr934.validation.Validator;

public class Main {
    public static void main(String[] args) {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Assignment> temaValidator = new AssignmentValidator();
        Validator<Grade> notaValidator = new GradeValidator();

        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "students.xml");
        AssignmentXMLRepository fileRepository2 = new AssignmentXMLRepository(temaValidator, "assignments.xml");
        GradeXMLRepository fileRepository3 = new GradeXMLRepository(notaValidator, "grades.xml");

        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);
        UI consola = new UI(service);
        consola.run();

        //PENTRU GUI
        // de avut un check: daca profesorul introduce sau nu saptamana la timp
        // daca se introduce nota la timp, se preia saptamana din sistem
        // altfel, se introduce de la tastatura
    }
}
