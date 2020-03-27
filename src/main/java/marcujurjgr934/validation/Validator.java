package marcujurjgr934.validation;

public interface Validator<E> {
    void validate(E entity) throws ValidationException;
}
