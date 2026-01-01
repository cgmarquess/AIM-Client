package chat.message;

public class TocInitDoneMessage extends TOCMessage {
    @Override
    public String toWireFormat() {
        return "toc_init_done";
    }
}