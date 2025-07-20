package ActorProperties;

import Proxy.ActorProxy;
import Proxy.QuitMessage;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class RingActor extends Actor {
    private int aux, count;
    private ArrayList list = new ArrayList<ActorProxy>(), listNameActors;
    private ActorProxy nextActorProxy, startProxy;
    private BlockingQueue<ActorProxy> actorProxyArrayList = new LinkedBlockingQueue<>();
    private int actualIndex = 0;
    private long start;

    public RingActor(int circle) throws InterruptedException { aux = circle;}

    public void setActorProxy(ActorProxy actorProxy) {
        actorProxyArrayList.add(actorProxy);
    }

    @Override
    public void process(Message message) throws InterruptedException {
        if(message.getClass() == QuitMessage.class){
            super.process(message);
        }

        if(count < aux){
            super.process(message);
            listNameActors = ActorContext.getNames();
            for(Object i : listNameActors){
                IActor actor = ActorContext.lookup(i);
                list.add(actor.getActorProxy().peek());
            }
            startProxy = (ActorProxy) list.get(list.indexOf(actorProxyArrayList.peek()));
            messageActorToActor(message);
            while (!actorProxyArrayList.peek().equals(startProxy)) {
                messageActorToActor(message);
            }
            count++;
            System.out.println("Realized "+ count +" loops");
            if(count == aux){
                long end = System.currentTimeMillis();
                System.out.println("Seconds that have passed = "+(end- start)/1000);
                for(Object object : list){
                    ((ActorProxy) object).sendTo(new QuitMessage("null", "Bye"));
                }
            }
        }
    }

    /**
     * Method that enables an actor sending a message to the following actor via Proxy
     * @param message Message to be sent
     */
    public void messageActorToActor(Message message) {
        actualIndex = list.indexOf(actorProxyArrayList.peek());
        if (actualIndex == list.size() - 1) {
            nextActorProxy = (ActorProxy) list.get(0);
        } else {
            nextActorProxy = (ActorProxy) list.get(list.indexOf(actorProxyArrayList.peek()) + 1);
        }
        message.setFrom((String) listNameActors.get(actualIndex));
        nextActorProxy.sendTo(message);
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        BlockingQueue<ActorProxy> newQueue = new LinkedBlockingQueue<>();
        newQueue.add(nextActorProxy);
        newQueue.addAll(actorProxyArrayList);
        actorProxyArrayList = newQueue;
    }

    public BlockingQueue<ActorProxy> getActorProxy() {
        return actorProxyArrayList;
    }

    public void setStart(long start) {
        this.start = start;
    }
}
