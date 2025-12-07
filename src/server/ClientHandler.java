package server;

import models.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class ClientHandler implements Runnable {
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private String username;
    private Set<ClientHandler> clientHandlers;
    private Map<String, User> users;
    private List<String> chatHistory;
    
    public ClientHandler(Socket socket, Set<ClientHandler> clientHandlers, 
                        Map<String, User> users, List<String> chatHistory) {
        this.socket = socket;
        this.clientHandlers = clientHandlers;
        this.users = users;
        this.chatHistory = chatHistory;
    }
    
    @Override
    public void run() {
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            
            // Get username
            username = (String) in.readObject();
            users.put(username, new User(username));
            
            System.out.println("👤 User '" + username + "' joined the chat");
            
            // Send chat history
            out.writeObject(chatHistory);
            out.flush();
            
            // Notify all clients about new user
            broadcastMessage(new Message("Server", username + " joined the chat"));
            
            // Broadcast updated user list
            broadcastUserList();
            
            // Handle messages
            while (true) {
                Message message = (Message) in.readObject();
                chatHistory.add(message.toString());
                broadcastMessage(message);
            }
            
        } catch (IOException | ClassNotFoundException e) {
            // Handle client disconnect
            if (username != null) {
                users.remove(username);
                clientHandlers.remove(this);
                broadcastMessage(new Message("Server", username + " left the chat"));
                broadcastUserList();
                System.out.println("👋 User '" + username + "' disconnected");
            }
        }
    }
    
    private void broadcastMessage(Message message) {
        for (ClientHandler handler : clientHandlers) {
            try {
                handler.out.writeObject(message);
                handler.out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void broadcastUserList() {
        List<String> userList = new ArrayList<>(users.keySet());
        for (ClientHandler handler : clientHandlers) {
            try {
                handler.out.writeObject(new Object[]{"USER_LIST", userList});
                handler.out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}