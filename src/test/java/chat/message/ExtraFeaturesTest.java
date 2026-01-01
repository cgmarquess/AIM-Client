package chat.message;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ExtraFeaturesTest {

    @Test
    void testTocChatJoinMessageWireFormat() {
        TocChatJoinMessage msg = new TocChatJoinMessage("Java Room");
        String result = msg.toWireFormat();

        assertEquals("toc_chat_join 4 \"Java Room\"", result);
    }

    @Test
    void testTocChatSendMessageWireFormat() {
        TocChatSendMessage msg = new TocChatSendMessage("1234", "Hello Room");
        String result = msg.toWireFormat();

        assertEquals("toc_chat_send 1234 \"Hello Room\"", result);
    }

    @Test
    void testTocAddBuddyMessageWireFormat() {
        List<String> buddies = Arrays.asList("friend1", "friend2");
        TocAddBuddyMessage msg = new TocAddBuddyMessage("Friends", buddies);

        String result = msg.toWireFormat();

        assertTrue(result.startsWith("toc2_new_buddies"));

        assertTrue(result.contains("\"g:Friends\n"));
        assertTrue(result.contains("b:friend1\n"));
        assertTrue(result.contains("b:friend2\n\""));
    }

    @Test
    void testServerUpdateBuddyMessageParse() {
        String input = "UPDATE_BUDDY2:cool_user:T:0:123456:0:UA:?";
        ServerUpdateBuddyMessage msg = new ServerUpdateBuddyMessage(input);

        assertEquals("cool_user", msg.getBuddyUser());
        assertTrue(msg.isOnline());
    }

    @Test
    void testServerChatJoinMessageParse() {
        String input = "CHAT_JOIN:5678:MyChat";
        ServerChatJoinMessage msg = new ServerChatJoinMessage(input);

        assertEquals("5678", msg.getRoomId());
        assertEquals("MyChat", msg.getRoomName());
    }

    @Test
    void testServerChatInMessageParse() {
        String input = "CHAT_IN:5678:sender_guy:F:Hello everyone";
        ServerChatInMessage msg = new ServerChatInMessage(input);

        assertEquals("5678", msg.getRoomId());
        assertEquals("sender_guy", msg.getSourceUser());
        assertEquals("Hello everyone", msg.getMessage());
    }
}