package Decorator;

import ActorProperties.ActorContext;
import ActorProperties.IActor;
import ActorProperties.Message;

public class FirewallDecorator extends ActorDecorator {

    public FirewallDecorator(IActor actor){
        super(actor);
    }

    /**
     * Method for processing a message and discard it if the sender isn't located at the ActorContext
     * @param message Message to be treated
     */
    @Override
    public void process(Message message) throws InterruptedException {
        if(ActorContext.lookup(message.getFrom()) != null) {
            super.process(message);
        }
        else{
            System.out.println("The actor "+message.getFrom()+" is not at the ActorContext");
        }
    }
}
