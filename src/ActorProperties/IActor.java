package ActorProperties;

import Proxy.ActorProxy;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;

public interface IActor {
    void process(Message message) throws InterruptedException;

    void send(Message message);

    void setActorProxy(ActorProxy actorProxy);

    BlockingQueue<ActorProxy> getActorProxy();

    void run();

    Queue<Message> getQueue();

    void setQueue(Queue<Message> queue);

    String getActorName();

    void setActorName(String actorName);
}
