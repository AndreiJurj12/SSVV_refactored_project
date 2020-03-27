package marcujurjgr934.repository;


import marcujurjgr934.domain.Assignment;
import marcujurjgr934.validation.Validator;

public class AssignmentRepository extends AbstractCRUDRepository<String, Assignment> {
    public AssignmentRepository(Validator<Assignment> validator) {
        super(validator);
    }
}

