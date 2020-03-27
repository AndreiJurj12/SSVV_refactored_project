package marcujurjgr934.repository;



import marcujurjgr934.domain.HasID;
import marcujurjgr934.validation.ValidationException;
import marcujurjgr934.validation.Validator;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCRUDRepository<ID, E extends HasID<ID>> implements CRUDRepository<ID, E>{
    protected Map<ID, E> entities;
    private Validator<E> validator;

    public AbstractCRUDRepository(Validator<E> validator) {
        entities = new HashMap<ID, E>();
        this.validator = validator;
    }

    @Override
    public E findOne(ID id) {
        if (id == null) {
            throw new IllegalArgumentException("The ID can't be null! \n");
        }
        else {
            return entities.get(id);
        }
    }

    @Override
    public Iterable<E> findAll() { return entities.values(); }

    @Override
    public E save(E entity) throws ValidationException {
        /*
        try {
            validator.validate(entity);
            return entities.putIfAbsent(entity.getID(), entity);
        }
        catch (ValidationException ve) {
            System.out.println("Entity is not valid! \n");
            return null;
        }
        */
        validator.validate(entity);
        return entities.putIfAbsent(entity.getID(), entity);
    }

    @Override
    public E delete(ID id) {
        if (id == null) {
            throw new IllegalArgumentException("The ID can't be null! \n");
        }
        else {
            return entities.remove(id);
        }
    }

    @Override
    public E update(E entity) throws ValidationException {
        /*
        try {
            validator.validate(entity);
            return entities.replace(entity.getID(), entity);
        }
        catch (ValidationException ve) {
            System.out.println("Entity is not valid! \n");
            return null;
        }
        */
        validator.validate(entity);
        return entities.replace(entity.getID(), entity);
    }
}
