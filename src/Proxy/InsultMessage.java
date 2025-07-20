package Proxy;

import ActorProperties.Message;

public class InsultMessage extends Message {
    public InsultMessage(String from, String body){
        super(from, body);
    }
}
