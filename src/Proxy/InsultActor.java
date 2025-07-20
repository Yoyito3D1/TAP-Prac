package Proxy;

import ActorProperties.Actor;
import ActorProperties.Message;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class InsultActor extends Actor {
    private Message actualMessage;
    private ActorProxy actorProxy;
    private Queue<Message> queue = new LinkedList<>();

    ArrayList<String> arrayInsults = new ArrayList<>(){{
        add("Dickhead");
        add("Stupid");
        add("Fathead");
    }};

    /**
     * Put message on the queue
     * @param message Message that we want to introduce
     */
    public void send(Message message) {
        queue.add(message);
    }

    /**
     * Method for obtaining a random insult
     * @return String, an Insult
     */
    public String getInsultMessage(){
        int random = (int) (Math.random()*arrayInsults.size());
        return arrayInsults.get(random);
    }

    /**
     * Method for adding a string to the list of insults
     * @param insult String insult to add
     */
    public void addInsultMessage(String insult){
        arrayInsults.add(insult);
    }

    /**
     * Method for getting all insults
     * @return String with all insults
     */
    public String getAllInsultsMessage(){
        return arrayInsults.toString();
    }

    /**
     * Method for processing a message on the queue
     * @param message message to be treated
     */
    public void process(Message message) {
        switch (message){
            case QuitMessage message1 -> {
                System.out.println(message.getBody());
                currentThread().stop();
            }
            // Generating random insult
            case InsultMessage message1 -> {
                actualMessage = new Message(message.getFrom(),getInsultMessage());
                actorProxy.queueProxy.add(actualMessage);
               }
            // Adding body to the list
            case AddInsultMessage message1 -> {
                addInsultMessage(message.getBody());
                actualMessage=new Message(message.getFrom(),"Insult added: "+message.getBody());
                actorProxy.queueProxy.add(actualMessage);
            }
            // Generating String in order to return list
            case AllInsultMessage message1 -> {
                actualMessage=new Message(message.getFrom(),getAllInsultsMessage());
                actorProxy.queueProxy.add(actualMessage);
            }
            // Print message
            case Message message1 -> System.out.println("Sent by: "+message.getFrom() + " message: "+message.getBody() );
        }

    }

    @Override
    public void run() {
        while(true){
            if(queue.size() > 0){
                process(queue.poll());
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

    /**
     * For knowing which actor proxy sent the message
     * @param actorProxy used ActorProxy
     */
    public void setActorProxy(ActorProxy actorProxy) {
        this.actorProxy = actorProxy;
    }

    @Override
    public Queue<Message> getQueue() {
        return queue;
    }
}
