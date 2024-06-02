import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;



public class Main {
    private Library library; //PRIVATE INSTANCE VARIABLE OF TYPE LIBRARY


    //CONSTRUCTOR
    public Main() {
        library = new Library(); //INSTANTIATES LIBRARY CLASS


        try {
            library.loadLibrary(); //CALLING LOADLIBRARY METHOD
        } catch (IOException e) {
            e.printStackTrace();
        }
        showLoginScreen(); //LOGIN SCREEN
    }




    //SHOWS LOGIN SCREEN
    private void showLoginScreen() {
        JFrame frame = new JFrame("Library Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel panel = new JPanel(null); // Using null layout for custom component placement
        frame.add(panel);
        panel.setBackground(new Color(202, 206, 232));


        //ADDING LOGO
        ImageIcon Logo = new ImageIcon("/Users/sadikatabassum/Desktop/Screenshot 2024-06-01 at 1.20.17 PM.png");
        Image logoImage = Logo.getImage().getScaledInstance(450, 150, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(logoImage));
        logoLabel.setBounds(120, 15, 1200, 200);
        panel.add(logoLabel);

        //ADDING MOTTO
        JLabel addLabel = new JLabel("MODERN LIBRARY");
        addLabel.setFont(new Font("Libre Baskerville", Font.PLAIN, 25));
        addLabel.setForeground(Color.WHITE);
        addLabel.setBounds(600, 180, 1920, 100);
        panel.add(addLabel);

        // ADDING IMAGE
        ImageIcon bookIcon = new ImageIcon("/Users/sadikatabassum/Downloads/Book.png");
        Image bookImage = bookIcon.getImage().getScaledInstance(300, 150, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(bookImage));
        imageLabel.setBounds(120, 250, 1200, 200);
        panel.add(imageLabel);

        // ADDING FORM PANEL
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBounds(320, 460, 800, 300);
        formPanel.setBackground(new Color(231, 232, 255));
        panel.add(formPanel);

        placeLoginComponents(formPanel);

        frame.setVisible(true);
    }



    private void placeLoginComponents(JPanel formPanel) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        JLabel nameLabel = new JLabel("Username");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        nameLabel.setBounds(250, 150, 100, 30);
        constraints.gridx = 0;
        constraints.gridy = 0;
        formPanel.add(nameLabel, constraints);

        JTextField nameText = new JTextField(20);
        nameLabel.setBounds(400, 150, 300, 30);
        constraints.gridx = 1;
        formPanel.add(nameText, constraints);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 18));
        passwordLabel.setBounds(250, 200, 100, 30);
        constraints.gridx = 0;
        constraints.gridy = 1;
        formPanel.add(passwordLabel, constraints);

        JPasswordField passwordText = new JPasswordField(20);
        passwordLabel.setBounds(400, 200, 300, 30);
        constraints.gridx = 1;
        formPanel.add(passwordText, constraints);

        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(66, 39, 176));
        loginButton.setForeground(Color.white);
        loginButton.setOpaque(true);
        loginButton.setBorderPainted(false);
        loginButton.setFocusPainted(false);
        constraints.gridx = 0;
        constraints.gridy = 2;
        formPanel.add(loginButton, constraints);

        JButton registerButton = new JButton("Register");
        registerButton.setBackground(new Color(66, 39, 176));
        registerButton.setOpaque(true);
        registerButton.setBorderPainted(false);
        registerButton.setFocusPainted(false);
        registerButton.setForeground(Color.white);
        constraints.gridx = 1;
        formPanel.add(registerButton, constraints);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameText.getText();
                String password = new String(passwordText.getPassword());

                if (library.login(name, password)) {
                    try {
                        if (library.isAdmin(name)) {
                            showLibrarianInterface(name);
                        } else {
                            showPatronInterface(name);
                        }
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(formPanel);
                    topFrame.dispose();
                } else {
                    JOptionPane.showMessageDialog(formPanel, "Invalid name or password");
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showRegisterScreen();
                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(formPanel);
                topFrame.dispose();
            }
        });
    }





    //REGISTRER SCREEN
    private void showRegisterScreen() {
        JFrame frame = new JFrame("Library Management System - Register");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(202, 206, 232));
        frame.add(formPanel);
        placeRegisterComponents(formPanel);

        frame.setVisible(true);
    }




    //ADDING COMPONENTS TO REGISTER SCREEN
    private void placeRegisterComponents(JPanel formPanel) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.fill = GridBagConstraints.HORIZONTAL;

        ImageIcon Logo = new ImageIcon("/Users/sadikatabassum/Desktop/Screenshot 2024-06-01 at 1.20.17 PM.png");
        Image logoImage = Logo.getImage().getScaledInstance(450, 150, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(logoImage));
        logoLabel.setBounds(120, 15, 1200, 200);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(20, 10, 40, 10);
        formPanel.add(logoLabel, constraints);

        constraints.gridwidth = 1;
        constraints.insets = new Insets(10, 10, 10, 10);

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        constraints.gridx = 0;
        constraints.gridy = 1;
        formPanel.add(nameLabel, constraints);

        JTextField nameText = new JTextField(20);
        constraints.gridx = 1;
        formPanel.add(nameText, constraints);

        JLabel phoneLabel = new JLabel("Phone Number");
        phoneLabel.setFont(new Font("Arial", Font.BOLD, 18));
        constraints.gridx = 0;
        constraints.gridy = 2;
        formPanel.add(phoneLabel, constraints);

        JTextField phoneText = new JTextField(20);
        constraints.gridx = 1;
        formPanel.add(phoneText, constraints);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 18));
        constraints.gridx = 0;
        constraints.gridy = 3;
        formPanel.add(passwordLabel, constraints);

        JPasswordField passwordText = new JPasswordField(20);
        constraints.gridx = 1;
        formPanel.add(passwordText, constraints);

        JLabel roleLabel = new JLabel("Role");
        roleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        constraints.gridx = 0;
        constraints.gridy = 4;
        formPanel.add(roleLabel, constraints);

        String[] roles = {"Patron", "Librarian"};
        JComboBox<String> roleComboBox = new JComboBox<>(roles);
        constraints.gridx = 1;
        formPanel.add(roleComboBox, constraints);

        JButton logoutButton = new JButton("Back");
        logoutButton.setBackground(new Color(66, 39, 176));
        logoutButton.setForeground(Color.white);
        logoutButton.setOpaque(true);
        logoutButton.setBorderPainted(false);
        logoutButton.setFocusPainted(false);
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        formPanel.add(logoutButton, constraints);

        JButton registerButton = new JButton("Register");
        registerButton.setBackground(new Color(66, 39, 176));
        registerButton.setForeground(Color.white);
        registerButton.setOpaque(true);
        registerButton.setBorderPainted(false);
        registerButton.setFocusPainted(false);
        constraints.gridx = 1;
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        formPanel.add(registerButton, constraints);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameText.getText();
                String phoneNumber = phoneText.getText();
                String password = new String(passwordText.getPassword());
                boolean isAdmin = roleComboBox.getSelectedItem().equals("Librarian");

                if (validateInput(name, phoneNumber, password)) {
                    library.register(name, phoneNumber, password, isAdmin);
                    JOptionPane.showMessageDialog(formPanel, "User registered successfully");
                    showLoginScreen();
                    disposeCurrentFrame(formPanel);
                }
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showLoginScreen();
                disposeCurrentFrame(formPanel);
            }
        });
    }

    private boolean validateInput(String name, String phoneNumber, String password) {
        if (name.isEmpty() || phoneNumber.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        // Additional validations can be added here
        return true;
    }

    private void disposeCurrentFrame(JPanel formPanel) {
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(formPanel);
        topFrame.dispose();
    }
    private void showPatronInterface(String username) throws IOException {
        JFrame frame = new JFrame("Library Management System - Patron");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel patronPanel = new JPanel(new GridBagLayout());
        patronPanel.setBackground(new Color(202, 206, 232));
        frame.add(patronPanel);
        placePatronComponents(patronPanel, username);

        frame.setVisible(true);
    }






    //ADDING COMPONENTS TO PATRON SCREEN
    private void placePatronComponents(JPanel patronPanel, String username) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.fill = GridBagConstraints.HORIZONTAL;


        ImageIcon Logo = new ImageIcon("/Users/sadikatabassum/Desktop/Screenshot 2024-06-01 at 1.20.17 PM.png");
        Image logoImage = Logo.getImage().getScaledInstance(450, 150, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(logoImage));
        logoLabel.setBounds(120, 15, 1200, 200);
        patronPanel.add(logoLabel);
        logoLabel.setVerticalAlignment(SwingConstants.TOP);
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(30, 10, 10, 10); // Increase top inset to move higher
        patronPanel.add(logoLabel, constraints);

        // PATRON LABEL
        JLabel patronLabel = new JLabel("Welcome Patrons!!");
        patronLabel.setFont(new Font("MV Boli", Font.BOLD, 50));
        patronLabel.setVerticalAlignment(SwingConstants.TOP);
        patronLabel.setHorizontalAlignment(SwingConstants.CENTER);
        patronLabel.setForeground(Color.WHITE);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(10, 10, 50, 10); // Increase bottom inset to move higher
        patronPanel.add(patronLabel, constraints);

        // CCOMMON BUTTON SETTINGS
        Dimension buttonSize = new Dimension(600, 50);

        // SEARCH BUTTON
        JButton searchButton = new JButton("Search Books");
        searchButton.setBackground(new Color(66, 39, 176));
        searchButton.setForeground(Color.white);
        searchButton.setOpaque(true);
        searchButton.setBorderPainted(false);
        searchButton.setFocusPainted(false);
        searchButton.setPreferredSize(buttonSize);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(10, 10, 20, 10);
        patronPanel.add(searchButton, constraints);

        // BORROW BUTTON
        JButton borrowButton = new JButton("Borrow Book");
        borrowButton.setBackground(new Color(66, 39, 176));
        borrowButton.setForeground(Color.white);
        borrowButton.setOpaque(true);
        borrowButton.setBorderPainted(false);
        borrowButton.setFocusPainted(false);
        borrowButton.setPreferredSize(buttonSize);
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.insets = new Insets(10, 10, 10, 10);
        patronPanel.add(borrowButton, constraints);

        // RRETURN BUTTON
        JButton returnButton = new JButton("Return Book");
        returnButton.setBackground(new Color(66, 39, 176));
        returnButton.setForeground(Color.white);
        returnButton.setOpaque(true);
        returnButton.setBorderPainted(false);
        returnButton.setFocusPainted(false);
        returnButton.setPreferredSize(buttonSize);
        constraints.gridx = 1;
        constraints.gridy = 3;
        patronPanel.add(returnButton, constraints);

        // REVIEW BUTTON
        JButton reviewButton = new JButton("Write Review");
        reviewButton.setBackground(new Color(66, 39, 176));
        reviewButton.setOpaque(true);
        reviewButton.setBorderPainted(false);
        reviewButton.setFocusPainted(false);
        reviewButton.setForeground(Color.white);
        reviewButton.setPreferredSize(buttonSize);
        constraints.gridx = 0;
        constraints.gridy = 4;
        patronPanel.add(reviewButton, constraints);

        // VIEW REVIEWS BUTTON
        JButton viewReviewsButton = new JButton("View Reviews");
        viewReviewsButton.setBackground(new Color(66, 39, 176));
        viewReviewsButton.setForeground(Color.white);
        viewReviewsButton.setOpaque(true);
        viewReviewsButton.setBorderPainted(false);
        viewReviewsButton.setFocusPainted(false);
        viewReviewsButton.setPreferredSize(buttonSize);
        constraints.gridx = 1;
        constraints.gridy = 4;
        patronPanel.add(viewReviewsButton, constraints);

        // CHECK AVAILIBITY BUTTON
        JButton checkAvailabilityButton = new JButton("Check Availability");
        checkAvailabilityButton.setBackground(new Color(66, 39, 176));
        checkAvailabilityButton.setForeground(Color.white);
        checkAvailabilityButton.setOpaque(true);
        checkAvailabilityButton.setBorderPainted(false);
        checkAvailabilityButton.setFocusPainted(false);
        checkAvailabilityButton.setPreferredSize(buttonSize);
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(10, 10, 20, 10);
        patronPanel.add(checkAvailabilityButton, constraints);

        Dimension backButtonSize = new Dimension(150, 50);

        // BACK BUTTON
        JButton logoutButton = new JButton("LogOut");
        logoutButton.setBackground(new Color(66, 39, 176));
        logoutButton.setForeground(Color.white);
        logoutButton.setOpaque(true);
        logoutButton.setBorderPainted(false);
        logoutButton.setFocusPainted(false);
        logoutButton.setPreferredSize(backButtonSize);
        constraints.gridx = 0;
        constraints.gridy = 6;
        constraints.gridwidth = 0;
        constraints.insets = new Insets(10, 500, 10, 500);
        constraints.anchor = GridBagConstraints.CENTER;
        patronPanel.add(logoutButton, constraints);

        borrowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bookTitle = JOptionPane.showInputDialog(patronPanel, "Enter book title to borrow:");
                if (library.borrowBook(username, bookTitle)) {
                    JOptionPane.showMessageDialog(patronPanel, "Book borrowed successfully");
                } else {
                    JOptionPane.showMessageDialog(patronPanel, "Book is not available for borrowing");
                }
            }
        });

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bookTitle = JOptionPane.showInputDialog(patronPanel, "Enter book title to return:");
                if (library.returnBook(username, bookTitle)) {
                    JOptionPane.showMessageDialog(patronPanel, "Book returned successfully");
                } else {
                    JOptionPane.showMessageDialog(patronPanel, "Book not found or not borrowed by you");
                }
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = JOptionPane.showInputDialog(patronPanel, "Enter search query:");
                String result = library.searchBooks(query);
                JOptionPane.showMessageDialog(patronPanel, result.isEmpty() ? "No books found" : result);
            }
        });

        checkAvailabilityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bookTitle = JOptionPane.showInputDialog(patronPanel, "Enter book title to check availability:");
                boolean isAvailable = library.isBookAvailable(bookTitle);
                JOptionPane.showMessageDialog(patronPanel, isAvailable ? "Book is available" : "Book is not available");
            }
        });

        reviewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bookTitle = JOptionPane.showInputDialog(patronPanel, "Enter book title to review:");
                String review = JOptionPane.showInputDialog(patronPanel, "Enter your review:");
                library.writeReview(username, bookTitle, review);
            }
        });

        viewReviewsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String reviews = library.viewReviews();
                JOptionPane.showMessageDialog(patronPanel, reviews.isEmpty() ? "No reviews available" : reviews);
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showLoginScreen();
                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(patronPanel);
                topFrame.dispose();
            }
        });
    }





    // ADDING COMPONENTS TO LIBRARIAN INTERFACE
    private void showLibrarianInterface(String username) throws IOException {
        JFrame frame = new JFrame("Library Management System - Librarian");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel librarianPanel = new JPanel(new GridBagLayout());
        librarianPanel.setBackground(new Color(202, 206, 232));
        frame.add(librarianPanel);
        placeLibrarianComponents(librarianPanel, username);

        frame.setVisible(true);
    }

    private void placeLibrarianComponents(JPanel librarianPanel, String username) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        ImageIcon Logo = new ImageIcon("/Users/sadikatabassum/Desktop/Screenshot 2024-06-01 at 1.20.17 PM.png");
        Image logoImage = Logo.getImage().getScaledInstance(450, 150, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(logoImage));
        logoLabel.setBounds(120, 15, 1200, 200);
        librarianPanel.add(logoLabel);
        logoLabel.setVerticalAlignment(SwingConstants.TOP);
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(30, 10, 10, 10);
        librarianPanel.add(logoLabel, constraints);


        //ADDING GREETINGS
        JLabel patronLabel = new JLabel("Welcome!!");
        patronLabel.setFont(new Font("MV Boli", Font.BOLD, 50));
        patronLabel.setVerticalAlignment(SwingConstants.TOP);
        patronLabel.setHorizontalAlignment(SwingConstants.CENTER);
        patronLabel.setForeground(Color.WHITE);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(10, 10, 30, 10);
        constraints.anchor = GridBagConstraints.CENTER;
        librarianPanel.add(patronLabel, constraints);


        Dimension buttonSize = new Dimension(600, 50);


        //ADD BUTTON
        JButton addButton = new JButton("Add Book");
        addButton.setBackground(new Color(66, 39, 176));
        addButton.setForeground(Color.white);
        addButton.setOpaque(true);
        addButton.setBorderPainted(false);
        addButton.setFocusPainted(false);
        addButton.setPreferredSize(buttonSize);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(10, 10, 20, 10);
        constraints.anchor = GridBagConstraints.CENTER;
        librarianPanel.add(addButton, constraints);


        //DELETE BUTTON
        JButton deleteButton = new JButton("Delete Book");
        deleteButton.setBackground(new Color(66, 39, 176));
        deleteButton.setForeground(Color.white);
        deleteButton.setOpaque(true);
        deleteButton.setBorderPainted(false);
        deleteButton.setFocusPainted(false);
        deleteButton.setPreferredSize(buttonSize);
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.insets = new Insets(10, 10, 10, 10);
        librarianPanel.add(deleteButton, constraints);

        // CHECK AVAILIBITY BUTTON
        JButton checkAvailabilityButton = new JButton("Check Availability");
        checkAvailabilityButton.setBackground(new Color(66, 39, 176));
        checkAvailabilityButton.setForeground(Color.white);
        checkAvailabilityButton.setOpaque(true);
        checkAvailabilityButton.setBorderPainted(false);
        checkAvailabilityButton.setFocusPainted(false);
        checkAvailabilityButton.setPreferredSize(buttonSize);
        constraints.gridx = 1;
        constraints.gridy = 3;
        librarianPanel.add(checkAvailabilityButton, constraints);

        // VIEW REVIIEWS BUTTON
        JButton viewReviewsButton = new JButton("View Reviews");
        viewReviewsButton.setBackground(new Color(66, 39, 176));
        viewReviewsButton.setForeground(Color.white);
        viewReviewsButton.setOpaque(true);
        viewReviewsButton.setBorderPainted(false);
        viewReviewsButton.setFocusPainted(false);
        viewReviewsButton.setPreferredSize(buttonSize);
        constraints.gridx = 0;
        constraints.gridy = 4;
        librarianPanel.add(viewReviewsButton, constraints);

        // MANAGE CALATONG BUTTON
        JButton manageCatalogButton = new JButton("Manage Catalog");
        manageCatalogButton.setBackground(new Color(66, 39, 176));
        manageCatalogButton.setForeground(Color.white);
        manageCatalogButton.setOpaque(true);
        manageCatalogButton.setBorderPainted(false);
        manageCatalogButton.setFocusPainted(false);
        manageCatalogButton.setPreferredSize(buttonSize);
        constraints.gridx = 1;
        constraints.gridy = 4;
        librarianPanel.add(manageCatalogButton, constraints);

        // VIEW DUE DATES BUTTON
        JButton viewDueDatesButton = new JButton("View Due Dates");
        viewDueDatesButton.setBackground(new Color(66, 39, 176));
        viewDueDatesButton.setForeground(Color.white);
        viewDueDatesButton.setOpaque(true);
        viewDueDatesButton.setBorderPainted(false);
        viewDueDatesButton.setFocusPainted(false);
        viewDueDatesButton.setPreferredSize(buttonSize);
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(10, 10, 20, 10);
        constraints.anchor = GridBagConstraints.CENTER;
        librarianPanel.add(viewDueDatesButton, constraints);

        // BACK BUTTON
        JButton backButton = new JButton("Back");
        backButton.setBackground(new Color(66, 39, 176));
        backButton.setForeground(Color.white);
        backButton.setOpaque(true);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.setPreferredSize(new Dimension(150, 50));
        constraints.gridx = 0;
        constraints.gridy = 6;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(10, 500, 10, 500);
        constraints.anchor = GridBagConstraints.CENTER;
        librarianPanel.add(backButton, constraints);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = JOptionPane.showInputDialog(librarianPanel, "Enter book title:");
                String author = JOptionPane.showInputDialog(librarianPanel, "Enter book author:");
                String isbn = JOptionPane.showInputDialog(librarianPanel, "Enter book ISBN:");
                int quantity = Integer.parseInt(JOptionPane.showInputDialog(librarianPanel, "Enter book quantity:"));
                library.addBook(new Book(title, author, isbn, quantity));
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bookTitle = JOptionPane.showInputDialog(librarianPanel, "Enter book title to delete:");
                library.deleteBook(bookTitle);
            }
        });

        checkAvailabilityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bookTitle = JOptionPane.showInputDialog(librarianPanel, "Enter book title to check availability:");
                boolean isAvailable = library.isBookAvailable(bookTitle);
                JOptionPane.showMessageDialog(librarianPanel, isAvailable ? "Book is available" : "Book is not available");
            }
        });

        viewReviewsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String reviews = library.viewReviews();
                JOptionPane.showMessageDialog(librarianPanel, reviews.isEmpty() ? "No reviews available" : reviews);
            }
        });

        manageCatalogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] options = {"Update Book"};
                int choice = JOptionPane.showOptionDialog(librarianPanel, "Choose an option", "Manage Catalog",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

                switch (choice) {
                    case 0:
                        String updateTitle = JOptionPane.showInputDialog(librarianPanel, "Enter book title to update:");
                        String newTitle = JOptionPane.showInputDialog(librarianPanel, "Enter new title:");
                        String newAuthor = JOptionPane.showInputDialog(librarianPanel, "Enter new author:");
                        String newIsbn = JOptionPane.showInputDialog(librarianPanel, "Enter new ISBN:");
                        int newQuantity = Integer.parseInt(JOptionPane.showInputDialog(librarianPanel, "Enter new quantity:"));
                        library.updateBook(updateTitle, new Book(newTitle, newAuthor, newIsbn, newQuantity));
                        break;
                }
            }
        });

        viewDueDatesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dueDates = library.viewDueDates();
                JOptionPane.showMessageDialog(librarianPanel, dueDates.isEmpty() ? "No due dates available" : dueDates);
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showLoginScreen();
                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(librarianPanel);
                topFrame.dispose();
            }
        });
    }

    public static void main(String[] args) {
        new Main();
    }
}
