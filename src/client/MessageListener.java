package client;

import models.Message;
import javax.swing.*;
import java.io.ObjectInputStream;
import java.util.List;

public class MessageListener implements Runnable {
    private ObjectInputStream in;
    private ClientGUI gui;
    
    public MessageListener(ObjectInputStream in, ClientGUI gui) {
        this.in = in;
        this.gui = gui;
    }
    
    @Override
    public void run() {
        try {
            while (true) {
                Object obj = in.readObject();
                
                if (obj instanceof Message) {
                    Message message = (Message) obj;
                    String formattedMsg;
                    
                    // Check if it's a server message
                    if (message.getSender().equals("Server")) {
                        String content = message.getContent();
                        if (content.contains("joined")) {
                            formattedMsg = "🟢 " + message.toString();
                        } else if (content.contains("left")) {
                            formattedMsg = "🔴 " + message.toString();
                        } else {
                            formattedMsg = "⚙ " + message.toString();
                        }
                    } else {
                        // Regular user message
                        formattedMsg = message.toString();
                    }
                    
                    // Call the GUI method to append message
                    gui.appendToChat(formattedMsg);
                    
                } else if (obj instanceof Object[]) {
                    Object[] data = (Object[]) obj;
                    if (data[0].equals("USER_LIST")) {
                        @SuppressWarnings("unchecked")
                        List<String> users = (List<String>) data[1];
                        // Call the GUI method to update user list
                        gui.updateUserList(users);
                    }
                }
            }
        } catch (Exception e) {
            // Use SwingUtilities to show error dialog in EDT
            SwingUtilities.invokeLater(() -> {
                JOptionPane.showMessageDialog(gui, 
                    "Disconnected from server!\nError: " + e.getMessage(),
                    "Connection Error",
                    JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            });
        }
    }
}