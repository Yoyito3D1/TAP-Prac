package ActorProperties;

import Proxy.QuitMessage;

import java.util.LinkedList;
import java.util.Queue;

public class PimPom extends Actor {
    private int numMessage;
    private Queue<Message> queue = new LinkedList<>();
    private String from;

    public PimPom(int numMessages){
        numMessage = numMessages;
    }

    @Override
    public void process(Message message) throws InterruptedException {
        if(message.getBody().equals("Bye")){
            System.out.println("From: "+message.getFrom()+" Message: "+message.getBody());
            currentThread().stop();
        }
        else {
            int value = Integer.parseInt(message.getBody());
            if (value < numMessage) {
                System.out.println("From: " + message.getFrom() + " Message: " + message.getBody());
                String body = String.valueOf(value + 1);
                Message messageActor = new Message(this.getActorName(), body);
                ActorContext.getProxyMap().get(message.getFrom()).sendTo(messageActor);
                from = (String) message.getFrom();
            } else {
                this.getActorProxy().take().sendTo(new QuitMessage(null, "Bye"));
                ActorContext.getProxyMap().get(from).sendTo(new QuitMessage(null, "Bye"));
            }
        }
    }

    @Override
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
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public void send(Message message) {
        queue.add(message);
    }
}
