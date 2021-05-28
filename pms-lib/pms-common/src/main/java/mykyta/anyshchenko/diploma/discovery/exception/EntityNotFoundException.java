package mykyta.anyshchenko.diploma.discovery.exception;

public class EntityNotFoundException extends RuntimeException {
    private final static String MESSAGE_PATTERN = "There is no entity of type=%s with id =%s";

    public EntityNotFoundException(String entityId, Class<?> entityClass) {
        super(String.format(MESSAGE_PATTERN, entityClass.getName(), entityId));
    }
}
