package marcujurjgr934.repository;



import marcujurjgr934.domain.Assignment;
import marcujurjgr934.validation.ValidationException;
import marcujurjgr934.validation.Validator;

import java.io.*;
import java.util.stream.Collectors;

public class AssignmentFileRepository extends AbstractFileRepository<String, Assignment> {

    public AssignmentFileRepository(Validator<Assignment> validator, String filename) {
        super(validator, filename);
        loadFromFile();
    }

    protected void loadFromFile() {
        try (BufferedReader buffer = new BufferedReader(new FileReader(filename))) {
            buffer.lines().collect(Collectors.toList()).forEach(line -> {
                String[] result = line.split("#");
                Assignment assignment = new Assignment(result[0], result[1], Integer.parseInt(result[2]), Integer.parseInt(result[3]));
                try {
                    super.save(assignment);
                } catch (ValidationException ve) {
                    ve.printStackTrace();
                }
            });
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    protected void writeToFile(Assignment assignment) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true))) {
            bw.write(assignment.getID() + "#" + assignment.getDescription() + "#" + assignment.getDeadline() + "#" + assignment.getStartline() + "\n");
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    protected void writeToFileAll() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename, false))) {
            super.entities.values().forEach(assignment -> {
                try {
                    bw.write(assignment.getID() + "#" + assignment.getDescription() + "#" + assignment.getDeadline() + "#" + assignment.getStartline() + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
