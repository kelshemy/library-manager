public class Shelf {

    enum SortCriteria {
        ISBN, NAME, AUTHOR, GENRE, YEAR, CONDITION;
    }

    Book headBook;
    Book tailBook;
    int length;
    SortCriteria shelfSortCriteria;

    public Shelf(Book headBook, Book tailBook, int length, SortCriteria shelfSortCriteria) {
        this.headBook = headBook;
        this.tailBook = tailBook;
        this.length = length;
        this.shelfSortCriteria = shelfSortCriteria;
    }

    public void addBook(Book addedBook) throws BookAlreadyExistsException {
        if (headBook == null) {
            headBook = addedBook;
            tailBook = addedBook;
            this.length = 1;
            return;
        }
        Book current;
        switch (this.shelfSortCriteria) {
            case ISBN:
                current = headBook;
                if (headBook.getISBN() == addedBook.getISBN()) {
                    throw new BookAlreadyExistsException("A book with the same ISBN already exists.");
                }
                if (current.getISBN() > addedBook.getISBN()) {
                    headBook = addedBook;
                    addedBook.setNextBook(current);
                    this.length++;
                    return;
                }
                while (current.getNextBook() != null) {
                    if (current.getNextBook().getISBN() > addedBook.getISBN()) {
                        addedBook.setNextBook(current.getNextBook());
                        current.setNextBook(addedBook);
                        this.length++;
                        return;
                    }
                    current = current.getNextBook();
                }
                current.setNextBook(addedBook);
                tailBook = addedBook;
                this.length++;
                return;

            case NAME:
                current = headBook;
                if (current.getName().equals(addedBook.getName())) {
                    headBook = addedBook;
                    addedBook.setNextBook(current.getNextBook());
                    this.length++;
                    return;
                }
                if (current.getName().compareTo(addedBook.getName()) > 0) {
                    headBook = addedBook;
                    addedBook.setNextBook(current);
                    this.length++;
                    return;
                }
                while (current.getNextBook() != null) {
                    if (current.getNextBook().getName().compareTo(addedBook.getName()) > 0) {
                        addedBook.setNextBook(current.getNextBook());
                        current.setNextBook(addedBook);
                        this.length++;
                        return;
                    }
                    current = current.getNextBook();
                }
                current.setNextBook(addedBook);
                tailBook = addedBook;
                this.length++;
                return;

            case AUTHOR:
                current = headBook;
                if (current.getAuthor().compareTo(addedBook.getAuthor()) > 0) {
                    headBook = addedBook;
                    addedBook.setNextBook(current);
                    this.length++;
                    return;
                }
                while (current.getNextBook() != null) {
                    if (current.getNextBook().getAuthor().compareTo(addedBook.getAuthor()) > 0) {
                        addedBook.setNextBook(current.getNextBook());
                        current.setNextBook(addedBook);
                        this.length++;
                        return;
                    }
                    current = current.getNextBook();
                }
                current.setNextBook(addedBook);
                tailBook = addedBook;
                this.length++;
                return;

            case GENRE:
                current = headBook;
                if (current.getGenre().compareTo(addedBook.getGenre()) > 0) {
                    headBook = addedBook;
                    addedBook.setNextBook(current);
                    this.length++;
                    return;
                }
                while (current.getNextBook() != null) {
                    if (current.getNextBook().getGenre().compareTo(addedBook.getGenre()) > 0) {
                        addedBook.setNextBook(current.getNextBook());
                        current.setNextBook(addedBook);
                        this.length++;
                        return;
                    }
                    current = current.getNextBook();
                }
                current.setNextBook(addedBook);
                tailBook = addedBook;
                this.length++;
                return;

            case YEAR:
                current = headBook;
                if (current.getYearPublished() > addedBook.getYearPublished()) {
                    headBook = addedBook;
                    addedBook.setNextBook(current);
                    this.length++;
                    return;
                }
                while (current.getNextBook() != null) {
                    if (current.getNextBook().getYearPublished() > addedBook.getYearPublished()) {
                        addedBook.setNextBook(current.getNextBook());
                        current.setNextBook(addedBook);
                        this.length++;
                        return;
                    }
                    current = current.getNextBook();
                }
                current.setNextBook(addedBook);
                tailBook = addedBook;
                this.length++;
                return;

            case CONDITION:
                current = headBook;
                if (current.getBookCondition().ordinal() > addedBook.getBookCondition().ordinal()) {
                    headBook = addedBook;
                    addedBook.setNextBook(current);
                    this.length++;
                    return;
                }
                while (current.getNextBook() != null) {
                    if (current.getNextBook().getBookCondition().ordinal() > addedBook.getBookCondition().ordinal()) {
                        addedBook.setNextBook(current.getNextBook());
                        current.setNextBook(addedBook);
                        this.length++;
                        return;
                    }
                    current = current.getNextBook();
                }
                current.setNextBook(addedBook);
                tailBook = addedBook;
                this.length++;
                return;
        }
    }

    public void removeBook(long removedISBN) throws BookDoesNotExistException, InvalidISBNException {
        String r = String.valueOf(removedISBN);
        if (r.length() > 13)
            throw new InvalidISBNException("Checked out book's ISBN is invalid.");

        Book curr = headBook;
        if(headBook == null) {
            throw new BookDoesNotExistException("Shelf is empty");
        }
        if (headBook.getISBN() == removedISBN) {
            Book next = headBook.nextBook;
            System.out.printf("The book %s by %s has been removed\n", headBook.getName(), headBook.getAuthor());
            headBook = next;
            return;
        }

        while (curr != null) {
            if (curr.getISBN() == removedISBN) {
                Book prev = headBook;
                while (prev.getNextBook() != curr) {
                    prev = prev.getNextBook(); // curr2 will be the book before curr
                }
                Book next = curr.getNextBook();
                if(next == null) {
                    tailBook = prev;
                }
                prev.setNextBook(next);
                this.length--;
                System.out.printf("The book %s by %s has been removed\n", curr.getName(), curr.getAuthor());
                return;
            }
            curr = curr.getNextBook();
        }
        throw new BookDoesNotExistException("A book with the same ISBN does not exist.");
    }

    public Book deepCopyBook(Book b) {
        return new Book(b.name, b.author, b.genre, b.bookCondition, b.ISBN, b.checkOutUserID, b.yearPublished, b.dueDate, b.nextBook, b.checkedOut);
    }

    public void sort(SortCriteria sortCriteria) {
        Book current = headBook;
        Shelf newShelf = new Shelf(null, null, 0, sortCriteria);

        switch (sortCriteria) {
            case ISBN:
                while (current != null) {
                    Book insert = deepCopyBook(current);
                    insert.nextBook = null;
                    Book next = current.getNextBook();
                    if (newShelf.headBook == null) {
                        newShelf.headBook = insert;
                        newShelf.length++;
                        current = current.getNextBook();
                        continue;
                    } else if (newShelf.headBook.getISBN() >= insert.getISBN()) {
                        insert.nextBook = newShelf.headBook;
                        newShelf.headBook = insert;
                        newShelf.length++;
                        current = current.getNextBook();
                        continue;
                    } else {
                        Book prev = newShelf.headBook;
                        while (prev.nextBook != null && prev.nextBook.getISBN() < insert.getISBN()) {
                            prev = prev.nextBook;
                        }
                        insert.nextBook = prev.nextBook;
                        prev.nextBook = insert;
                        newShelf.length++;
                    }
                    current = next;
                }
                this.headBook = newShelf.headBook;
                this.tailBook = newShelf.tailBook;
                this.length = newShelf.length;
                this.shelfSortCriteria =newShelf.shelfSortCriteria;
                return;

            case NAME:
                while (current != null) {
                    Book insert = deepCopyBook(current);
                    insert.nextBook = null;
                    Book next = current.getNextBook();
                    if (newShelf.headBook == null) {
                        newShelf.headBook = insert;
                        newShelf.length++;
                        current = current.getNextBook();
                        continue;
                    } else if (newShelf.headBook.getName().compareTo(insert.getName()) >= 0) {
                        insert.nextBook = newShelf.headBook;
                        newShelf.headBook = insert;
                        newShelf.length++;
                        current = current.getNextBook();
                        continue;
                    } else {
                        Book prev = newShelf.headBook;
                        while (prev.nextBook != null && prev.nextBook.getName().compareTo(insert.getName()) < 0) {
                            prev = prev.nextBook;
                        }
                        insert.nextBook = prev.nextBook;
                        prev.nextBook = insert;
                        newShelf.length++;
                    }
                    current = next;
                }
                this.headBook = newShelf.headBook;
                this.tailBook = newShelf.tailBook;
                this.length = newShelf.length;
                this.shelfSortCriteria =newShelf.shelfSortCriteria;
                return;

            case AUTHOR:
                while (current != null) {
                    Book insert = deepCopyBook(current);
                    insert.nextBook = null;
                    Book next = current.getNextBook();
                    if (newShelf.headBook == null) {
                        newShelf.headBook = insert;
                        newShelf.length++;
                        current = current.getNextBook();
                        continue;
                    } else if (newShelf.headBook.getAuthor().compareTo(insert.getAuthor()) >= 0) {
                        insert.nextBook = newShelf.headBook;
                        newShelf.headBook = insert;
                        newShelf.length++;
                        current = current.getNextBook();
                        continue;
                    } else {
                        Book prev = newShelf.headBook;
                        while (prev.nextBook != null && prev.nextBook.getAuthor().compareTo(insert.getAuthor()) < 0) {
                            prev = prev.nextBook;
                        }
                        insert.nextBook = prev.nextBook;
                        prev.nextBook = insert;
                        newShelf.length++;
                    }
                    current = next;
                }
                this.headBook = newShelf.headBook;
                this.tailBook = newShelf.tailBook;
                this.length = newShelf.length;
                this.shelfSortCriteria =newShelf.shelfSortCriteria;
                return;

            case GENRE:
                while (current != null) {
                    Book insert = deepCopyBook(current);
                    insert.nextBook = null;
                    Book next = current.getNextBook();
                    if (newShelf.headBook == null) {
                        newShelf.headBook = insert;
                        newShelf.length++;
                        current = current.getNextBook();
                        continue;
                    } else if (newShelf.headBook.getGenre().compareTo(insert.getGenre()) >= 0) {
                        insert.nextBook = newShelf.headBook;
                        newShelf.headBook = insert;
                        newShelf.length++;
                        current = current.getNextBook();
                        continue;
                    } else {
                        Book prev = newShelf.headBook;
                        while (prev.nextBook != null && prev.nextBook.getGenre().compareTo(insert.getGenre()) < 0) {
                            prev = prev.nextBook;
                        }
                        insert.nextBook = prev.nextBook;
                        prev.nextBook = insert;
                        newShelf.length++;
                    }
                    current = next;
                }
                this.headBook = newShelf.headBook;
                this.tailBook = newShelf.tailBook;
                this.length = newShelf.length;
                this.shelfSortCriteria =newShelf.shelfSortCriteria;
                return;

            case YEAR:
                while (current != null) {
                    Book insert = deepCopyBook(current);
                    insert.nextBook = null;
                    Book next = current.getNextBook();
                    if (newShelf.headBook == null) {
                        newShelf.headBook = insert;
                        newShelf.length++;
                        current = current.getNextBook();
                        continue;
                    } else if (newShelf.headBook.getYearPublished() >= insert.getYearPublished()) {
                        insert.nextBook = newShelf.headBook;
                        newShelf.headBook = insert;
                        newShelf.length++;
                        current = current.getNextBook();
                        continue;
                    } else {
                        Book prev = newShelf.headBook;
                        while (prev.nextBook != null && prev.nextBook.getYearPublished() < insert.getYearPublished()) {
                            prev = prev.nextBook;
                        }
                        insert.nextBook = prev.nextBook;
                        prev.nextBook = insert;
                        newShelf.length++;
                    }
                    current = next;
                }
                this.headBook = newShelf.headBook;
                this.tailBook = newShelf.tailBook;
                this.length = newShelf.length;
                this.shelfSortCriteria =newShelf.shelfSortCriteria;
                return;


            case CONDITION:
                while (current != null) {
                    Book insert = deepCopyBook(current);
                    insert.nextBook = null;
                    Book next = current.getNextBook();
                    if (newShelf.headBook == null) {
                        newShelf.headBook = insert;
                        newShelf.length++;
                        current = current.getNextBook();
                        continue;
                    } else if (newShelf.headBook.getBookCondition().ordinal() >= insert.getBookCondition().ordinal()) {
                        insert.nextBook = newShelf.headBook;
                        newShelf.headBook = insert;
                        newShelf.length++;
                        current = current.getNextBook();
                        continue;
                    } else {
                        Book prev = newShelf.headBook;
                        while (prev.nextBook != null && prev.nextBook.getBookCondition().ordinal() < insert.getBookCondition().ordinal()) {
                            prev = prev.nextBook;
                        }
                        insert.nextBook = prev.nextBook;
                        prev.nextBook = insert;
                        newShelf.length++;
                    }
                    current = next;
                }
                this.headBook = newShelf.headBook;
                this.tailBook = newShelf.tailBook;
                this.length = newShelf.length;
                this.shelfSortCriteria =newShelf.shelfSortCriteria;
                return;
        }
    }

    public void printShelf() {
        Book current = this.headBook;
        String yes = "Y";
        String no = "N";
        switch(this.shelfSortCriteria) {
            case ISBN:
                System.out.printf("%-15s|%-15s|%-15s|%-15s\n", "ISBN", "Checked Out", "Due Date", "Checkout User ID");
                System.out.println("-------------------------------------------------------------------------------");
                while(current != null) {
                    if(current.isCheckedOut()) {
                        System.out.printf("%-15s|%-15s|%d/%d/%d\t|%-15s\n", current.getISBNAsString(), yes, current.getDueDate().month, current.getDueDate().day, current.getDueDate().year, current.getCheckOutUserID());
                    }
                    else {
                        System.out.printf("%-15s|%-15s|%-15s|%-15s\n", current.getISBNAsString(), no, "N/A", "N/A");
                    }
                    current = current.getNextBook();
                }
                break;

            case NAME:
                System.out.printf("%-15s|%-15s|%-15s|%-15s\n", "Name", "Checked Out", "Due Date", "Checkout User ID");
                System.out.println("-------------------------------------------------------------------------------");
                while(current != null) {
                    if(current.isCheckedOut()) {
                        System.out.printf("%-15s|%-15s|%d/%d/%d\t|%-15s\n", current.getName(), yes, current.getDueDate().month, current.getDueDate().day, current.getDueDate().year, current.getCheckOutUserID());
                    }
                    else {
                        System.out.printf("%-15s|%-15s|%-15s|%-15s\n", current.getName(), no, "N/A", "N/A");
                    }
                    current = current.getNextBook();
                }
                break;

            case AUTHOR:
                System.out.printf("%-15s|%-15s|%-15s|%-15s\n", "Author", "Checked Out", "Due Date", "Checkout User ID");
                System.out.println("-------------------------------------------------------------------------------");
                while(current != null) {
                    if(current.isCheckedOut()) {
                        System.out.printf("%-15s|%-15s|%d/%d/%d\t|%-15s\n", current.getAuthor(), yes, current.getDueDate().month, current.getDueDate().day, current.getDueDate().year, current.getCheckOutUserID());
                    }
                    else {
                        System.out.printf("%-15s|%-15s|%-15s|%-15s\n", current.getAuthor(), no, "N/A", "N/A");
                    }
                    current = current.getNextBook();
                }
                break;

            case GENRE:
                System.out.printf("%-15s|%-15s|%-15s|%-15s\n", "Genre", "Checked Out", "Due Date", "Checkout User ID");
                System.out.println("-------------------------------------------------------------------------------");
                while(current != null) {
                    if(current.isCheckedOut()) {
                        System.out.printf("%-15s|%-15s|%d/%d/%d\t|%-15s\n", current.getGenre(), yes, current.getDueDate().month, current.getDueDate().day, current.getDueDate().year, current.getCheckOutUserID());
                    }
                    else {
                        System.out.printf("%-15s|%-15s|%-15s|%-15s\n", current.getGenre(), no, "N/A", "N/A");
                    }
                    current = current.getNextBook();
                }
                break;

            case YEAR:
                System.out.printf("%-15s|%-15s|%-15s|%-15s\n", "Year", "Checked Out", "Due Date", "Checkout User ID");
                System.out.println("-------------------------------------------------------------------------------");
                while(current != null) {
                    if(current.isCheckedOut()) {
                        System.out.printf("%-15s|%-15s|%d/%d/%d\t|%-15s\n", current.getYearPublished(), yes, current.getDueDate().month, current.getDueDate().day, current.getDueDate().year, current.getCheckOutUserID());
                    }
                    else {
                        System.out.printf("%-15s|%-15s|%-15s|%-15s\n", current.getYearPublished(), no, "N/A", "N/A");
                    }
                    current = current.getNextBook();
                }
                break;

            case CONDITION:
                System.out.printf("%-15s|%-15s|%-15s|%-15s\n", "Condition", "Checked Out", "Due Date", "Checkout User ID");
                System.out.println("-------------------------------------------------------------------------------");
                while(current != null) {
                    if(current.isCheckedOut()) {
                        System.out.printf("%-15s|%-15s|%d/%d/%d\t|%-15s\n", current.getBookCondition(), yes, current.getDueDate().month, current.getDueDate().day, current.getDueDate().year, current.getCheckOutUserID());
                    }
                    else {
                        System.out.printf("%-15s|%-15s|%-15s|%-15s\n", current.getBookCondition(), no, "N/A", "N/A");
                    }
                    current = current.getNextBook();
                }
                break;

            default:
                break;
        }
        System.out.printf("Shelf is currently sorted by criteria: %s\n", this.shelfSortCriteria);
    }
}

