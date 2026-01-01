package chat.message;

public class TocChatJoinMessage extends TOCMessage {
    private final String roomName;
    private static final int EXCHANGE = 4;

    public TocChatJoinMessage(String roomName) {
        this.roomName = roomName;
    }

    @Override
    public String toWireFormat() {
        return "toc_chat_join " + EXCHANGE + " " + quoteString(escapeString(roomName));
    }
}