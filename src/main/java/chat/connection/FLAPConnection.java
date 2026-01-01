package chat.connection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class FLAPConnection {
    private static final String TOC_SERVER = "toc.oscar.aol.com";
    private static final int TOC_PORT = 9898;
    private static final byte SIGNON_FRAME_TYPE = 1;
    private static final byte DATA_FRAME_TYPE = 2;
    private static final byte KEEP_ALIVE_FRAME_TYPE = 5;

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private short sequenceNumber;

    public synchronized void connect(String username) throws IOException, SignOnException {
        this.socket = new Socket(TOC_SERVER, TOC_PORT);
        this.in = new DataInputStream(this.socket.getInputStream());
        this.out = new DataOutputStream(this.socket.getOutputStream());
        this.sequenceNumber = 0;

        this.out.write("FLAPON\r\n\r\n".getBytes(StandardCharsets.US_ASCII));

        readFLAPSignon();
        writeFLAPSignon(username);
    }

    public void disconnect() throws IOException {
        if (this.socket != null) {
            this.socket.close();
        }
        this.socket = null;
        this.out = null;
        this.in = null;
    }

    public synchronized void writeMessage(String message) throws IOException {
        byte[] data = message.getBytes(StandardCharsets.US_ASCII);
        byte[] nullTerminated = new byte[data.length + 1];
        System.arraycopy(data, 0, nullTerminated, 0, data.length);
        nullTerminated[data.length] = 0;

        writeFLAPFrame(DATA_FRAME_TYPE, nullTerminated);
    }

    public String readMessage() throws IOException {
        FLAPFrame frame = readFLAPFrame();

        while (frame.frameType == KEEP_ALIVE_FRAME_TYPE) {
            frame = readFLAPFrame();
        }

        if (frame.frameType != DATA_FRAME_TYPE) {
            return readMessage();
        }

        return new String(frame.data, 0, frame.data.length - 1, StandardCharsets.US_ASCII);
    }

    private void readFLAPSignon() throws IOException, SignOnException {
        FLAPFrame frame = readFLAPFrame();
        if (frame.frameType != SIGNON_FRAME_TYPE) {
            throw new SignOnException("Invalid frame type during signon");
        }
        if (frame.data.length != 4) {
            throw new SignOnException("Invalid FLAP version length");
        }
    }

    private synchronized void writeFLAPSignon(String username) throws IOException {
        byte[] version = { 0, 0, 0, 1 };
        byte[] tlv = { 0, 1 };
        byte[] userBytes = username.getBytes(StandardCharsets.US_ASCII);

        int length = version.length + tlv.length + 2 + userBytes.length;
        byte[] payload = new byte[length];

        int offset = 0;
        System.arraycopy(version, 0, payload, offset, version.length);
        offset += version.length;
        System.arraycopy(tlv, 0, payload, offset, tlv.length);
        offset += tlv.length;

        payload[offset++] = (byte) ((userBytes.length >> 8) & 0xFF);
        payload[offset++] = (byte) (userBytes.length & 0xFF);

        System.arraycopy(userBytes, 0, payload, offset, userBytes.length);

        writeFLAPFrame(SIGNON_FRAME_TYPE, payload);
    }

    private FLAPFrame readFLAPFrame() throws IOException {
        if (in.readByte() != '*') {
            throw new IOException("Framing error: missing asterisk");
        }

        byte frameType = in.readByte();
        in.readShort();
        short dataLength = in.readShort();

        byte[] data = new byte[dataLength];
        in.readFully(data);

        return new FLAPFrame(frameType, data);
    }

    private void writeFLAPFrame(byte type, byte[] data) throws IOException {
        out.writeByte('*');
        out.writeByte(type);
        out.writeShort(sequenceNumber++);
        out.writeShort(data.length);
        out.write(data);
    }

    private static class FLAPFrame {
        final byte frameType;
        final byte[] data;

        FLAPFrame(byte frameType, byte[] data) {
            this.frameType = frameType;
            this.data = data;
        }
    }
}