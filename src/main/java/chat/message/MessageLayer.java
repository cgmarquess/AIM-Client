package chat.message;

import chat.connection.FLAPConnection;
import chat.connection.SignOnException;
import java.io.IOException;

public class MessageLayer {
    private final FLAPConnection connection;

    public MessageLayer(FLAPConnection connection) {
        this.connection = connection;
    }

    public void login(String username, String password) throws IOException, SignOnException, AIMErrorException {
        connection.connect(username);

        TOCMessage signonMsg = new Toc2SignonMessage(username, password);
        sendMessage(signonMsg);

        TOCMessage response = receiveMessage();
        if (!(response instanceof ServerSignOnMessage)) {
            throw new SignOnException("Expected SIGN_ON but received: " + response.getClass().getSimpleName());
        }

        sendMessage(new TocInitDoneMessage());
    }

    public void sendMessage(TOCMessage msg) throws IOException {
        connection.writeMessage(msg.toWireFormat());
    }

    public TOCMessage receiveMessage() throws IOException, AIMErrorException {
        String raw = connection.readMessage();
        String command = TOCMessage.extractServerCommand(raw);

        if (AIMErrorException.COMMAND_STRING.equals(command)) {
            throw new AIMErrorException(raw);
        }

        switch (command) {
            case ServerSignOnMessage.COMMAND_STRING:
                return new ServerSignOnMessage(raw);
            case ServerIMIn2Message.COMMAND_STRING:
                return new ServerIMIn2Message(raw);
            case ServerUpdateBuddyMessage.COMMAND_STRING:
                return new ServerUpdateBuddyMessage(raw);
            case ServerChatJoinMessage.COMMAND_STRING:
                return new ServerChatJoinMessage(raw);
            case ServerChatInMessage.COMMAND_STRING:
                return new ServerChatInMessage(raw);
            default:
                return new TOCMessage() {
                    @Override
                    public String toWireFormat() { return raw; }
                };
        }
    }
}