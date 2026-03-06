import java.util.*;

// --- 1. The Strategy Interface ---
interface PalindromeStrategy {
    boolean isPalindrome(String text);
}

// --- 2. Concrete Strategy: Using a Stack (LIFO) ---
class StackStrategy implements PalindromeStrategy {
    @Override
    public boolean isPalindrome(String text) {
        String clean = text.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        Stack<Character> stack = new Stack<>();

        for (char ch : clean.toCharArray()) {
            stack.push(ch);
        }

        for (char ch : clean.toCharArray()) {
            if (ch != stack.pop()) return false;
        }
        return true;
    }
}

// --- 3. Concrete Strategy: Using a Deque (Double-Ended Queue) ---
class DequeStrategy implements PalindromeStrategy {
    @Override
    public boolean isPalindrome(String text) {
        String clean = text.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        Deque<Character> deque = new LinkedList<>();

        for (char ch : clean.toCharArray()) {
            deque.addLast(ch);
        }

        while (deque.size() > 1) {
            if (deque.removeFirst() != deque.removeLast()) {
                return false;
            }
        }
        return true;
    }
}

// --- 4. Concrete Strategy: Your Recursive Linked List logic ---
class RecursiveLinkedListStrategy implements PalindromeStrategy {
    private static class Node {
        char data;
        Node next;
        Node(char data) { this.data = data; }
    }

    private static class FrontPointer {
        Node node;
        FrontPointer(Node node) { this.node = node; }
    }

    @Override
    public boolean isPalindrome(String text) {
        String clean = text.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        if (clean.isEmpty()) return true;

        // Build the list
        Node head = null, tail = null;
        for (char ch : clean.toCharArray()) {
            Node newNode = new Node(ch);
            if (head == null) {
                head = tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        }

        return checkRecursive(head, new FrontPointer(head));
    }

    private boolean checkRecursive(Node current, FrontPointer front) {
        if (current == null) return true;
        if (!checkRecursive(current.next, front)) return false;
        if (current.data != front.node.data) return false;
        front.node = front.node.next;
        return true;
    }
}

// --- 5. The Context Class ---
class PalindromeChecker {
    private PalindromeStrategy strategy;

    // Dependency Injection via Setter
    public void setStrategy(PalindromeStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean validate(String text) {
        if (strategy == null) {
            System.out.println("Error: No strategy selected!");
            return false;
        }
        return strategy.isPalindrome(text);
    }
}

// --- 6. Main Application ---
public class PalindromeCheckerApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PalindromeChecker checker = new PalindromeChecker();

        System.out.println("=== Advanced Palindrome Checker (Strategy Pattern) ===");
        System.out.print("Enter string: ");
        String input = scanner.nextLine();

        System.out.println("\nSelect Algorithm Strategy:");
        System.out.println("1. Stack Strategy (LIFO)");
        System.out.println("2. Deque Strategy (Front/Back comparison)");
        System.out.println("3. Recursive Linked List Strategy");
        System.out.print("Choice: ");

        int choice = scanner.nextInt();

        // Polymorphic assignment
        switch (choice) {
            case 1 -> checker.setStrategy(new StackStrategy());
            case 2 -> checker.setStrategy(new DequeStrategy());
            case 3 -> checker.setStrategy(new RecursiveLinkedListStrategy());
            default -> {
                System.out.println("Invalid choice. Defaulting to Stack.");
                checker.setStrategy(new StackStrategy());
            }
        }

        System.out.println("------------------------------------------------");
        if (checker.validate(input)) {
            System.out.println("RESULT: '" + input + "' IS a palindrome.");
        } else {
            System.out.println("RESULT: '" + input + "' IS NOT a palindrome.");
        }

        scanner.close();
    }
}