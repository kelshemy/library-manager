public class BookRepository {
    Shelf[] shelves = new Shelf[10];
    public BookRepository() {
        for(int i=0; i<10; i++) {
            shelves[i] = new Shelf(null, null, 0, Shelf.SortCriteria.ISBN);
        }
    }
    public void checkInBook(long checkedInISBN) throws InvalidISBNException, BookDoesNotExistException {
        String isbn = String.valueOf(checkedInISBN);
        char firstChar = isbn.charAt(0);
        if (!Character.isDigit(firstChar) || isbn.length() > 13) {
            throw new InvalidISBNException("Checked out book's ISBN is invalid.");
        }
        firstChar -= 48; //convert the character to its actual digit form
        Book current = shelves[firstChar].headBook;
        while (current != null) {
            if (current.getISBN() == checkedInISBN) {
                current.setCheckedOut(false);
                System.out.printf("%s by %s has been checked in\n",current.getName(), current.getAuthor());
                return;
            }
            current = current.getNextBook();
        }
        throw new BookDoesNotExistException("Book does not exist");
    }

    public void checkOutBook(long checkedOutISBN, long checkOutUserID, Date dueDate) throws InvalidISBNException, InvalidUserIDException, BookAlreadyCheckedOutException {
        String y = String.valueOf(checkOutUserID);
        if (y.length() > 10)
            throw new InvalidUserIDException("Checked out user ID is invalid.");
        String isbn = String.valueOf(checkedOutISBN);
        char firstChar = isbn.charAt(0);
        if(isbn.length() < 13)
            firstChar = '0';
        if (!Character.isDigit(firstChar) || isbn.length() > 13) {
            throw new InvalidISBNException("Checked out book's ISBN is invalid.");
        }
        firstChar -= 48; //convert the character to its actual digit form
        Book current = shelves[firstChar].headBook;
            while (current != null) {
                if (current.getISBN() == checkedOutISBN) {
                    if (current.isCheckedOut()) {
                        System.out.printf("Book has already been checked out by %d", current.getCheckOutUserID());
                        throw new BookAlreadyCheckedOutException("\n");
                    }
                    current.setCheckedOut(true);
                    current.setCheckOutUserID(checkOutUserID);
                    current.setDueDate(dueDate);
                    System.out.printf("%s has been checked out by %d and must be returned by %d/%d/%d\n",current.getName(), current.getCheckOutUserID(), dueDate.month, dueDate.day, dueDate.year);
                    return;
                }
            current = current.getNextBook();
        }
    }

    public void addBook(long addISBN, String addName, String addAuthor, String addGenre, int addYear, Book.Condition addCondition) throws InvalidISBNException, BookAlreadyExistsException {
        String isbn = String.valueOf(addISBN);
        char firstChar = isbn.charAt(0);
        if(isbn.length() < 13)
            firstChar = '0';
        if (!Character.isDigit(firstChar) || isbn.length() > 13) {
            throw new InvalidISBNException("Checked out book's ISBN is invalid.");
        }
        firstChar -= 48; //convert the character to its actual digit form
        Book insert = new Book(addName, addAuthor, addGenre, addCondition, addISBN, 0, addYear, null, null, false);
        shelves[firstChar].addBook(insert);
        System.out.printf("%s by %s has been successfully added to the book repository.\n", insert.getName(), insert.getAuthor());
    }

    public void removeBook(long removeISBN) throws InvalidISBNException, BookDoesNotExistException {
        String isbn = String.valueOf(removeISBN);
        char firstChar = isbn.charAt(0);
        if(isbn.length() < 13)
            firstChar = '0';
        if (!Character.isDigit(firstChar) || isbn.length() > 13) {
            throw new InvalidISBNException("Checked out book's ISBN is invalid.");
        }
        firstChar -= 48; //convert the character to its actual digit form
        shelves[firstChar].removeBook(removeISBN);

    }

    public void sortShelf(int shelfInd, String sortCriteria) throws InvalidSortCriteriaException {
        if(Shelf.SortCriteria.valueOf(sortCriteria) == null) {
            throw new InvalidSortCriteriaException("The sort criteria has no corresponding enum.");
        }
        shelves[shelfInd].sort(Shelf.SortCriteria.valueOf(sortCriteria));
    }
}

