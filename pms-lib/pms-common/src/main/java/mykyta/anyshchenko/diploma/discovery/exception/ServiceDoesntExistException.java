package mykyta.anyshchenko.diploma.discovery.exception;

public class ServiceDoesntExistException extends RuntimeException {

    public static final String MESSAGE_PATTERN = "Service with id='%s' doesnt exist";

    public ServiceDoesntExistException(String serviceId) {
        super(String.format(MESSAGE_PATTERN, serviceId));
    }
}
