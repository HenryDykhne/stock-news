package stockNews.roles;

public class InvalidPrivilageException extends RuntimeException {
    public InvalidPrivilageException(String details) {
        super("The selected actor does not have the right to preform this action: " + details);
    }
}
