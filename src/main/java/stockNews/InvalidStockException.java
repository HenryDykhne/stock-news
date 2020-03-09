package stockNews;

public class InvalidStockException extends RuntimeException {
    public InvalidStockException(String details) {
        super("This stock has been moved into an unsafe state: " + details);
    }
}
