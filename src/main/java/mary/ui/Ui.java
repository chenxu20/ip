package mary.ui;

/**
 * A class which provides prompts and messages for the user.
 */
public class Ui {

    public void showLoadingError() {
        System.out.println("There is some issue, please restart the program");
    }

    public String exit() {
        return "Bye. Hope to see you again soon!";
    }
}
