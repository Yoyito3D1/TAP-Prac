package ActorProperties;

import Proxy.ActorProxy;
import Proxy.QuitMessage;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Actor extends Thread implements IActor{
    private Queue<Message> queue = new LinkedList<>();
    private BlockingQueue<ActorProxy> actorProxyArrayList = new LinkedBlockingQueue<>();
    private String actorName;

    /**
     * Method that introduces a message on the actor Queue
     * @param message Message that we want to introduce to the actor
     */
    public void send(Message message) {
        queue.add(message);
    }

    @Override
    public void setActorProxy(ActorProxy actorProxy) {
        actorProxyArrayList.add(actorProxy);
    }

    /**
     * Method for treating the queue messages
     * @param message Message to be treated
     */
    public void process(Message message) throws InterruptedException {
        switch (message){
            case QuitMessage message1 -> {
                System.out.println(message.getBody());
                currentThread().stop();
            }
            case Message message1 -> System.out.println("Sent by: " + message.getFrom() + " Message: " + message.getBody());
        }
    }

    /**
     * Method that will run the thread
     */
    public void run() {
        while(true){
            if(queue.size() > 0){
                try {
                    process(queue.poll());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else {
                try {
                    Thread.sleep(15);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public Queue<Message> getQueue() {
        return queue;
    }

    public void setQueue(Queue<Message> queue) {
        this.queue = queue;
    }

    public BlockingQueue<ActorProxy> getActorProxy() {
        return actorProxyArrayList;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }
}

