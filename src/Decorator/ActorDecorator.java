package Decorator;

import ActorProperties.IActor;
import ActorProperties.Message;
import Proxy.ActorProxy;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;

public class ActorDecorator extends Thread implements IActor {

    private IActor actor;

    public ActorDecorator(IActor actor){
        this.actor = actor;
    }

    @Override
    public void process(Message message) throws InterruptedException {
        actor.process(message);
    }

    @Override
    public void send(Message message) {
        actor.send(message);
    }

    @Override
    public void setActorProxy(ActorProxy actorProxy) {
        actor.setActorProxy(actorProxy);
    }

    @Override
    public BlockingQueue<ActorProxy> getActorProxy() {
        return actor.getActorProxy();
    }


    @Override
    public void run() {
        while(true){
            if(actor.getQueue().size() > 0){
                try {
                    process(actor.getQueue().poll());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public Queue<Message> getQueue() {
        return actor.getQueue();
    }

    @Override
    public void setQueue(Queue<Message> queue) {
        actor.setQueue(queue);
    }

    @Override
    public String getActorName() {
        return actor.getActorName();
    }

    @Override
    public void setActorName(String actorName) {
        actor.setActorName(actorName);
    }
}
