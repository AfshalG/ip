import java.util.Scanner;

public class Genie {
    public static void main(String[] args) {
        String line = "____________________________________________________________";
        Scanner scanner = new Scanner(System.in);

        // Print greeting
        System.out.println(line);
        System.out.println(" Hello! I'm Genie");
        System.out.println(" What can I do for you?");
        System.out.println(line);
        // Input loop
        while (true) {
            System.out.println();
            String input = scanner.nextLine();
            System.out.println(line);

            if (input.equals("bye")) {
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println(line);
                break;
            } else {
                // Echo the input
                System.out.println(" " + input);
                System.out.println(line);
            }
        }

        scanner.close();
}
}
