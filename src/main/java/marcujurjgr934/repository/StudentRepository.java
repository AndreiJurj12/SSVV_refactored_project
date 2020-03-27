package marcujurjgr934.repository;


import marcujurjgr934.domain.Student;
import marcujurjgr934.validation.Validator;

public class StudentRepository extends AbstractCRUDRepository<String, Student> {
    public StudentRepository(Validator<Student> validator) {
        super(validator);
    }
}

