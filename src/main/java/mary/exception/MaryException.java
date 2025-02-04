package mary.exception;

/**
 * throws exceptions to errors raised within the chatbot
 */
public class MaryException extends Exception {
    public MaryException(String message) {
        super(message);
    }
}
