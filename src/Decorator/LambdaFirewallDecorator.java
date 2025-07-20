package Decorator;

import ActorProperties.IActor;
import ActorProperties.Message;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Predicate;

public class LambdaFirewallDecorator extends ActorDecorator {
    private IActor actor;
    private Queue<Message> queue = new LinkedList<>();

    public LambdaFirewallDecorator(IActor actor) {
        super(actor);
        this.actor = actor;
    }

    Predicate<String> greater_or_equal = x -> x.length() >= 2;

    /**
     * From predicate x -> {return x.length() >= 2} we will discard all messages that don't fulfill the condition
     * @param message Message to be treated
     */
    @Override
    public void send(Message message) {
        if(greater_or_equal.test(message.getBody())){
            super.send(message);
        }
        else{
            System.out.println("Error on the message sent by "+message.getFrom()+ " it has less than 2 characters.");
        }
    }

}
