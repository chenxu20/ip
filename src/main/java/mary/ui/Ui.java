package mary.ui;

public class Ui {

    public void showLoadingError() {
        System.out.println("There is some issue, please restart the program");
    }

    public void welcomeMessage() {
        String welcome = "Hello! I'm Mary\n"
                + "What can I do for you?\n\n"
                + "----------------------------";

        System.out.println(welcome);
    }

    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void printLine() {
        System.out.println("_________________________________________________________");
    }

    public void printNewline() {
        System.out.println("_________________________________________________________\n\n");
    }
}
