import java.util.Scanner;

public class PalindromeCheckerApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Palindrome Checker App – Recursive Linked List Version");
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        // Create PalindromeChecker object
        PalindromeChecker checker = new PalindromeChecker(input);

        // Check palindrome
        if (checker.checkPalindrome()) {
            System.out.println("It is a Palindrome");
        } else {
            System.out.println("It is Not a Palindrome");
        }

        scanner.close();
    }
}

// --- Encapsulated PalindromeChecker class ---
class PalindromeChecker {

    // Node class for singly linked list (internal)
    private static class Node {
        char data;
        Node next;
        Node(char data) { this.data = data; this.next = null; }
    }

    // Wrapper for front pointer during recursion
    private static class FrontPointer {
        Node node;
        FrontPointer(Node node) { this.node = node; }
    }

    private Node head; // head of linked list

    // Constructor: normalize string and convert to linked list
    public PalindromeChecker(String input) {
        String processed = input.replaceAll("\\s+", "").toLowerCase();

        Node tail = null;
        for (char ch : processed.toCharArray()) {
            Node newNode = new Node(ch);
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        }
    }

    // Public method to check palindrome
    public boolean checkPalindrome() {
        FrontPointer front = new FrontPointer(head);
        return isPalindromeRecursive(head, front);
    }

    // Internal recursive method
    private boolean isPalindromeRecursive(Node current, FrontPointer front) {
        if (current == null) return true;

        boolean res = isPalindromeRecursive(current.next, front);
        if (!res) return false;

        if (front.node.data != current.data) return false;

        front.node = front.node.next;
        return true;
    }
}