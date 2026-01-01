package chat.message;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ServerIMIn2MessageTest {

    @Test
    void testParseValidMessage() {
        String input = "IM_IN2:friend:T:F:Hello World";
        ServerIMIn2Message msg = new ServerIMIn2Message(input);

        assertEquals("friend", msg.getSourceUser());
        assertEquals("Hello World", msg.getMessage());
    }

    @Test
    void testParseMessageWithColonsInBody() {
        String input = "IM_IN2:friend:T:F:Hello: World: 2";
        ServerIMIn2Message msg = new ServerIMIn2Message(input);

        assertEquals("Hello: World: 2", msg.getMessage());
    }
}