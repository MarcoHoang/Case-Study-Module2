package model;

import java.io.Serializable;

public abstract class User implements Serializable {
    private String id;
    private String userName;
    private String password;
    private String email;
    private String phone;
    private int age;
    private Gender gender;

    public User(String id, String userName, String password, String email,
                String phone, int age, Gender gender) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.age = age;
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public int getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
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
                        "╚══════════════════════════════════════╝\n",
                id, userName, "********", email, phone, age, gender
        );
    }

}
