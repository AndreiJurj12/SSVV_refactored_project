package marcujurjgr934.validation;


import marcujurjgr934.domain.Student;

public class StudentValidator implements Validator<Student> {
    public void validate(Student student) throws ValidationException {
        if (student.getID() == null || student.getID().equals("")) {
            throw new ValidationException("Invalid ID! \n");
        }
        if (student.getName() == null || student.getName().equals("")) {
            throw new ValidationException("Invalid name! \n");
        }
        if (student.getGroup() <= 110 || student.getGroup() >= 938) {
            throw new ValidationException("Invalid group! \n");
        }
//        if (student.getEmail() == null || student.getEmail().equals("")) {
//            throw new ValidationException("Invalid email! \n");
//        }
//        if (student.getProfessorName() == null || student.getProfessorName().equals("")) {
//            throw new ValidationException("Invalid professorName! \n");
//        }
    }
}

