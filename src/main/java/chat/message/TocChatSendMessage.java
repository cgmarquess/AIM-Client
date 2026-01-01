package chat.message;

public class TocChatSendMessage extends TOCMessage {
    private final String roomId;
    private final String message;

    public TocChatSendMessage(String roomId, String message) {
        this.roomId = roomId;
        this.message = message;
    }

    @Override
    public String toWireFormat() {
        return "toc_chat_send " + roomId + " " + quoteString(escapeString(message));
    }
}