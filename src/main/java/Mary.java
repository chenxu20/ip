import java.util.Scanner;

public class Mary {

    public void echo() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String repeat = scanner.nextLine();
            if (repeat.equals("bye")) {
                System.out.println("_______________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("_______________________________\n");
                break;
            }
            System.out.println("_______________________________");
            System.out.println(repeat);
            System.out.println("_______________________________\n");
        }
        scanner.close();
    }

    public static void main(String[] args) {
        Mary mary = new Mary();
        String welcome = "Hello! I'm Mary \n"
                + "What can I do for you? \n\n"
                + "----------------------------";

        System.out.println(welcome);
        mary.echo();
    }
}
