class Review {

    //ATTRIBUTES
    private String name;
    private String bookTitle;
    private String reviewText;


    //CONSTRUCTOR
    public Review(String name, String bookTitle, String reviewText) {
        this.name = name;
        this.bookTitle = bookTitle;
        this.reviewText = reviewText;
    }

    //ACCESS TO PRIAVTE ATTRIBUTES
    public String getName() {
        return name;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getReviewText() {
        return reviewText;
    }

    //HUMAN READABLE STRING PRESENTATION OF REVIEW OBJECT
    @Override
    public String toString() {
        return "Review{" +
                "name='" + name + '\'' +
                ", bookTitle='" + bookTitle + '\'' +
                ", reviewText='" + reviewText + '\'' +
                '}';
    }
}
