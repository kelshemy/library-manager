public class BookNotCheckedOutException extends Exception {
    public BookNotCheckedOutException(String errorMessage) {
        super(errorMessage);
    }
}

