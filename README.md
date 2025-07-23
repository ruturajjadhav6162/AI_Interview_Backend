# 🧠 Interview Service

The **Interview Service** is a Spring Boot microservice that facilitates AI-driven interviews between candidates and an AI Interview Bot using **WebSocket** for real-time communication and **gRPC** for backend-to-AI messaging.

---

## 📦 Features

- AI-powered real-time candidate interviews
- WebSocket-based one-to-one chat between frontend and backend
- gRPC communication between Spring Boot and Python AI Interviewer
- JWT-based authentication (user-service)

---

## 🚀 WebSocket (One-to-One, Username-Based)

The Interview Service uses **username-based one-to-one WebSocket connections** for real-time communication between:

- The **candidate** and the **AI Interviewer** (via Spring Boot backend)
- Each user (candidate or admin) is connected **individually** using their **username**

### 🔐 WebSocket Security

- WebSocket connection is initiated at:  
  `ws://loaclhost:8003/websocket?token=<JWT>`
- The JWT is intercepted using `JwtHandshakeInterceptor` during the handshake phase.
- The token contains:
    - `username`
    - `role`
- Only authenticated users are allowed to establish a connection.

### 🧠 Flow of Communication

1. Frontend connects to WebSocket using token.
2. Spring backend extracts the **username** from token and maps it to the current session.
3. Messages are sent to `/app/chat`
4. Backend routes the message to the authenticated user via `/user/{username}/queue/chat`.
5. Each message is:
    - Stored in DB with sender info and timestamp.
    - Forwarded to the AI Interviewer via gRPC.
6. AI’s response is routed back **only to the sender** via `/user/{username}/queue/chat`.

> ✅ This ensures **strict one-to-one privacy** — no message leakage between users.

---

## 🔗 gRPC Communication

The Interview Service connects to a **Python-based AI Interviewer** using **gRPC** for processing candidate messages and generating AI responses.

### 🧠 Flow

1. A message received via WebSocket is passed to the `InterviewService` class.
2. This message is sent to the Python AI server over gRPC using a defined `.proto` contract.
3. AI returns a generated response.
4. The response is sent back to the user via WebSocket and stored in DB.
  
` .proto` File
```
syntax = "proto3";

option java_package = "com.interview.chat"; // Match your package
option java_multiple_files = true;
option java_outer_classname = "InterviewChatOuterClass";

service InterviewChat {
rpc getResponse(stream Message) returns (stream Response);
}

message Message {
string message = 1;
}

message Response {
string responseLLM = 1;
}
```
---

## 📘 Endpoints Overview

### 🎯 WebSocket Endpoint

| Method | Path            | Description            |
|--------|------------------|------------------------|
| WS     | `/websocket`     | Initiates socket connection using JWT token |
| SEND   | `/app/chat`      | Send message to AI     |
| LISTEN | `/user/{username}/queue/chat` | Receive message from AI |

### 📘 Sample Request & Response

#### Request

```
SEND /app/chat
Payload: "Hi, I am ready for the interview."
```

#### Response

```
RECEIVED at /user/username/queue/chat
Payload: "Hello! Let's get started. Tell me about yourself."
```

---

## 🛡️ Security Summary

- JWT token is passed via WebSocket query param (`?token=...`)
- Token is validated and user session is created with `JwtHandshakeInterceptor`
- SecurityContext is manually set using `JwtHandshakeHandler` , here username is stored in principal.
- This username form principal is used to create one to one websocket connection.

