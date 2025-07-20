package Proxy;

import ActorProperties.Message;

public class AddInsultMessage extends Message {
    public AddInsultMessage(String from, String body){
        super(from, body);
    }
}
