 package server;

import java.io.*;
import models.User;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class ChatServer {
    private static final int PORT = 12345;
    private static final Set<ClientHandler> clientHandlers = ConcurrentHashMap.newKeySet();
    private static final List<String> chatHistory = Collections.synchronizedList(new ArrayList<>());
    private static final Map<String, User> users = new ConcurrentHashMap<>();
    
    public static void main(String[] args) {
        System.out.println("🚀 Chat Server starting on port " + PORT + "...");
        
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("✅ Server started successfully!");
            
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("🔗 New client connected: " + socket.getInetAddress());
                
                ClientHandler clientHandler = new ClientHandler(socket, clientHandlers, users, chatHistory);
                clientHandlers.add(clientHandler);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            System.err.println("❌ Server error: " + e.getMessage());
        }
    }
}       