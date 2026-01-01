package chat.message;

public class ServerChatInMessage extends TOCMessage {
    public static final String COMMAND_STRING = "CHAT_IN";
    private final String roomId;
    private final String sourceUser;
    private final String message;
    private final String wireformat;

    public ServerChatInMessage(String wireformat) {
        this.wireformat = wireformat;
        String[] parts = wireformat.split(":", 5);
        if (parts.length >= 5) {
            this.roomId = parts[1];
            this.sourceUser = parts[2];
            this.message = parts[4];
        } else {
            this.roomId = "0";
            this.sourceUser = "UNKNOWN";
            this.message = "";
        }
    }

    public String getRoomId() { return roomId; }
    public String getSourceUser() { return sourceUser; }
    public String getMessage() { return message; }

    @Override
    public String toWireFormat() { return wireformat; }
}