package marcujurjgr934.repository;


import marcujurjgr934.domain.Grade;
import marcujurjgr934.domain.Pair;
import marcujurjgr934.validation.Validator;

public class GradeRepository extends AbstractCRUDRepository<Pair<String, String>, Grade> {
    public GradeRepository(Validator<Grade> validator) {
        super(validator);
    }
}
