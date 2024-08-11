public class BookAlreadyCheckedOutException extends Exception {
        public BookAlreadyCheckedOutException(String errorMessage) {
            super(errorMessage);
        }
    }

