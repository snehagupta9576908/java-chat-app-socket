
# Java Socket Chat Application

A multi-client chat application built using **Java socket programming**, where multiple users can connect to a central server and exchange messages in real time.

---

## 🚀 Features
- Client–server architecture using sockets
- Supports multiple concurrent clients
- Real-time message broadcasting
- GUI-based client application
- Threaded server for handling multiple users

---

## 🛠️ Tech Stack
- Java
- Socket Programming
- Multithreading
- Swing (for Client GUI)
- Git & GitHub

---

## 📂 Project Structure
src/ ├── client/ │    ├── ChatClient.java │    ├── ClientGUI.java │    └── MessageListener.java │ ├── server/ │    ├── ChatServer.java │    ├── ClientHandler.java │    └── UserManager.java │ └── models/ ├── User.java ├── Message.java └── ChatRoom.java
Copy code

---

## ▶️ How to Run the Project

### 1️⃣ Start the Server
- Run `ChatServer.java`
- Server will start on a predefined port (e.g., `12345`)

### 2️⃣ Start the Client
- Run `ClientGUI.java`
- Enter a username to join the chat
- Run multiple clients to simulate multiple users

---

## 🧪 Testing
- Tested client-server communication locally
- Verified multiple client connections
- Ensured real-time message delivery

---

## 📌 Learning Outcomes
- Understanding of client–server architecture
- Hands-on experience with Java sockets
- Handling concurrency using threads
- Real-time communication between multiple clients

---

## 🔮 Future Enhancements
- Convert socket-based communication to REST APIs using Spring Boot
- Add user authentication
- Store chat history in a database
- Build a web-based frontend

---

## 👩‍💻 Author
**Sneha Gupta**  
Backend Developer (Java)
