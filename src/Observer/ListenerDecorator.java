package Observer;

import ActorProperties.IActor;
import ActorProperties.Message;
import Proxy.QuitMessage;
import Decorator.ActorDecorator;

public class ListenerDecorator extends ActorDecorator {
    private IActor actor;

    public ListenerDecorator(IActor actor) {
        super(actor);
        this.actor = actor;
    }

    @Override
    public void process(Message message) throws InterruptedException {
        MonitorService.notifyAllObservers(this.getActorName(),this.getQueue().size(),message, true);

        if(message.getClass() == QuitMessage.class){
            MonitorService.notifyAllObservers(this.getActorName(),this.getQueue().size(),message, false);
        }
        super.process(message);
    }

}
