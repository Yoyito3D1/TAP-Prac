package Observer;

import ActorProperties.Message;

public class ActorReceiveMessageObserver implements Observer {

    /**
     * Method to update the Hashmaps of the queue of messages of every actor
     * @param nameActorReceive
     * @param size
     * @param message
     * @param x
     */
    @Override
    public void update(String nameActorReceive, int size, Message message, boolean x) {
        MonitorService.getActors().get(nameActorReceive).add(new Message(message.getFrom(), message.getBody()));
    }
}
