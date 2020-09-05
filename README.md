# nextwin-server.jar
[![Download](https://img.shields.io/badge/download-v1.5.2-blue)](https://github.com/nextwingames/server-framework/releases/download/v1.5.2/nextwin-server-1.5.2.jar)
[![license](https://img.shields.io/github/license/mashape/apistatus.svg)](https://github.com/nextwingames/server/blob/master/LICENSE) 

## Framework of game server
Server developer do not need to implement all of server. It provides guidelines what programmers should implement. This works on JAVA Maven project and there are 5 core packages.
1. For executing server
2. For implementing thread work
3. For networking
4. For implementing services
5. Other utilities

## Add dependency
Open your pom.xml and add dependency.
```xml
<dependency>
  <groupId>org.nextwin</groupId>
	<artifactId>nextwin-server</artifactId>
	<version>1.5.2</version>
</dependency>
```
Then [![Download](https://img.shields.io/badge/download-v1.5.2-blue)](https://github.com/nextwingames/server-framework/releases/download/v1.5.2/nextwin-server-1.5.2.jar) nextwin-server.jar to ${project_path}/target/lib/. We are going to register this library for maven repository soon. At that time, it will make able to download the library just by adding the dependency to pom.xml.

## Usage
### Execute server
To execute your server, you have to make classes which extend Server.class in org.nextwin.server. So it would need to import org.nextwin.server. And then you can see the class need to be implemented 3 methods. After implementing these following methods, you can execute your server by calling go() method.
#### ServerThread createServerThread(Socket socket)
Because this method creates appropriate server thread and return it, you have to make classes which extend ServerThread in org.nextwin.thread. 
The related explanation will be given at implementing thread work.
#### void onTerminate()
It is called when the server is terminated.
#### int getPort()
It returns the port number on which the server will run.
### Implement what severs do
Executing server, you created thread so the threads need to be implemented what they do. Like executing server, you have to make classes which extend ServerThread in org.nextwin.thread.
#### void service(int msgType, byte[] data)
Before Server threads call this method and pass 2 arguments, they parse header and serialize the received data. This executes services along message type which is one of the arguments. So you should create appropriate service object that extends Service.class in org.nextwin.service and call execute method of it.
### Network
org.nextwin.service.Service and org.nextwin.thread.ServerThread have networkManager in field. It helps send and receive packet data easily. If you want to use other class you made, import org.nextwin.net and declare networkManager. You don't need to declare it in the classes which extend ServerThread.class or Service.class. You can use networkManager like following script.
```java
import org.nextwin.net.NetworkManager;

// declare and create instance
NetworkManager networkManager;
networkManager = NetworkManager.getInstance();    // get singletone instance

// receive data (you don't need to use this because ServerThread does this)
Header header = networkManager.receive();
byte[] data = networkManager.receive(header.getLength());

// send data (ex. player information)
networkManager.send(Protocols.PLAYER_INFORMATION, packetPlayerInformation);    // Protocols.PLAYER_INFORMATION is an integer value, packetPlayerInformation is an ex object.
```
### Implement services
In service() method which is in ServerThread.class, service object should be created along the message type of header which is received from client. You can make classes extend Service.class in org.nextwin.service and use them by overriding execute() method.
#### void execute()
You can implement your own service excution.
### Other utilities
#### JsonManager
It is assumed here that the client is implemented in the Unity engine. Therefore, it is essential to convert the data to Json format when passing it. JsonManager helps you do this through simple function calls. Before use it, adding another dependency is needed.
Open your pom.xml and add following dependency.
```xml
<dependencies>
  <dependency>
	  <groupId>com.fasterxml.jackson.core</groupId>
	  <artifactId>jackson-databind</artifactId>
    <version>2.8.8</version>
  </dependency>
</dependencies>  
```
Then you can use JsonManager.
```java
import org.nextwin.util.JsonManager;

// serialize
byte[] data = JsonManager.objectToBytes(object);
// deserialize
MyObject object = (MyObject)JsonManager.bytesToObject(data, MyObject.class);
```
#### BitConverter
If you want serialize or deserialize something primitive, use BitConverter.
```java
import org.nextwin.util.BitConverter;

// serialize
byte[] data = BitConverter.intToBytes(10);
// deserialize
int val = BitConverter.bytesToInt(data);
```
#### Logger
You can debug easily with logger.
```java
import org.nextwin.util.Logger;

Logger.log(TAG, "log message");
```
