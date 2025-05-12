package model;

public class Admin extends User {

    public Admin(String id, String userName, String password, String email,
                 String phone, int age, Gender gender) {
        super(id, userName, password, email, phone, age, gender);
    }

    @Override
    public String toString() {
        return String.format(
                "\n" +
                        "╔══════════════════════════════════════╗\n" +
                        "║ ID       : %-25s ║\n" +
                        "║ Username : %-25s ║\n" +
                        "║ Password : %-25s ║\n" +
                        "║ Email    : %-25s ║\n" +
                        "║ Phone    : %-25s ║\n" +
                        "║ Age      : %-25d ║\n" +
                        "║ Gender   : %-25s ║\n" +
                        "║ Role     : %-25s ║\n" +
                        "╚══════════════════════════════════════╝\n",
                getId(), getUserName(), "********", getEmail(), getPhone(), getAge(), getGender(), "Admin"
        );
    }
}
