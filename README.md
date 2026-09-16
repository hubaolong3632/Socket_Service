# Socket_Service

Java 原生 Socket 即时通讯服务端，支持多用户登录、线程化会话管理与消息转发。

## 项目简介

本项目实现了一个基于 TCP Socket 的简易聊天服务器，客户端连接后先进行用户名/密码认证，认证成功后为每个用户分配独立线程处理消息。内置测试账号（1/1、2/2、3/3），适用于 Java 网络编程学习与小规模内网通讯实验。

## 技术栈

| 类别 | 技术 |
|------|------|
| 语言 | Java 11 |
| 网络 | Java Socket / ServerSocket、ObjectInputStream / ObjectOutputStream |
| 序列化 | Java 对象序列化（USerPasword、Message） |
| 工具 | Fastjson |
| 构建 | Maven |

## 主要功能

- **TCP 监听**：ServerSocket 绑定端口 `9999`
- **用户认证**：连接后首个对象为用户凭证，校验通过后返回登录成功/失败消息
- **多线程会话**：每个在线用户对应一个 `SocketThreadService` 线程
- **线程池管理**（`ManagementClientThreadMAp`）：维护在线用户与线程映射
- **消息类型**（`MessageType`）：登录成功、登录失败、普通消息等枚举
- **客户端界面**（`View/`）：Swing 或控制台客户端视图

## 项目结构

```
Socket_Service/
├── pom.xml
└── src/main/java/com/
    ├── Model/                          # 消息、用户、线程映射模型
    │   ├── Message.java
    │   ├── USerPasword.java
    │   └── ManagementClientThreadMAp.java
    └── 服务器/
        ├── server/
        │   ├── SocketService.java      # 主服务：监听 9999 端口
        │   └── SocketThreadService.java # 单用户消息处理线程
        ├── IFwqService/MessageType.java
        ├── View/                       # 客户端 UI
        └── util/                       # 工具类
```

## 快速开始

### 环境要求

- JDK 11+
- Maven 3.6+

### 启动服务端

```bash
# 在 IDE 中运行 SocketService 构造函数
# 或通过 Maven 编译后运行
mvn compile
java -cp target/classes com.服务器.server.SocketService
```

服务启动后监听 **9999** 端口。

### 测试账号

| 用户名 | 密码 |
|--------|------|
| 1 | 1 |
| 2 | 2 |
| 3 | 3 |

### 客户端连接

运行 `View/` 目录下客户端程序，输入用户名密码连接 `localhost:9999`。
