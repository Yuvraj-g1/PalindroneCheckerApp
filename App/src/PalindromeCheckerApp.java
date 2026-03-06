import java.util.Scanner;

public class PalindromeCheckerApp {

    // Node class for singly linked list
    static class Node {
        char data;
        Node next;
        Node(char data) {
            this.data = data;
            this.next = null;
        }
    }

    // Wrapper class to hold front pointer during recursion
    static class FrontPointer {
        Node node;
        FrontPointer(Node node) { this.node = node; }
    }

    // Recursive function to check palindrome using linked list
    public static boolean isPalindromeRecursive(Node current, FrontPointer front) {
        if (current == null) return true; // base case: end of list

        // Recurse to the end
        boolean res = isPalindromeRecursive(current.next, front);
        if (!res) return false;

        // Compare front node and current node
        if (front.node.data != current.data) return false;

        // Move front pointer forward
        front.node = front.node.next;

        return true;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Palindrome Checker App – Recursive Linked List Version");

        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        // Normalize string: remove spaces and ignore case
        String processed = input.replaceAll("\\s+", "").toLowerCase();

        // Convert string to linked list
        Node head = null, tail = null;
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

        // Create a front pointer for recursion
        FrontPointer front = new FrontPointer(head);

        if (isPalindromeRecursive(head, front)) {
            System.out.println("It is a Palindrome");
        } else {
            System.out.println("It is Not a Palindrome");
        }

        scanner.close();
    }
}