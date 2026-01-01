package chat.message;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TOCMessageTest {

    @Test
    void testEscapeString() {
        String input = "${}[]()\"\\";
        String expected = "\\$\\{\\}\\[\\]\\(\\)\\\"\\\\";
        assertEquals(expected, TOCMessage.escapeString(input));
    }

    @Test
    void testEscapeNormalString() {
        String input = "abcdefghijklmnop";
        assertEquals(input, TOCMessage.escapeString(input));
    }

    @Test
    void testQuoteString() {
        String input = "test";
        String expected = "\"test\"";
        assertEquals(expected, TOCMessage.quoteString(input));
    }

    @Test
    void testExtractServerCommand() {
        String input = "IM_IN2:user:T:msg";
        assertEquals("IM_IN2", TOCMessage.extractServerCommand(input));
    }

    @Test
    void testExtractServerCommandNoArgs() {
        String input = "PAUSE";
        assertEquals("PAUSE", TOCMessage.extractServerCommand(input));
    }
}