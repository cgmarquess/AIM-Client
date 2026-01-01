package chat.message;

public class ServerSignOnMessage extends TOCMessage {
    public static final String COMMAND_STRING = "SIGN_ON";
    private final String wireformat;

    public ServerSignOnMessage(String wireformat) {
        this.wireformat = wireformat;
    }

    @Override
    public String toWireFormat() { return this.wireformat; }
}