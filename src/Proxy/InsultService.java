package Proxy;

public class InsultService implements IService {

    /**
     * Method for obtaining a random insult
     * @param actorProxy actor Proxy
     * @return String, an Insult
     */
    @Override
    public String getInsult(ActorProxy actorProxy) {
        actorProxy.sendTo(new InsultMessage(actorProxy.getActor().getActorName(), ""));
        return actorProxy.receive().getBody();
    }

    /**
     * Method for adding a string to the list of insults
     * @param actorProxy actor Proxy
     * @param insult String insult to add
     */
    @Override
    public void addInsult(ActorProxy actorProxy, String insult) {
        actorProxy.sendTo(new AddInsultMessage(actorProxy.getActor().getActorName(), insult));
    }

    /**
     * Method for getting all insults
     * @param actorProxy actor Proxy
     * @return String with all insults
     */
    @Override
    public String getAllInsults(ActorProxy actorProxy) {
        actorProxy.sendTo(new AllInsultMessage(actorProxy.getActor().getActorName(), ""));
        return actorProxy.receive().getBody();
    }
}
