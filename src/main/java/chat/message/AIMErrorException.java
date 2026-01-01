package chat.message;

public class AIMErrorException extends Exception {
    public static final String COMMAND_STRING = "ERROR";

    private final String error;
    private final String args;

    public AIMErrorException(String s) {
        super(s);
        String[] fields = s.split(":", 3);
        this.error = fields.length > 1 ? fields[1] : "UNKNOWN";
        this.args = fields.length > 2 ? fields[2] : "";
    }

    public String getError() { return error; }
    public String getArgs() { return args; }
}