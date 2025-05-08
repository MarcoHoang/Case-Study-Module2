package model;

public class Librarian extends User {

    public Librarian(String id, String username, String password, String email,
                     String phone, int age, String gender) {
        super(id, username, password, email, phone, age, gender);
    }

    @Override
    public String getRole() {
        return "librarian";
    }
}
