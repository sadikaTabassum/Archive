class User {

    //ATTRIBUTES
    private String name;
    private String phoneNumber;
    private String password;
    private boolean isAdmin;

    //CONSTRUCTOR
    public User(String name, String phoneNumber, String password, boolean isAdmin) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.isAdmin = isAdmin;
    }


    //ACCESS TO PRIAVTE ATTRIBUTES
    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}
