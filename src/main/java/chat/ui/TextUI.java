package chat.ui;

import chat.connection.FLAPConnection;
import chat.message.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;

public class TextUI {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java chat.ui.TextUI <username> <password>");
            return;
        }

        String username = args[0];
        String password = args[1];

        FLAPConnection connection = new FLAPConnection();
        MessageLayer messageLayer = new MessageLayer(connection);

        try {
            System.out.println("Connecting...");
            messageLayer.login(username, password);
            System.out.println("Logged in as " + username);
        } catch (Exception e) {
            System.err.println("Login failed: " + e.getMessage());
            return;
        }

        Thread listenerThread = new Thread(() -> {
            while (true) {
                try {
                    TOCMessage msg = messageLayer.receiveMessage();

                    if (msg instanceof ServerIMIn2Message) {
                        ServerIMIn2Message im = (ServerIMIn2Message) msg;
                        System.out.println("\n[IM] " + im.getSourceUser() + ": " + im.getMessage());
                        System.out.print(">>> ");
                    } else if (msg instanceof ServerChatInMessage) {
                        ServerChatInMessage cm = (ServerChatInMessage) msg;
                        System.out.println("\n[Chat " + cm.getRoomId() + "] " + cm.getSourceUser() + ": " + cm.getMessage());
                        System.out.print(">>> ");
                    } else if (msg instanceof ServerChatJoinMessage) {
                        ServerChatJoinMessage join = (ServerChatJoinMessage) msg;
                        System.out.println("\n[System] Joined room '" + join.getRoomName() + "' (ID: " + join.getRoomId() + ")");
                        System.out.print(">>> ");
                    } else if (msg instanceof ServerUpdateBuddyMessage) {
                        ServerUpdateBuddyMessage buddy = (ServerUpdateBuddyMessage) msg;
                        String status = buddy.isOnline() ? "Online" : "Offline";
                        System.out.println("\n[Buddy] " + buddy.getBuddyUser() + " is now " + status);
                        System.out.print(">>> ");
                    }

                } catch (Exception e) {
                    System.err.println("\nConnection lost.");
                    break;
                }
            }
        });
        listenerThread.setDaemon(true);
        listenerThread.start();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            boolean running = true;
            System.out.print(">>> ");

            while (running) {
                String line = reader.readLine();
                if (line == null) break;

                String[] parts = line.trim().split("\\s+", 3);
                String cmd = parts[0].toLowerCase();

                if ("exit".equals(cmd)) {
                    running = false;
                } else if ("im".equals(cmd) && parts.length >= 3) {
                    messageLayer.sendMessage(new Toc2SendIMMessage(parts[1], parts[2]));
                } else if ("addbuddy".equals(cmd) && parts.length >= 3) {
                    messageLayer.sendMessage(new TocAddBuddyMessage(parts[1], Collections.singletonList(parts[2])));
                    System.out.println("Added " + parts[2] + " to group " + parts[1]);
                } else if ("join".equals(cmd) && parts.length >= 2) {
                    messageLayer.sendMessage(new TocChatJoinMessage(parts[1]));
                } else if ("say".equals(cmd) && parts.length >= 3) {
                    messageLayer.sendMessage(new TocChatSendMessage(parts[1], parts[2]));
                } else if ("help".equals(cmd)) {
                    System.out.println("Commands:");
                    System.out.println("  im <user> <msg>         - Send direct message");
                    System.out.println("  addbuddy <group> <user> - Add buddy to list");
                    System.out.println("  join <roomName>         - Join chat room");
                    System.out.println("  say <roomID> <msg>      - Talk in chat room");
                    System.out.println("  exit                    - Close client");
                } else {
                    System.out.println("Unknown command. Type 'help'.");
                }

                System.out.print(">>> ");
            }
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}