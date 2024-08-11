public class ReturnStack {
    ReturnLog topLog;

    public ReturnStack(ReturnLog topLog) {
        this.topLog = topLog;
    }

    public ReturnStack() {
    }

    public ReturnLog getTopLog() {
        return topLog;
    }

    public void setTopLog(ReturnLog topLog) {
        this.topLog = topLog;
    }

    public boolean pushLog(long returnISBN, String returnUserID, Date returnDate, BookRepository bookRepoRef) throws NumberFormatException, InvalidISBNException, InvalidUserIDException, BookDoesNotExistException, BookNotCheckedOutException, BookCheckedOutBySomeoneElseException, InvalidReturnDateException {
        if(returnUserID.length() > 10) {
            throw new InvalidUserIDException("Invalid user ID");
        }
        String isbn = String.valueOf(returnISBN);
        char firstChar = isbn.charAt(0);
        if (!Character.isDigit(firstChar) || isbn.length() > 13) {
            throw new InvalidISBNException("Checked out book's ISBN is invalid.");
        }
        firstChar -= 48; //convert the character to its actual digit form

        Book current = bookRepoRef.shelves[firstChar].headBook;
        while(current != null) {
            if(current.getISBN() == returnISBN) {
                if(!current.isCheckedOut()) {
                    throw new BookNotCheckedOutException("A book with this ISBN is not checked out.");
                }
                if(current.getCheckOutUserID() != Long.parseLong(returnUserID)) {
                    throw new BookCheckedOutBySomeoneElseException("A book was originally checked out by someone else.");
                }
                if(!Date.verify(returnDate)) {
                    throw new InvalidReturnDateException("The return date is invalid");
                }
                ReturnLog returned = new ReturnLog(returnISBN, Long.parseLong(returnUserID), returnDate, topLog, current);
                topLog = returned;
                if(Date.compare(current.getDueDate(), returnDate) >= 0) {
                    System.out.printf("%s by %s has been returned early or on time\n",current.getName(), current.getAuthor());
                    return true;
                }
                else {
                    System.out.printf("%s by %s has been returned late\n",current.getName(), current.getAuthor());
                    return false;
                }
            }
            current= current.getNextBook();
        }
        throw new BookDoesNotExistException("Book does not exist.");
    }

    public ReturnLog popLog() throws EmptyStackException {
        if(topLog == null) {
            throw new EmptyStackException("The return stack is empty.");
        }
        ReturnLog last = topLog;
        last.book.setCheckedOut(false);
        topLog = topLog.getNextLog();
        return last;
    }

    public void printReturnStack() {
        ReturnLog current = topLog;
        System.out.printf("%-15s|%-15s|%-15s\n", "ISBN", "User ID", "Return Date");
        System.out.println("----------------------------------------------------");
        while(current != null) {
            System.out.printf("%-15s|%-15s|%d/%d/%d\n", current.book.getISBNAsString(), current.getUserID(), current.getReturnDate().month, current.getReturnDate().day,current.getReturnDate().year);
            current = current.getNextLog();
        }
    }
}
