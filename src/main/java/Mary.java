import java.util.Scanner;

public class Mary {

    public void store() {
        Scanner scanner = new Scanner(System.in);
        Task[] storage = new Task[100];
        int counter = 0;

        while (true) {
            String input = scanner.nextLine();
            String[] splitInput = input.split(" ");

            System.out.println("_________________________________________________________");
            if (splitInput.length < 2) {
                if (splitInput[0].equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println("_________________________________________________________\n");
                    break;
                }
                if (splitInput[0].equals("list")) {
                    for (int count = 0; (count < 100) && (storage[count] != null); count++) {
                        System.out.println((count + 1) + ". " + storage[count].toString());
                    }
                    System.out.println("_________________________________________________________\n");
                    continue;
                }
            }

            if (splitInput[0].equals("mark") || splitInput[0].equals("unmark")) {
                if (splitInput[1].matches("\\d+")) {
                    int index = Integer.parseInt(splitInput[1]);
                    if (splitInput[0].equals("mark")) {
                        storage[index - 1].mark();
                    } else {
                        storage[index - 1].unmark();
                    }
                    System.out.println("_________________________________________________________\n");
                    continue;
                }
            }

            System.out.println("added: " + input);
            storage[counter] = new Task(input);
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
