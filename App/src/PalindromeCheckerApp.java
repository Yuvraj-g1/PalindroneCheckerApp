import java.util.Scanner;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;
public class PalindromeCheckerApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Stack<Character> stack = new Stack<>();
        Queue<Character> queue = new LinkedList<>();

        System.out.println("Palindrome Checker App");
        System.out.println("Version 6.0");

        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        // Enqueue and Push characters
        for (int i = 0; i < input.length(); i++) {

            char ch = input.charAt(i);

            stack.push(ch);     // LIFO
            queue.add(ch);      // FIFO
        }

        boolean isPalindrome = true;

        // Compare pop (stack) and dequeue (queue)
        while (!stack.isEmpty()) {

            char fromStack = stack.pop();   // LIFO
            char fromQueue = queue.remove(); // FIFO

            if (fromStack != fromQueue) {
                isPalindrome = false;
                break;
            }
        }

        if (isPalindrome) {
            System.out.println("It is a Palindrome");
        } else {
            System.out.println("It is Not a Palindrome");
        }

        scanner.close();
    }
}