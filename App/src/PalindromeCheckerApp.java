import java.util.*;

// --- 1. The Strategy Interface ---
interface PalindromeStrategy {
    boolean isPalindrome(String text);
    String getName(); // Added to identify the strategy in results
}

// --- 2. Stack Strategy ---
class StackStrategy implements PalindromeStrategy {
    public String getName() { return "Stack (LIFO)"; }
    @Override
    public boolean isPalindrome(String text) {
        String clean = text.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        Stack<Character> stack = new Stack<>();
        for (char ch : clean.toCharArray()) stack.push(ch);
        for (char ch : clean.toCharArray()) {
            if (ch != stack.pop()) return false;
        }
        return true;
    }
}

// --- 3. Deque Strategy ---
class DequeStrategy implements PalindromeStrategy {
    public String getName() { return "Deque (Two-Way)"; }
    @Override
    public boolean isPalindrome(String text) {
        String clean = text.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        Deque<Character> deque = new LinkedList<>();
        for (char ch : clean.toCharArray()) deque.addLast(ch);
        while (deque.size() > 1) {
            if (deque.removeFirst() != deque.removeLast()) return false;
        }
        return true;
    }
}

// --- 4. Recursive Linked List Strategy ---
class RecursiveLinkedListStrategy implements PalindromeStrategy {
    public String getName() { return "Recursive Linked List"; }
    private static class Node { char data; Node next; Node(char data) { this.data = data; } }
    private static class FrontPointer { Node node; FrontPointer(Node node) { this.node = node; } }

    @Override
    public boolean isPalindrome(String text) {
        String clean = text.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        if (clean.isEmpty()) return true;
        Node head = null, tail = null;
        for (char ch : clean.toCharArray()) {
            Node newNode = new Node(ch);
            if (head == null) head = tail = newNode;
            else { tail.next = newNode; tail = newNode; }
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

// --- 5. Main Application with Performance Comparison (UC13) ---
public class PalindromeCheckerApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== UC13: Palindrome Performance Comparison ===");
        System.out.print("Enter string to test: ");
        String input = scanner.nextLine();

        // List of strategies to compare
        List<PalindromeStrategy> strategies = Arrays.asList(
                new StackStrategy(),
                new DequeStrategy(),
                new RecursiveLinkedListStrategy()
        );

        System.out.println("\n--- Performance Results ---");
        System.out.printf("%-25s | %-10s | %-15s\n", "Algorithm", "Result", "Time (ns)");
        System.out.println("------------------------------------------------------------");

        for (PalindromeStrategy strategy : strategies) {
            // 1. Capture start time
            long startTime = System.nanoTime();

            // 2. Run the algorithm
            boolean result = strategy.isPalindrome(input);

            // 3. Capture end time
            long endTime = System.nanoTime();

            long duration = endTime - startTime;

            System.out.printf("%-25s | %-10s | %-15d\n",
                    strategy.getName(),
                    (result ? "Palindrome" : "Not Pal"),
                    duration);
        }

        System.out.println("------------------------------------------------------------");
        System.out.println("Note: 1,000,000 nanoseconds = 1 millisecond.");
        scanner.close();
    }
}