import java.util.Scanner;

public class PalindromeCheckerApp {

    // Recursive function to check palindrome
    public static boolean isPalindrome(String str, int start, int end) {
        // Base condition: crossed pointers or single character
        if (start >= end) {
            return true;
        }

        // If characters don't match
        if (str.charAt(start) != str.charAt(end)) {
            return false;
        }

        // Recursive call for inner substring
        return isPalindrome(str, start + 1, end - 1);
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Palindrome Checker App");
        System.out.println("Version 9.0 – Recursive");

        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        // Remove spaces and ignore case (optional)
        String processed = input.replaceAll("\\s+", "").toLowerCase();

        if (isPalindrome(processed, 0, processed.length() - 1)) {
            System.out.println("It is a Palindrome");
        } else {
            System.out.println("It is Not a Palindrome");
        }

        scanner.close();
    }
}