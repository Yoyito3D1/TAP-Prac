# Actor System with Decorators and Proxies

Aquest projecte Java implementa un sistema d'actors amb capacitats de decoració i ús de proxies. Els actors poden enviar-se missatges, i el comportament d’aquests es pot modificar dinàmicament mitjançant patrons com **Decorator** i **Proxy**.

## 📦 Estructura del projecte

- `MainFunctions.java`: Conté funcions de test per provar els diferents actors i decoradors.
- `ActorContext`, `ActorProxy`: Implementació del sistema d’actors i les seves interfícies de comunicació.
- `Decorator`: Paquet amb decoradors com:
  - `FirewallDecorator`
  - `LambdaFirewallDecorator`
  - `EncryptionDecorator`
- `Proxy`: Implementació del patró Proxy i Dynamic Proxy (`DynamicProxy`, `IService`, `InsultService`, etc.)
- `ActorProperties`: Inclou actors específics com `InsultActor`, `RingActor`, `PimPom`, etc.

## 🚀 Tests disponibles (`MainFunctions`)

### ✅ `testHelloWorld()`
Crea un actor bàsic i li envia missatges "Hello World!" i "Bye!". Mostra com funciona la comunicació bàsica.

### 😡 `insultActor()`
Prova l’`InsultActor`, que pot retornar insults aleatoris, afegir-ne i retornar-los tots. També es veu la interacció amb missatges personalitzats.

### 🔥 `firewallDecorator()`
Aplica el `FirewallDecorator` per bloquejar missatges no autoritzats entre actors. Inclou proves amb `InsultActor`.

### ⚠️ `lambdaFirewallDecorator()`
Prova un `LambdaFirewallDecorator` combinat amb `FirewallDecorator` per a filtrar missatges segons regles lambda. Es mostra amb actors normals i d’insults.

### 🔐 `encryptionDecorator()`
Combina `EncryptionDecorator` amb els altres decoradors per encriptar missatges i afegir seguretat a la comunicació. També es prova una cadena de decoradors: Encryption + Firewall + LambdaFirewall sobre `InsultActor`.

### 🔁 `ringActor()`
Crea 100 actors i els connecta en un anell. Es mesura el temps de transmissió d’un missatge en cadena.

### 🏓 `pimPom(int numMissatges)`
Simula una conversa Pim-Pom entre dos actors, alternant missatges fins a arribar a un nombre determinat.

### 🧙 `dynamicProxy()`
Utilitza `DynamicProxy` per transformar l’actor en una interfície `IService`. Permet trucades tipus RPC (Remote Procedure Call) com `getInsult()` i `addInsult()`.

### 🧪 `allTest()`
Executa tots els tests anteriors de manera consecutiva.

## 🧠 Patrons de disseny utilitzats

- **Actor Model**: Comunicació entre entitats mitjançant missatgeria asíncrona.
- **Decorator**: Afegeix funcionalitats a actors sense modificar-ne la lògica original.
- **Proxy**: Permet representar actors remotament.
- **Dynamic Proxy**: Implementa interfícies dinàmicament per trucar mètodes sobre actors.

## 🔧 Requisits

- Java 17 o superior
- IDE com IntelliJ o Eclipse per executar els tests

## 📎 Exemple de sortida

