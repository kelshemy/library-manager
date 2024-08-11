import java.util.Scanner;

public class LibraryManager {
    public static void main(String[] args) throws InvalidSortCriteriaException {
        boolean quit = false;
        Scanner s = new Scanner(System.in);
        BookRepository br = new BookRepository();
        ReturnStack rs = new ReturnStack();
        while (!quit) {
            String choices;
            System.out.println("Menu: ");
            System.out.println("B) Manage Book Repository");
            System.out.println("R) Manage Return Stack");
            System.out.println("Q) Quit");
            System.out.println("Please select what to manage: ");
            choices = s.nextLine().trim();
            choices = choices.toUpperCase();
            switch (choices) {
                case "B":
                    System.out.println("C) Checkout Book");
                    System.out.println("N) Add New Book");
                    System.out.println("R) Remove Book");
                    System.out.println("P) Print Repository");
                    System.out.println("S) Sort Shelf");
                    System.out.println("Please select an option: ");
                    String choices2;
                    choices2 = s.nextLine().trim().toUpperCase();
                    switch (choices2) {
                        case "C":
                            System.out.println("Please provide a library user ID: ");
                            long uUserID = Long.parseLong(s.nextLine().trim());
                            System.out.println("Please provide an ISBN number: ");
                            long uISBN = Long.parseLong(s.nextLine().trim());
                            System.out.println("Please provide a due date: ");
                            String[] uDate = s.nextLine().trim().split("/");
                            if (uDate.length == 3) {
                                try {
                                    Date d = new Date(Integer.parseInt(uDate[0]), Integer.parseInt(uDate[1]), Integer.parseInt(uDate[2]));
                                    br.checkOutBook(uISBN, uUserID, d);
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                            } else {
                                System.out.println("Invalid date");
                            }
                            continue;

                        case "N":
                            System.out.println("Please provide an ISBN number: ");
                            long uISBN2 = Long.parseLong(s.nextLine().trim());
                            System.out.println("Please provide a name: ");
                            String uName = s.nextLine().trim();
                            System.out.println("Please provide a author: ");
                            String uAuthor = s.nextLine().trim();
                            System.out.println("Please provide a genre: ");
                            String uGenre = s.nextLine().trim();
                            System.out.println("Please provide a publishing year: ");
                            int uPublishingYear = Integer.parseInt(s.nextLine().trim());
                            System.out.println("Please provide a condition: ");
                            Book.Condition uCondition = Book.Condition.valueOf(s.next().toUpperCase());
                            try {
                                br.addBook(uISBN2, uName, uAuthor, uGenre, uPublishingYear, uCondition);
                            } catch (InvalidISBNException iie) {
                                System.out.println(iie.getMessage());
                            } catch (BookAlreadyExistsException baee) {
                                System.out.println(baee.getMessage());
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                            continue;

                        case "R":
                            System.out.println("Please provide an ISBN number: ");
                            long uISBN3 = Long.parseLong(s.nextLine().trim());
                            try {
                                br.removeBook(uISBN3);
                            } catch (InvalidISBNException | BookDoesNotExistException e) {
                                System.out.println(e.getMessage());
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                            continue;

                        case "P":
                            System.out.println("Please select a shelf: ");
                            int uShelf = Integer.parseInt(s.nextLine().trim());
                            br.shelves[uShelf].printShelf();
                            continue;

                        case "S":
                            System.out.println("I) – ISBN Number");
                            System.out.println("N) – Name");
                            System.out.println("A) – Author");
                            System.out.println("G) – Genre");
                            System.out.println("Y) – Year");
                            System.out.println("C) – Condition");
                            System.out.println("Please select a sorting criteria: ");
                            String choices3;
                            choices3 = s.nextLine().trim();
                            choices3 = choices3.toUpperCase();
                            switch (choices3) {
                                case "I":
                                    System.out.println("Please select a shelf: ");
                                    int uShelf2 = Integer.parseInt(s.nextLine().trim());
                                    br.sortShelf(uShelf2, "ISBN");
                                    break;

                                case "N":
                                    System.out.println("Please select a shelf: ");
                                    int uShelf3 = Integer.parseInt(s.nextLine().trim());
                                    br.sortShelf(uShelf3, "NAME");
                                    break;

                                case "A":
                                    System.out.println("Please select a shelf: ");
                                    int uShelf4 = Integer.parseInt(s.nextLine().trim());
                                    br.sortShelf(uShelf4, "AUTHOR");
                                    break;

                                case "G":
                                    System.out.println("Please select a shelf: ");
                                    int uShelf5 = Integer.parseInt(s.nextLine().trim());
                                    br.sortShelf(uShelf5, "GENRE");
                                    break;

                                case "Y":
                                    System.out.println("Please select a shelf: ");
                                    int uShelf6 = Integer.parseInt(s.nextLine().trim());
                                    br.sortShelf(uShelf6, "YEAR");
                                    break;

                                case "C":
                                    System.out.println("Please select a shelf: ");
                                    int uShelf7 = Integer.parseInt(s.nextLine().trim());
                                    br.sortShelf(uShelf7, "CONDITION");
                                    break;
                            }
                            continue;
                    }
                case "R":
                    System.out.println("R) – Return Book");
                    System.out.println("L) – See Last Return");
                    System.out.println("C) – Check In Last Return");
                    System.out.println("P) Print Return Stack");
                    System.out.println("Please select an option: ");
                    String choices4;
                    choices4 = s.nextLine().trim();
                    choices4 = choices4.toUpperCase();
                    switch (choices4) {
                        case "R":
                            System.out.println("Please provide the ISBN of the book you are returning: ");
                            long uISBN4 = Long.parseLong(s.nextLine().trim());
                            System.out.println("Please provide your Library UserID: ");
                            String uUserID2 = s.nextLine().trim();
                            System.out.println("Please provide your current date: ");
                            String[] uDate2 = s.nextLine().trim().split("/");
                            if (uDate2.length == 3) {
                                try {
                                    Date d2 = new Date(Integer.parseInt(uDate2[0]), Integer.parseInt(uDate2[1]), Integer.parseInt(uDate2[2]));
                                    rs.pushLog(uISBN4, uUserID2, d2, br);
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                            } else {
                                System.out.println("Invalid date");
                            }
                            break;

                        case "L":
                            System.out.println("Book with ISBN " + rs.topLog.ISBN + " is the last book to be returned.");
                            break;

                        case "C":
                            try {
                                br.checkInBook(rs.popLog().getISBN());
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                            break;

                        case "P":
                            rs.printReturnStack();
                            break;
                    }
                    continue;

                case ("Q"):
                    System.out.println("You have quit the program.");
                    System.exit(0);
                    break;
                }
            }
        }
    }