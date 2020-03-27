package marcujurjgr934.validation;

import marcujurjgr934.domain.Assignment;

public class AssignmentValidator implements Validator<Assignment> {
    public void validate(Assignment assignment) throws ValidationException {
        if (assignment.getID() == null || assignment.getID().equals("")) {
            throw new ValidationException("Invalid ID! \n");
        }
        if (assignment.getDescription() == null || assignment.getDescription().equals("")) {
            throw new ValidationException("Invalid description! \n");
        }
        if (assignment.getDeadline() < 1 || assignment.getDeadline() > 14) {
            throw new ValidationException("Invalid deadline! \n");
        }
        if (assignment.getStartline() < 1 || assignment.getStartline() > 14) {
            throw new ValidationException("Invalid startline! \n");
        }
        if (assignment.getDeadline() < assignment.getStartline()) {
            throw new ValidationException("Deadline week is lower than startline week! \n");
        }
    }
}

