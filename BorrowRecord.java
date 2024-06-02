import java.time.LocalDate;
class BorrowRecord {

    //ATTRIBUTES
    private String userName;
    private String bookTitle;
    private LocalDate dueDate;

    //CONSTRUCTOR
    public BorrowRecord(String userName, String bookTitle, LocalDate dueDate) {
        this.userName = userName;
        this.bookTitle = bookTitle;
        this.dueDate = dueDate;
    }

    //ACCESS TO PRIAVTE ATTRIBUTES
    public String getUserName() {
        return userName;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    //HUMAN READABLE STRING PRESENTATION OF BORROWRECORD OBJECT
    @Override
    public String toString() {
        return "BorrowRecord{" +
                "userName='" + userName + '\'' +
                ", bookTitle='" + bookTitle + '\'' +
                ", dueDate=" + dueDate +
                '}';
    }
}
