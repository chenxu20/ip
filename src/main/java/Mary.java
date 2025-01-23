import java.util.Scanner;

public class Mary {

    public void store() {
        Scanner scanner = new Scanner(System.in);
        String[] storage = new String[100];
        int counter = 0;

        while (true) {
            String item = scanner.nextLine();
            System.out.println("_________________________________________________________");
            if (item.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("_________________________________________________________\n");
                break;
            }
            if (item.equals("list")) {
                for (int count = 0; (count < 100) && (storage[count] != null); count++) {
                    System.out.println((count + 1) + ". " + storage[count]);
                }
                System.out.println("_________________________________________________________\n");
                continue;
            }
            System.out.println("added: " + item);
            storage[counter] = item;
            counter++;
            System.out.println("_________________________________________________________\n");
        }
        scanner.close();
    }

    public static void main(String[] args) {
        Mary mary = new Mary();
        String welcome = "Hello! I'm Mary \n"
                + "What can I do for you? \n\n"
                + "----------------------------";

        System.out.println(welcome);
        mary.store();
    }
}
