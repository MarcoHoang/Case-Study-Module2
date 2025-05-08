package util;

import org.mindrot.jbcrypt.BCrypt;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class PasswordUtil {

    public static String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    public static boolean checkPassword(String plainTextPassword, String hashedPassword) {
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }

    public static void validatePassword(String password) {
        List<String> errors = new ArrayList<>();

        if (password.length() < 8) {
            errors.add(" - Mật khẩu phải có ít nhất 8 ký tự");
        }
        if (!password.matches(".*[A-Z].*")) {
            errors.add(" - Mật khẩu phải chứa ít nhất một chữ cái viết hoa (A-Z)");
        }
        if (!password.matches(".*[a-z].*")) {
            errors.add(" - Mật khẩu phải chứa ít nhất một chữ cái viết thường (a-z)");
        }
        if (!password.matches(".*\\d.*")) {
            errors.add(" - Mật khẩu phải chứa ít nhất một chữ số (0-9)");
        }
        if (!password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*")) {
            errors.add(" - Mật khẩu phải chứa ít nhất một ký tự đặc biệt (ví dụ: !@#$%^&*)");
        }

        if (!errors.isEmpty()) {
            String fullMessage = " Mật khẩu không hợp lệ. Vui lòng đảm bảo:\n" + String.join("\n", errors);
            throw new IllegalArgumentException(fullMessage);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String password;

        while (true) {
            System.out.println("Nhập mật khẩu của bạn:");
            password = scanner.nextLine();

            try {
                validatePassword(password);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println("Vui lòng nhập lại.\n");
            }
        }

        String hashed = hashPassword(password);
        System.out.println(" Mật khẩu đã được mã hóa: " + hashed);

        while (true) {
            System.out.println("Mời bạn xác nhận mật khẩu:");
            String plainTextPassword = scanner.nextLine();
            if (checkPassword(plainTextPassword, hashed)) {
                System.out.println("Mật khẩu chính xác");
                break;
            } else {
                System.out.println(" Mật khẩu không chính xác. Thử lại.");
            }
        }
        scanner.close();
    }
}