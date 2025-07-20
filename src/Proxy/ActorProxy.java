package Proxy;

import ActorProperties.IActor;
import ActorProperties.Message;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ActorProxy {
     BlockingQueue<Message> queueProxy = new LinkedBlockingQueue<>();
     private IActor actor;

    public IActor getActor() {
        return actor;
    }

    public ActorProxy(IActor actor){ this.actor = actor; }

    /**
     * Method that puts a message to an actor via proxy
     * @param message message to be sent
     */
    public void sendTo(Message message){ actor.send(message); }

    public Message receive(){
        Message message = queueProxy.poll();
        return message;
    }
}
