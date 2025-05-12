package model;

import java.io.Serializable;

public class Borrower extends User implements Serializable {

    private String borrowerType;

    public Borrower(String id, String username, String password, String email,
                    String phone, int age, Gender gender, String borrowerType) {
        super(id, username, password, email, phone, age, gender);
        this.borrowerType = borrowerType;
    }

    public String getBorrowerType() {
        return borrowerType;
    }

    public void setBorrowerType(String borrowerType) {
        this.borrowerType = borrowerType;
    }

    @Override
    public String toString() {
        String parentInfo = super.toString();

        int lastLineIndex = parentInfo.lastIndexOf("╚");
        String borrowerLine = String.format("║ Borrower Type : %-20s ║\n", borrowerType);

        return parentInfo.substring(0, lastLineIndex) + borrowerLine + parentInfo.substring(lastLineIndex);
    }
}
