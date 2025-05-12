package util;

import model.Gender;

public class AccountHelper {

    public static AccountInfo getAccountInfo(String accountType) {
        System.out.println("=== NHẬP THÔNG TIN " + accountType + " ===");
        String id = InputHelper.inputNonEmptyString("ID: ");

        String username;
        username = InputHelper.inputNonEmptyString("Tên đăng nhập: ");

        String password = InputHelper.getValidatedPassword();
        String hashedPassword = PasswordUtil.hashPassword(password);

        String email = InputHelper.inputEmail("Email: ");
        String phone = InputHelper.inputPhone("Số điện thoại: ");
        int age = InputHelper.inputIntInRange("Tuổi: ", 1, 150);
        Gender gender = InputHelper.inputGender("Giới tính (Male/Female/Unknown): ");

        return new AccountInfo(id, username, hashedPassword, email, phone, age, gender);
    }

    public static class AccountInfo {
        public String id;
        public String username;
        public String hashedPassword;
        public String email;
        public String phone;
        public int age;
        public Gender gender;

        public AccountInfo(String id, String username, String hashedPassword, String email, String phone, int age, Gender gender) {
            this.id = id;
            this.username = username;
            this.hashedPassword = hashedPassword;
            this.email = email;
            this.phone = phone;
            this.age = age;
            this.gender = gender;
        }
    }
}