package marcujurjgr934.validation;

import marcujurjgr934.domain.Grade;

public class GradeValidator implements Validator<Grade> {
    public void validate(Grade grade) throws ValidationException {
        if (grade.getID().getFirst() == null || grade.getID().getFirst().equals("")) {
            throw new ValidationException("Invalid ID Student! \n");
        }
        if (grade.getID().getSecond() == null || grade.getID().getSecond().equals("")) {
            throw new ValidationException("Invalid ID Assignment! \n");
        }
        if (grade.getGradeValue() < 0 || grade.getGradeValue() > 10) {
            throw new ValidationException("Invalid grade value! \n");
        }
        if (grade.getDeliveryWeek() < 0) {
            throw new ValidationException("Invalid Delivery Week! \n");
        }
    }
}
