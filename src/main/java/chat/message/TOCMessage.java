package chat.message;

public abstract class TOCMessage {
    public abstract String toWireFormat();

    public static String quoteString(String s) {
        return "\"" + s + "\"";
    }

    public static String escapeString(String s) {
        String specialChars = "${}[]()\"\\";
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (specialChars.indexOf(c) != -1) {
                sb.append('\\');
            }
            sb.append(c);
        }
        return sb.toString();
    }

    public static String extractServerCommand(String s) {
        int idx = s.indexOf(':');
        return (idx == -1) ? s : s.substring(0, idx);
    }
}