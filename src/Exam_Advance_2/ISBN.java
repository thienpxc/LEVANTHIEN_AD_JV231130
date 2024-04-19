package Exam_Advance_2;

import java.util.Scanner;
import java.util.Stack;

public class ISBN {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập số ISBN (10 chữ số): ");
        String input = scanner.nextLine();

        if (isValidISBN(input)) {
            System.out.println("Số ISBN nhập vào hợp lệ.");
        } else {
            System.out.println("Số ISBN nhập vào không hợp lệ.");
        }
    }

    public static boolean isValidISBN(String isbn) {
        if (isbn.length() != 10) {
            return false;
        }

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < isbn.length(); i++) {
            char c = isbn.charAt(i);
            if (!Character.isDigit(c)) {
                return false;
            }
            stack.push(Character.getNumericValue(c));
        }
        int sum = 0;
        int weight = 10;
        while (!stack.isEmpty()) {
            int digit = stack.pop();
            sum += digit * weight;
            weight--;
        }
        return sum % 11 == 0;
    }
}
