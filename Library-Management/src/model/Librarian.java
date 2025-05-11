package model;

import java.io.Serializable;

public class Librarian extends User implements Serializable {

    public Librarian(String id, String username, String password, String email,
                     String phone, int age, Gender gender) {
        super(id, username, password, email, phone, age, gender);
    }
}
