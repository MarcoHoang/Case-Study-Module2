package model;

public class Borrower extends User {
    private String phone;
    private String email;
    private String borrowerType;

    public Borrower() {}

    public Borrower(String id, String username, String password, String phone, String borrowerType) {
        super(id, username, password);
        this.phone = phone;
        this.email = email;
        this.borrowerType = borrowerType;
    }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getBorrowerType() { return borrowerType; }
    public void setBorrowerType(String borrowerType) { this.borrowerType = borrowerType; }
}
