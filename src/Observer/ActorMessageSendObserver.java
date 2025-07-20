package Observer;

import ActorProperties.Message;

public class ActorMessageSendObserver implements Observer {

    /**
     * Method that updates the HashMap where all the messages sent by each actor are saved
     * @param nameActorReceive
     * @param size
     * @param message
     * @param x
     */
    @Override
    public void update(String nameActorReceive, int size, Message message, boolean x) {
        MonitorService.getSentMessagesForActor().get((String) message.getFrom()).add(new Message(message.getFrom(),message.getBody()));
    }
}
