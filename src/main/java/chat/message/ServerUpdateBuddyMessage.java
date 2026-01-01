package chat.message;

public class ServerUpdateBuddyMessage extends TOCMessage {
    public static final String COMMAND_STRING = "UPDATE_BUDDY2";
    private final String buddyUser;
    private final boolean online;
    private final String wireformat;

    public ServerUpdateBuddyMessage(String wireformat) {
        this.wireformat = wireformat;
        String[] parts = wireformat.split(":", 8);
        if (parts.length >= 3) {
            this.buddyUser = parts[1];
            this.online = "T".equalsIgnoreCase(parts[2]);
        } else {
            this.buddyUser = "UNKNOWN";
            this.online = false;
        }
    }

    public String getBuddyUser() { return buddyUser; }
    public boolean isOnline() { return online; }

    @Override
    public String toWireFormat() { return wireformat; }
}