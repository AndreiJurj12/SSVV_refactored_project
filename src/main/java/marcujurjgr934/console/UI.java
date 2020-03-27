package marcujurjgr934.console;


import marcujurjgr934.domain.Assignment;
import marcujurjgr934.domain.Grade;
import marcujurjgr934.domain.Student;
import marcujurjgr934.service.Service;

import java.util.Scanner;

public class UI {
    private Service service;
    private Scanner scanner;

    public UI(Service service) {
        this.service = service;
        this.scanner = new Scanner(System.in);
    }

    private void printMenu() {
        System.out.println("11. Print all students");
        System.out.println("12. Print all assignments");
        System.out.println("13. Print all grades");

        System.out.println("21. Add a new student");
        System.out.println("22. Add a new assignment");
        System.out.println("23. Assign a grade to a student for an assignment");

        System.out.println("31. Delete an existing student");
        System.out.println("32. Delete an existing assignment");

        System.out.println("4. Update student's data");

        System.out.println("5. Extend the deadline of an assignment");

        System.out.println("0. EXIT \n");
    }

    private void uiPrintAllStudents() {
        for(Student student : service.findAllStudents()) {
            System.out.println(student);
        }
    }

    private void uiPrintAllTeme() {
        for(Assignment assignment : service.findAllAssignments()) {
            System.out.println(assignment);
        }
    }

    private void uiPrintAllNote() {
        for(Grade note : service.findAllGrades()) {
            System.out.println(note);
        }
    }

    private void uiSaveStudent() {
        System.out.println("Introduce student's ID:");
        String id = scanner.nextLine();

        System.out.println("Introduce student's name:");
        String name = scanner.nextLine();

        System.out.println("Introduce student's group:");
        int group = 0;
        try {
            group = scanner.nextInt();
        }
        catch (Exception exception) {
            System.out.println("Invalid group value");
            return;
        }

        int resultCode = service.saveStudent(id, name, group);
        if (resultCode == 0) {
            System.out.println("Student successfully added");
        }
        else if (resultCode == -1) {
            System.out.println("Validation failed for the student");
        }
        else {
            System.out.println("Id was already taken");
        }
    }

    private void uiSaveAssignment() {
        System.out.println("Introduce assignment's ID:");
        String id = scanner.nextLine();

        System.out.println("Introduce assignment's description:");
        String description = scanner.nextLine();

        System.out.println("Introduce assignment's deadline:");
        int deadline = 0;
        try {
            deadline = scanner.nextInt();
        }
        catch (Exception exception) {
            System.out.println("Invalid deadline value");
            return;
        }

        System.out.println("Introduce assignment's startline:");
        int startline = 0;
        try {
            startline = scanner.nextInt();
        }
        catch (Exception exception) {
            System.out.println("Invalid startline value");
            return;
        }

        int resultCode = service.saveAssignment(id, description, deadline, startline);
        if (resultCode == 0) {
            System.out.println("Assignment successfully added");
        }
        else if (resultCode == -1) {
            System.out.println("Validation failed for the assignment");
        }
        else {
            System.out.println("Id was already taken");
        }
    }

    public void uiSaveGrade() {
        System.out.println("Introduce student's ID:");
        String idStudent = scanner.nextLine();

        System.out.println("Introduce assignment's ID:");
        String idAssignment = scanner.nextLine();

        System.out.println("Introduce grade's value:");
        double gradeValue;
        try {
            String line = scanner.nextLine();
            gradeValue = Double.parseDouble(line);
        }
        catch (Exception exception) {
            System.out.println("Invalid grade value");
            return;
        }

        System.out.println("Introduce grade's delivery week:");
        int deliveryWeek;
        try {
            String line = scanner.nextLine();
            deliveryWeek = Integer.parseInt(line);
        }
        catch (Exception exception) {
            System.out.println("Invalid delivery week value");
            return;
        }

        System.out.println("Introduce assignment's feedback:");
        String feedback = scanner.nextLine();

        int resultCode = service.saveNota(idStudent, idAssignment, gradeValue, deliveryWeek, feedback);
        if (resultCode == 0) {
            service.createStudentFile(idStudent, idAssignment);
            System.out.println("Grade successfully added");
        }
        else if (resultCode == -1) {
            System.out.println("Validation failed for the grade");
        }
        else if (resultCode == -2) {
            System.out.println("Id was already taken");
        }
        else {
            System.out.println("Invalid id for student or assignment");
        }
    }

    private void uiDeleteStudent() {
        System.out.println("Introduce student's ID:");
        String id = scanner.nextLine();

        int resultCode = service.deleteStudent(id);
        if (resultCode == 0) {
            System.out.println("Student successfully deleted");
        }
        else if (resultCode == -1) {
            System.out.println("Student's id doesn't exist");
        }
    }

    public void uiDeleteAssignment() {
        System.out.println("Introduce assignment's ID:");
        String id = scanner.nextLine();

        int resultCode = service.deleteAssignment(id);
        if (resultCode == 0) {
            System.out.println("Assigment successfully deleted");
        }
        else if (resultCode == -1) {
            System.out.println("Assigment's id doesn't exist");
        }
    }

    public void uiUpdateStudent() {
        System.out.println("Introduce student's ID:");
        String id = scanner.nextLine();

        System.out.println("Introduce student's new name:");
        String newName = scanner.nextLine();

        System.out.println("Introduce student's new group:");
        int newGroup = 0;
        try {
            newGroup = scanner.nextInt();
        }
        catch (Exception exception) {
            System.out.println("Invalid new group value");
            return;
        }

        int resultCode = service.updateStudent(id, newName, newGroup);
        if (resultCode == 0) {
            System.out.println("Student successfully updated");
        }
        else if (resultCode == -1) {
            System.out.println("Validation failed for the student");
        }
        else {
            System.out.println("Student's id doesn't exist");
        }
    }

    private void uiExtendDeadline() {
        System.out.println("Introduce assignment's ID:");
        String id = scanner.nextLine();

        System.out.println("Introduce no of weeks to add for the deadline:");
        int addedWeeksDeadline = 0;
        try {
            addedWeeksDeadline = scanner.nextInt();
        }
        catch (Exception exception) {
            System.out.println("Invalid no of weeks value");
            return;
        }

        int resultCode = service.extendDeadline(id, addedWeeksDeadline);
        if (resultCode == 0) {
            System.out.println("Deadline successfully extended");
        }
        else if (resultCode == -1) {
            System.out.println("Validation failed for the assignment update");
        }
        else if (resultCode == -2) {
            System.out.println("Assigment's id doesn't exist");
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        int cmd = -1;

        while(cmd != 0) {
            printMenu();
            System.out.println("Introduceti comanda: ");
            try {
                cmd = scanner.nextInt();
            }
            catch (Exception exception) {
                System.out.println("Invalid command value. Try again");
                continue;
            }

            switch(cmd) {
                case 11:
                    uiPrintAllStudents();
                    break;
                case 12:
                    uiPrintAllTeme();
                    break;
                case 13:
                    uiPrintAllNote();
                    break;
                case 21:
                    uiSaveStudent();
                    break;
                case 22:
                    uiSaveAssignment();
                    break;
                case 23:
                    uiSaveGrade();
                    break;
                case 31:
                    uiDeleteStudent();
                    break;
                case 32:
                    uiDeleteAssignment();
                    break;
                case 4:
                    uiUpdateStudent();
                    break;
                case 5:
                    uiExtendDeadline();
                    break;
                case 0:
                    cmd = 0;
                    break;
            }
        }
    }
}
