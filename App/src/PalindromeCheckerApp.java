import java.util.Scanner;
public class PalindromeCheckerApp {
   public static void main(String[] args) {
       System.out.println("welcome to the palindrome checker app managment system");
       Scanner scanner=new Scanner(System.in);
       System.out.println("enter your name ");

       String original = scanner.nextLine();
       String reversed = "";

       for (int i = original.length() - 1; i >= 0; i--) {
           reversed += original.charAt(i);
       }

       if (original.equals(reversed)) {
           System.out.println(original + " is a palindrome.");
       } else {
           System.out.println(original + " is not a palindrome.");
       }
       scanner.close();
    }
}
