package Proxy;

import ActorProperties.Message;

public class QuitMessage extends Message {
    public QuitMessage(String from, String body){
        super(from, body);
    }
}
