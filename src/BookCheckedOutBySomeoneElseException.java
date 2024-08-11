public class BookCheckedOutBySomeoneElseException extends Exception {
    public BookCheckedOutBySomeoneElseException(String errorMessage) {
        super(errorMessage);
    }
}

