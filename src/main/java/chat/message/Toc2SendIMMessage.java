package chat.message;

public class Toc2SendIMMessage extends TOCMessage {
    private final String destinationUser;
    private final String message;

    public Toc2SendIMMessage(String destinationUser, String message) {
        this.destinationUser = destinationUser;
        this.message = message;
    }

    @Override
    public String toWireFormat() {
        return "toc2_send_im " + quoteString(destinationUser) + " " + quoteString(escapeString(message));
    }
}