package util;

import model.Gender;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InputHelper {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\d{10,11}$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$");
    // private static final Pattern DATE_PATTERN = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");

    public static String inputString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public static String inputNonEmptyString(String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Lỗi: Trường này không được để trống. Vui lòng nhập lại.");
            }
        } while (input.isEmpty());
        return input;
    }

    public static int inputInt(String prompt) {
        while (true) {
            String input = inputNonEmptyString(prompt);
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập một số nguyên hợp lệ. Vui lòng nhập lại.");
            }
        }
    }

    public static LocalDate inputDate(String prompt) {
        while (true) {
            String dateStr = inputNonEmptyString(prompt);
            try {
                return LocalDate.parse(dateStr);
            } catch (DateTimeParseException e) {
                System.out.println("Lỗi: Định dạng ngày không hợp lệ. Vui lòng nhập (yyyy-MM-dd). Vui lòng nhập lại.");
            }
        }
    }

    public static String inputPhone(String prompt) {
        String phone;
        do {
            phone = inputNonEmptyString(prompt);
            if (!PHONE_PATTERN.matcher(phone).matches()) {
                System.out.println("Lỗi: Số điện thoại không hợp lệ (phải là 10 hoặc 11 chữ số). Vui lòng nhập lại.");
            }
        } while (!PHONE_PATTERN.matcher(phone).matches());
        return phone;
    }

    public static String inputEmail(String prompt) {
        String email;
        do {
            email = inputNonEmptyString(prompt);
            if (!EMAIL_PATTERN.matcher(email).matches()) {
                System.out.println("Lỗi: Email không đúng định dạng (ví dụ: user@example.com). Vui lòng nhập lại.");
            }
        } while (!EMAIL_PATTERN.matcher(email).matches());
        return email;
    }

    public static int inputIntInRange(String prompt, int min, int max) {
        int number;
        do {
            number = inputInt(prompt);
            if (number < min || number > max) {
                System.out.println("Lỗi: Vui lòng nhập số trong khoảng từ " + min + " đến " + max + ". Vui lòng nhập lại.");
            }
        } while (number < min || number > max);
        return number;
    }

    public static Gender inputGender(String prompt) {
        while (true) {
            String input = inputNonEmptyString(prompt).trim().toUpperCase();
            try {
                return Gender.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Lỗi: Giới tính không hợp lệ. Vui lòng nhập NAM, NỮ hoặc KHÁC.");
            }
        }
    }
}