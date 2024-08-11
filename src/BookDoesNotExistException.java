public class BookDoesNotExistException extends Exception {
    public BookDoesNotExistException(String errorMessage) {

        super(errorMessage);
    }
}
