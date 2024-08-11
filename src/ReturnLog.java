public class ReturnLog {
    long ISBN;
    long userID;
    Date returnDate;
    Book book;
    ReturnLog nextLog;

    public ReturnLog(long ISBN, long userID, Date returnDate, ReturnLog nextLog, Book book) {
        this.ISBN = ISBN;
        this.userID = userID;
        this.returnDate = returnDate;
        this.nextLog = nextLog;
        this.book = book;
    }

    public long getISBN() {
        return ISBN;
    }

    public void setISBN(long ISBN) {
        this.ISBN = ISBN;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public ReturnLog getNextLog() {
        return nextLog;
    }

    public void setNextLog(ReturnLog nextLog) {
        this.nextLog = nextLog;
    }
}
