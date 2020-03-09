package stockNews;

public class InvalidBlacklistException extends RuntimeException {
    public InvalidBlacklistException(String details) {
        super("This blacklist has been moved into an unsafe state: " + details);
    }
}
