package chat.message;

public class ServerIMIn2Message extends TOCMessage {
    public static final String COMMAND_STRING = "IM_IN2";

    private final String sourceUser;
    private final boolean autoResponse;
    private final String message;
    private final String wireformat;

    public ServerIMIn2Message(String wireformat) {
        this.wireformat = wireformat;
        String[] parts = wireformat.split(":", 5);
        if (parts.length >= 5) {
            this.sourceUser = parts[1];
            this.autoResponse = "T".equalsIgnoreCase(parts[2]);
            this.message = parts[4];
        } else {
            this.sourceUser = "UNKNOWN";
            this.autoResponse = false;
            this.message = "";
        }
    }

    public String getSourceUser() { return sourceUser; }
    public String getMessage() { return message; }

    @Override
    public String toWireFormat() { return wireformat; }
}