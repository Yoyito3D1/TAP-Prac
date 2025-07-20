package ActorProperties;

import Observer.ListenerDecorator;
import Observer.MonitorService;
import Proxy.ActorProxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ActorContext {

    private static ActorContext onlyInstance = null;

    public static ActorContext instance(){
        if(onlyInstance == null){
            onlyInstance = new ActorContext();
        }
        return onlyInstance;
    }

    protected ActorContext(){}

    private static Map<String,IActor> hashMap = new HashMap<>();
    private static Map<String, ActorProxy> proxyMap = new HashMap<>();

    /**
     * Function that saves the actors on a library and initializes the thread for the actor
     * @param name The name of the actor will be the key of the hashmap
     * @param actor The actor that we want to save
     * @return Actor
     */
    public static ActorProxy spawnActor(String name, IActor actor){
        IActor actor1 = new ListenerDecorator(actor);
        hashMap.put(name, actor1);
        new Thread((Runnable) actor1).start();

        ActorProxy actorProxy = new ActorProxy(actor1);
        proxyMap.put(name,actorProxy);
        actor1.setActorProxy(actorProxy);
        actor1.setActorName(name);

        MonitorService.getActors().put(name,new ArrayList<>());
        MonitorService.getActorQueueSize().put(name, 0);
        MonitorService.getActiveActor().put(name,true);
        MonitorService.getSentMessagesForActor().put(name, new ArrayList<>());
        return actorProxy;
    }

    /**
     * Function that saves the actors on a library and initializes the thread for the actor
     * @param name The name of the actor will be the key of the hashmap
     * @param actor The actor that we want to save
     * @return Actor
     */
    public static ActorProxy spawnActor2(String name, IActor actor){
        hashMap.put(name, actor);
        new Thread((Runnable) actor).start();
        ActorProxy actorProxy = new ActorProxy(actor);
        proxyMap.put(name,actorProxy);
        actor.setActorProxy(actorProxy);
        actor.setActorName(name);
        return actorProxy;
    }

    /**
     * Function that returns an actor from the library
     * @param name Key from the actor we're trying to find
     * @return Returns IActor, the actor whose key was introduced
     */
    public static IActor lookup(Object name){
        return hashMap.get(name);
    }

    /**
     * Method that returns all keys/names from the actors that are inside the library
     * @return ArrayList of the names of the actors
     */
    public static ArrayList getNames(){
        ArrayList<String> list = new ArrayList<>();
        hashMap.entrySet().forEach((entry) -> list.add(entry.getKey()) );
        return list;
    }

    public static Map<String, ActorProxy> getProxyMap() {
        return proxyMap;
    }
}
