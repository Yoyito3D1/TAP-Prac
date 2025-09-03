# 🎭 Actor System with Decorators and Proxies  

This Java project implements an **actor system** with support for **decorators** and **proxies**.  
Actors can send messages to each other, and their behavior can be dynamically modified using design patterns like **Decorator** and **Proxy**.  

---

## 📦 Project Structure  

- **MainFunctions.java** → Test functions to try different actors and decorators.  
- **ActorContext, ActorProxy** → Core implementation of the actor system and communication interfaces.  
- **Decorator** → Package with decorators such as:  
  - `FirewallDecorator`  
  - `LambdaFirewallDecorator`  
  - `EncryptionDecorator`  
- **Proxy** → Implementation of Proxy and Dynamic Proxy patterns (`DynamicProxy`, `IService`, `InsultService`, etc.).  
- **ActorProperties** → Specific actors like `InsultActor`, `RingActor`, `PimPom`, etc.  

---

## 🚀 Available Tests (MainFunctions)  

- ✅ **testHelloWorld()** → Creates a basic actor, sends "Hello World!" and "Bye!" messages, and shows basic communication.  
- 😡 **insultActor()** → Tests the `InsultActor`, which can return random insults, add new ones, and list all insults.  
- 🔥 **firewallDecorator()** → Applies `FirewallDecorator` to block unauthorized messages between actors (tested with `InsultActor`).  
- ⚠️ **lambdaFirewallDecorator()** → Uses `LambdaFirewallDecorator` with `FirewallDecorator` to filter messages via lambda rules.  
- 🔐 **encryptionDecorator()** → Adds encryption to messages with `EncryptionDecorator`, combined with other decorators (Encryption + Firewall + LambdaFirewall).  
- 🔁 **ringActor()** → Creates 100 actors in a ring and measures message transmission performance.  
- 🏓 **pimPom(int numMessages)** → Simulates a Pim-Pom conversation between two actors until a given number of messages is reached.  
- 🧙 **dynamicProxy()** → Uses `DynamicProxy` to transform an actor into an `IService` interface, allowing RPC-like calls (`getInsult()`, `addInsult()`).  
- 🧪 **allTest()** → Runs all tests sequentially.  

---

## 🧠 Design Patterns Used  

- **Actor Model** → Asynchronous message-based communication between entities.  
- **Decorator** → Dynamically adds functionality to actors without changing their original logic.  
- **Proxy** → Represents actors remotely.  
- **Dynamic Proxy** → Dynamically implements interfaces for method calls on actors.  

---

## 🔧 Requirements  

- **Java 17** or higher  
- An IDE such as **IntelliJ** or **Eclipse** to run the tests  
