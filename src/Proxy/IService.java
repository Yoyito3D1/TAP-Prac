package Proxy;

public interface IService {
    String getInsult(ActorProxy actorProxy);
    void addInsult(ActorProxy actorProxy, String insult);
    String getAllInsults(ActorProxy actorProxy);
}
