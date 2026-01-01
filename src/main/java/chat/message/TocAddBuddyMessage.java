package chat.message;

import java.util.List;

public class TocAddBuddyMessage extends TOCMessage {
    private final List<String> buddies;
    private final String group;

    public TocAddBuddyMessage(String group, List<String> buddies) {
        this.group = group;
        this.buddies = buddies;
    }

    @Override
    public String toWireFormat() {
        StringBuilder sb = new StringBuilder();
        sb.append("g:").append(group).append("\n");
        for (String buddy : buddies) {
            sb.append("b:").append(buddy).append("\n");
        }
        return "toc2_new_buddies " + quoteString(sb.toString());
    }
}