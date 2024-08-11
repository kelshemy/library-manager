public class Book {
    enum Condition {
        NEW, GOOD, BAD, REPLACE;
    }
    String name;
    String author;
    String genre;
    Condition bookCondition;
    long ISBN;
    long checkOutUserID;
    int yearPublished;
    Date dueDate;
    Book nextBook;
    boolean checkedOut;

    public Book(String name, String author, String genre, Condition bookCondition, long ISBN, long checkOutUserID, int yearPublished, Date dueDate, Book nextBook, boolean checkedOut) {
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.bookCondition = bookCondition;
        this.ISBN = ISBN;
        this.checkOutUserID = checkOutUserID;
        this.yearPublished = yearPublished;
        this.dueDate = dueDate;
        this.nextBook = nextBook;
        this.checkedOut = checkedOut;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getISBNAsString() {
        String s = String.valueOf(this.ISBN);
        if(s.length() < 13) {
            int n = 13 - s.length();
            StringBuilder s2 = new StringBuilder();
            for(int i = 0; i<n; i++) {
                s2.append('0');
            }
            s2.append(s);
            return s2.toString();
        }
        return s;
    }

    public Condition getBookCondition() {
        return bookCondition;
    }

    public void setBookCondition(Condition bookCondition) {
        this.bookCondition = bookCondition;
    }

    public long getISBN() {
        return ISBN;
    }

    public void setISBN(long ISBN) {
        this.ISBN = ISBN;
    }

    public long getCheckOutUserID() {
        return checkOutUserID;
    }

    public void setCheckOutUserID(long checkOutUserID) {
        this.checkOutUserID = checkOutUserID;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Book getNextBook() {
        return nextBook;
    }

    public void setNextBook(Book nextBook) {
        this.nextBook = nextBook;
    }

    public boolean isCheckedOut() {
        return checkedOut;
    }

    public void setCheckedOut(boolean checkedOut) {
        this.checkedOut = checkedOut;
    }
}
