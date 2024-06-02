import java.io.*;
import java.time.LocalDate;
import java.util.*;

class Library {

    //ATTRIBUTES
    private List<User> users;
    private List<Book> books;
    private List<Review> reviews;
    private List<BorrowRecord> borrowRecords;
    private Map<String, String> sessions;  // Simulates user sessions



    //CONSTRUCTOR OF LIBRARY CLASS
    public Library() {
        users = new ArrayList<>();
        books = new ArrayList<>();
        reviews = new ArrayList<>();
        borrowRecords = new ArrayList<>();
        sessions = new HashMap<>(); //MANAGES SESSION DATA
    }




    //ACCESS USER LIST
    public List<User> getUsers() {
        return users;
    }


    //LOADS DATA RELATED TO USERS
    public void loadLibrary() throws IOException {
        loadUsers();
        loadBooks();
        loadReviews();
        loadBorrowRecords();
    }



    //SAVES DATA RELATED TO USERS
    public void saveLibrary() throws IOException {
        saveUsers();
        saveBooks();
        saveReviews();
        saveBorrowRecords();
    }



    //LOGIN METHOD
    public boolean login(String name, String password) {
        for (User user : users) {       //ITERATES THROUGH THE LIST OF USERS
            if (user.getName().equals(name) && user.getPassword().equals(password)) {
                sessions.put(name, "active");
                return true;
            }
        }
        return false;
    }





    //CLEARS OUT ALL ACTIVE SESSIONS
    public void logout() {
        sessions.clear();
    }



    //REGISTERS A NEW USER AND ADDS TO THE USER LIST
    public void register(String name, String phoneNumber, String password, boolean isAdmin) {
        users.add(new User(name, phoneNumber, password, isAdmin));
        System.out.println("User registered successfully.");
        try {
            saveUsers();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    //CHECKS ADMINISTRATIVE PRIVILEGE OF A SPECIFIC USER
    public boolean isAdmin(String name) {
        for (User user : users) {
            if (user.getName().equals(name) && user.isAdmin()) {
                return true;
            }
        }
        return false;
    }

    //ADDING NEW BOOK TO THE LIST
    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added successfully.");
        try {
            saveBooks();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    //DELETING A BOOK
    public void deleteBook(String title) {
        books.removeIf(book -> book.getTitle().equalsIgnoreCase(title));
        System.out.println("Book deleted successfully.");
        try {
            saveBooks();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    //UPDATING EXISTING BOOK
    public void updateBook(String oldTitle, Book newBook) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(oldTitle)) {
                book.setTitle(newBook.getTitle());
                book.setAuthor(newBook.getAuthor());
                book.setIsbn(newBook.getIsbn());
                book.setQuantity(newBook.getQuantity());
                System.out.println("Book updated successfully.");
                try {
                    saveBooks();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return;
            }
        }
        System.out.println("Book not found.");
    }





    //ALLOWS USER TO BORROW BOOK IF AVAILABLE
    public boolean borrowBook(String name, String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title) && book.getQuantity() > 1) {
                book.setQuantity(book.getQuantity() - 1);
                borrowRecords.add(new BorrowRecord(name, title, LocalDate.now().plusDays(14))); // 2 weeks borrowing time
                try {
                    saveBooks();
                    saveBorrowRecords();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return true;
            }
        }
        return false;
    }




    //RETURN BOOK METHOD
    public boolean returnBook(String name, String title) {
        Iterator<BorrowRecord> iterator = borrowRecords.iterator();
        while (iterator.hasNext()) {
            BorrowRecord record = iterator.next();
            if (record.getUserName().equals(name) && record.getBookTitle().equals(title)) {
                iterator.remove();
                for (Book book : books) {
                    if (book.getTitle().equalsIgnoreCase(title)) {
                        book.setQuantity(book.getQuantity() + 1);
                        try {
                            saveBooks();
                            saveBorrowRecords();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }




    //SEARCH BOOK METHOD
    public String searchBooks(String query) {
        StringBuilder result = new StringBuilder();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                    book.getAuthor().toLowerCase().contains(query.toLowerCase())) {
                result.append(book).append("\n");
            }
        }
        return result.toString();
    }




    //CHECKS IF BOOK IS AVAILABLE
    public boolean isBookAvailable(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title) && book.getQuantity() > 0) {
                return true;
            }
        }
        return false;
    }



    //VIEW REVIEW METHOS
    public String viewReviews() {
        StringBuilder result = new StringBuilder();
        for (Review review : reviews) {
            result.append(review).append("\n");
        }
        return result.toString();
    }


    //WRITE REVIEW
    public void writeReview(String name, String bookTitle, String reviewText) {
        reviews.add(new Review(name, bookTitle, reviewText));
        System.out.println("Review added successfully.");
        try {
            saveReviews();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    //VIEW DUE DATES METHOD
    public String viewDueDates() {
        StringBuilder result = new StringBuilder();
        for (BorrowRecord record : borrowRecords) {
            result.append(record).append("\n");
        }
        return result.toString();
    }



    //READ USER DATA FROM TEXT FILE
    private void loadUsers() throws IOException {
        File file = new File("users.txt");
        if (file.exists()) {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                users.add(new User(parts[0], parts[1], parts[2], Boolean.parseBoolean(parts[3])));
            }
            reader.close();  //AVOIDS MEMORY LEAK AND FREES UP SYSTEM RESOURCES
        }
    }



    //SAVES DATA INTO A TEXT FILE
    private void saveUsers() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt"));
        for (User user : users) {
            writer.write(user.getName() + "," + user.getPhoneNumber() + "," + user.getPassword() + "," + user.isAdmin());
            writer.newLine();
        }
        writer.close();
    }




    //LOAD BOOKS METHOD
    private void loadBooks() throws IOException {
        File file = new File("books.txt");
        if (file.exists()) {
            BufferedReader reader = new BufferedReader(new FileReader(file)); //READS DATA FROM THE FILE
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                books.add(new Book(parts[0], parts[1], parts[2], Integer.parseInt(parts[3])));
            }
            reader.close();
        }
    }



    //SAVE BOOKS METHOD
    private void saveBooks() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("books.txt"));
        for (Book book : books) {
            writer.write(book.getTitle() + "," + book.getAuthor() + "," + book.getIsbn() + "," + book.getQuantity());
            writer.newLine();
        }
        writer.close(); //FREES UP SYSTEM RESOURCES
    }



    //LOAD REVIEWS METHOD
    private void loadReviews() throws IOException {
        File file = new File("reviews.txt");
        if (file.exists()) {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                reviews.add(new Review(parts[0], parts[1], parts[2]));
            }
            reader.close();
        }
    }



    //SAVE REVIEWS METHOD
    private void saveReviews() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("reviews.txt"));
        for (Review review : reviews) {
            writer.write(review.getName() + "," + review.getBookTitle() + "," + review.getReviewText());
            writer.newLine();
        }
        writer.close();
    }


    //LOAD BORROW RECORDS METHOD
    private void loadBorrowRecords() throws IOException {
        File file = new File("borrowRecords.txt");
        if (file.exists()) {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                borrowRecords.add(new BorrowRecord(parts[0], parts[1], LocalDate.parse(parts[2])));
            }
            reader.close();
        }
    }




    //SAVE BORROW BOOKS METHOD
    private void saveBorrowRecords() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("borrowRecords.txt"));
        for (BorrowRecord record : borrowRecords) {
            writer.write(record.getUserName() + "," + record.getBookTitle() + "," + record.getDueDate());
            writer.newLine();
        }
        writer.close();
    }
}
