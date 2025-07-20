package Observer;

import ActorProperties.Message;

import java.util.*;

public class MonitorService {
    // -----------------------Singleton--------------------

    private static MonitorService onlyInstance = null;

    public static MonitorService instance(){
        if(onlyInstance == null){
            onlyInstance = new MonitorService();
        }
        return onlyInstance;
    }

    protected MonitorService(){}

    //------------------------Maps-------------------------------------------

    private static List<Observer> observers = new ArrayList<>(Arrays.asList(new ActorMessageSendObserver(), new ActorStateObserver(), new ActorQueueSizeObserver(), new ActorReceiveMessageObserver()));
    private static Map<String,ArrayList<Message>> actors = new HashMap<>();                     // Queue of messages pf every actor
    private static Map<String, Integer> actorQueueSize = new HashMap<>();                       // Length of the queue of actors
    private static Map<String,Boolean> activeActor = new HashMap<>();                           // Used to know if an actor is active
    private static Map<String,ArrayList<Message>> sentMessagesForActor = new HashMap<>();       // For having registered every message sent by every actor

    //-------------------Update for the tables--------------------------
    
    public static void notifyAllObservers(String nameActorReceive, int size, Message message, boolean x){
        for(Observer observer : observers){
            observer.update(nameActorReceive, size, message, x);
        }
    }

    //-----------------------------Messages Actor-------------------------------------------

    /**
     * Method that returns an arraylist with the messages sent from an actor
     * @param actorName String name of the actor
     * @return  ArrayList<Message> with the messages that the actor has sent
     */
    public static ArrayList messagesActor(String actorName) {
        ArrayList aux = new ArrayList();
        sentMessagesForActor.get(actorName).forEach(message -> aux.add(extractMessage(message)+"\n"));
        return aux;
    }

    /**
     * Method that returns an ArrayList<Message> with every message sent by every actor
     * @return ArrayList<Message> with all the messages sent by every actor
     */
    public static ArrayList messagesAllActors() {
        ArrayList aux = new ArrayList();
        sentMessagesForActor.forEach((key, value) -> sentMessagesForActor.get(key).forEach(message -> aux.add("Actor:"+key+": "+ extractMessage(message)+"\n")));
        return aux;
    }

    /**
     * Method that returns an arraylist with the messages received of an actor
     * @param actorName String name of the actor
     * @return ArrayList<Message> all messages
     */
    public static ArrayList messagesReceivedActor(String actorName) {
        ArrayList aux = new ArrayList();
        actors.get(actorName).forEach(message -> aux.add(extractMessage(message)+"\n"));
        return aux;
    }

    /**
     * Method that returns an ArrayList<Message> with every message received by every actor
     * @return ArrayList<Message> containing all received messages
     */
    public static ArrayList messagesReceivedAllActors() {
        ArrayList aux = new ArrayList();
        actors.forEach((key, value) -> actors.get(key).forEach(message -> aux.add("Actor:"+key+": "+ extractMessage(message)+"\n")));
        return aux;
    }

    //-----------------------------Length Queues--------------------------------------------

    public static String getTrafficActor(String name) {
        ArrayList messagesByActor = actors.get(name);
        String queueSize = assignLength(messagesByActor.size());
        return queueSize;
    }

    public static HashMap<String,ArrayList<String>> getAllTraffics(){
        HashMap<String,ArrayList<String>> aux = new HashMap<>();
        aux.put("LOW", new ArrayList<>());
        aux.put("MEDIUM", new ArrayList<>());
        aux.put("HIGH", new ArrayList<>());

        actors.forEach((key, value) -> aux.get(getTrafficActor(key)).add(key));
        return aux;
    }

    //--------------------Get HashMap-----------------------------

    public static Map<String, ArrayList<Message>> getActors() {
        return actors;
    }
    public static Map<String, Integer> getActorQueueSize(){
        return actorQueueSize;
    }
    public static Map<String, Boolean> getActiveActor() {
        return activeActor;
    }
    public static Map<String, ArrayList<Message>> getSentMessagesForActor() {
        return sentMessagesForActor;
    }

    //---------------------------Others-----------------------------

    /**
     * Method to classify the number of messages
     * @param aux int number of messages
     * @return LOW -> aux < 5 , MEDIUM -> 5 <= aux < 15 , HIGH -> aux >= 15
     */
    public static String assignLength(int aux){
        if(aux< 5){
            return "LOW";
        }
        else if((aux >= 5) && (aux<15) ){
            return  "MEDIUM";
        }
        else{
            return "HIGH";
        }
    }

    /**
     * Method for extracting messages
     * @param message Message that we want to see
     * @return  String of the message.getFrom() + message.getBody()
     */
    public static String extractMessage(Message message){ return ("Sent by: " + message.getFrom() + " Message: " + message.getBody()); }
}
