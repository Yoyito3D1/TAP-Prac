package Observer;

import ActorProperties.Message;

public class ActorQueueSizeObserver implements Observer {

    /**
     * Method that updates the length of the queue of the actors on the HashMap
     * @param nameActorReceive
     * @param size
     * @param message
     * @param x
     */
    @Override
    public void update(String nameActorReceive, int size, Message message, boolean x) {
        MonitorService.getActorQueueSize().put(nameActorReceive,size);
    }
}
