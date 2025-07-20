package Observer;

import ActorProperties.Message;

public class ActorStateObserver implements Observer {

    /**
     * Method that updates the state of an actor in the Hashmap
     * @param nameActorReceive
     * @param size
     * @param message
     * @param x
     */
    @Override
    public void update(String nameActorReceive, int size, Message message, boolean x) {
        MonitorService.getActiveActor().put(nameActorReceive, x);
    }
}
