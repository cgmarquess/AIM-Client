package chat.message;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Toc2SignonMessageTest {

    @Test
    void testRoastedPasswordLogic() {
        Toc2SignonMessage msg = new Toc2SignonMessage("test", "password");
        String wireFormat = msg.toWireFormat();

        assertTrue(wireFormat.contains("0x2408105c23001130"));
    }

    @Test
    void testGenerateCodeLogic() {
        Toc2SignonMessage msg = new Toc2SignonMessage("test", "x5435");
        String wireFormat = msg.toWireFormat();

        assertTrue(wireFormat.contains("107128320"));
    }

    @Test
    void testWireFormatStructure() {
        Toc2SignonMessage msg = new Toc2SignonMessage("user", "pass");
        String result = msg.toWireFormat();

        assertTrue(result.startsWith("toc2_signon"));
        assertTrue(result.contains("login.oscar.aol.com"));
        assertTrue(result.contains("5190"));
        assertTrue(result.contains("\"english\""));
        assertTrue(result.contains("\"TIC:TBP2\""));
    }
}