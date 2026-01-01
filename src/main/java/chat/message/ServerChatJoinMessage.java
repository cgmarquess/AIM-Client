package chat.message;

public class ServerChatJoinMessage extends TOCMessage {
    public static final String COMMAND_STRING = "CHAT_JOIN";
    private final String roomId;
    private final String roomName;
    private final String wireformat;

    public ServerChatJoinMessage(String wireformat) {
        this.wireformat = wireformat;
        String[] parts = wireformat.split(":", 3);
        if (parts.length >= 3) {
            this.roomId = parts[1];
            this.roomName = parts[2];
        } else {
            this.roomId = "0";
            this.roomName = "UNKNOWN";
        }
    }

    public String getRoomId() { return roomId; }
    public String getRoomName() { return roomName; }

    @Override
    public String toWireFormat() { return wireformat; }
}