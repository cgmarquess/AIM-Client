package chat.message;

public class Toc2SignonMessage extends TOCMessage {
    private static final String LOGIN_SERVER = "login.oscar.aol.com";
    private static final int LOGIN_PORT = 5190;
    private static final String ROASTING_STRING = "Tic/Toc";
    private static final String VERSION = "TIC:TBP2";
    private static final String LANGUAGE = "english";

    private final String username;
    private final String password;

    public Toc2SignonMessage(String username, String password) {
        this.username = username;
        this.password = password;
    }

    private String roastedPassword() {
        StringBuilder sb = new StringBuilder("0x");
        for (int i = 0; i < password.length(); i++) {
            int roastByte = ROASTING_STRING.charAt(i % ROASTING_STRING.length());
            int passByte = password.charAt(i);
            int xor = roastByte ^ passByte;
            String hex = Integer.toHexString(xor);
            if (hex.length() < 2) sb.append('0');
            sb.append(hex);
        }
        return sb.toString();
    }

    private String generateCode() {
        int sn = username.charAt(0) - 96;
        int pw = password.charAt(0) - 96;
        int a = sn * 7696 + 738816;
        int b = sn * 746512;
        int c = pw * a;
        return String.valueOf(c - a + b + 71665152);
    }

    @Override
    public String toWireFormat() {
        return "toc2_signon " + LOGIN_SERVER + " " + LOGIN_PORT + " " +
                quoteString(username) + " " + roastedPassword() + " " +
                quoteString(LANGUAGE) + " " + quoteString(VERSION) + " " +
                "160 " + generateCode();
    }
}