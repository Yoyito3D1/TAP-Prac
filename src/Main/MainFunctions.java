package Main;

import ActorProperties.*;
import Decorator.EncryptionDecorator;
import Decorator.FirewallDecorator;
import Decorator.LambdaFirewallDecorator;
import Proxy.*;

public class MainFunctions {
    public static void main(String[] args){}

    public static void testHelloWorld() throws InterruptedException {
        System.out.println("-> Testing Hello World");

        ActorProxy hello = ActorContext.spawnActor2("Actor1", new Actor());
        hello.sendTo(new Message(null, "Hello World!"));
        hello.sendTo(new QuitMessage(null, "Bye!"));

        hello = ActorContext.spawnActor2("HelloWorld", new Actor());
        int un =1;
        for(int i=0; i<=10; i++){

            hello.sendTo(new Message("p1", ""+un*i));
            //Thread.sleep(500);            //queue empty test
        }
        hello.sendTo(new QuitMessage(null, "Bye!"));
        Thread.sleep(1000);
        System.out.println("Everything correct!");
    }

    public static void insultActor(){
        System.out.println("-> Testing InsultActor");
        ActorProxy actor1 = ActorContext.spawnActor2("Insult", new InsultActor());
        actor1.sendTo(new InsultMessage("Actor2","Insult"));
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Message result = actor1.receive();
        System.out.println(result.toString());
        actor1.sendTo(new AddInsultMessage("Actor2","Shit"));
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        result = actor1.receive();
        System.out.println(result.toString());
        actor1.sendTo(new AllInsultMessage("Actor2","Insult"));
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        result = actor1.receive();
        System.out.println(result.toString());
        actor1.sendTo(new QuitMessage("Actor2", "Bye"));
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Everything correct!");
    }

    public static void firewallDecorator() throws InterruptedException {
        System.out.println("-> Testing FireWallDecorator with the class Actor and InsultActor");
        ActorProxy actorProxy1 = ActorContext.spawnActor2("Actor1", new FirewallDecorator(new Actor()));
        ActorProxy actorProxy2 = ActorContext.spawnActor2("Actor2", new Actor());
        actorProxy1.sendTo(new Message("Actor4", "ERROR"));
        actorProxy1.sendTo(new Message("Actor2", "HelloWorld!"));
        actorProxy1.sendTo(new QuitMessage("Actor2", "BYE"));
        Thread.sleep(2000);
        ActorProxy actorProxyInsult = ActorContext.spawnActor2("InsultActor", new FirewallDecorator(new InsultActor()));
        actorProxyInsult.sendTo(new InsultMessage("Actor2","Insult"));
        Thread.sleep(1000);
        Message message = actorProxyInsult.receive();
        System.out.println(message.toString());
        actorProxyInsult.sendTo(new QuitMessage("Actor2", "ActorInsult ends"));
        actorProxy2.sendTo(new QuitMessage("null", "Quit"));
        Thread.sleep(1000);
        System.out.println("Everything correct!");
    }

    public static void lambdaFirewallDecorator() throws InterruptedException {
        System.out.println("-> Testing Firewall decorator and Lambda Firewall decorator");
        ActorProxy actorProxy1 = ActorContext.spawnActor2("Actor1", new FirewallDecorator(new LambdaFirewallDecorator(new Actor())));
        ActorProxy actorProxy2 = ActorContext.spawnActor2("Actor2", new Actor());
        actorProxy1.sendTo(new Message("Actor2","HELLO"));
        actorProxy1.sendTo(new Message("Actor2","H"));
        actorProxy1.sendTo(new Message("Actor4","H"));
        actorProxy1.sendTo(new Message("Actor4","HELLO"));
        actorProxy1.sendTo(new QuitMessage("Actor2", "QUIT \n"));
        Thread.sleep(1500);

        System.out.println("Now testing with insultActor...");
        actorProxy1 = ActorContext.spawnActor2("Actor1", new FirewallDecorator(new LambdaFirewallDecorator(new InsultActor())));
        actorProxy1.sendTo(new InsultMessage("Actor2","Insult"));
        actorProxy1.sendTo(new InsultMessage("Actor2","H"));
        actorProxy1.sendTo(new InsultMessage("Actor4","H"));
        actorProxy1.sendTo(new InsultMessage("Actor4","HELLO"));
        Thread.sleep(1000);
        Message message = actorProxy1.receive();
        System.out.println(message.toString());
        actorProxy1.sendTo(new AllInsultMessage("Actor2", "Everyone"));
        Thread.sleep(1500);
        message = actorProxy1.receive();
        System.out.println(message.toString());
        actorProxy1.sendTo(new QuitMessage("Actor2", "ActorInsult ends"));
        actorProxy2.sendTo(new QuitMessage("null", "Quit"));
        Thread.sleep(1000);
        System.out.println("Everything correct!");
    }

    public static void encryptionDecorator() throws InterruptedException {
        System.out.println("-> Testing Encryption and firewall");
        ActorProxy actorProxy1 = ActorContext.spawnActor2("Actor1", new EncryptionDecorator(new FirewallDecorator(new Actor())));
        ActorProxy actorProxy2 = ActorContext.spawnActor2("Actor2",new Actor());
        actorProxy1.sendTo(new Message("Actor2", "Hello Moon!"));
        actorProxy1.sendTo(new Message("Actor3", "!"));
        actorProxy1.sendTo(new QuitMessage("Actor2", "Quit"));
        Thread.sleep(1500);
        System.out.println("Everything correct!");

        System.out.println("-> Testing the three decorators");
        actorProxy1 = ActorContext.spawnActor2("Actor", new EncryptionDecorator(new LambdaFirewallDecorator(new FirewallDecorator(new InsultActor()))));
        actorProxy1.sendTo(new InsultMessage("Actor2", "Insult"));
        actorProxy1.sendTo(new InsultMessage("Actor3", "Insult"));
        actorProxy1.sendTo(new InsultMessage("Actor2", ""));
        Thread.sleep(500);
        Message message = actorProxy1.receive();
        System.out.println(message.toString());
        actorProxy1.sendTo(new QuitMessage("Actor2","Quit"));
        actorProxy2.sendTo(new QuitMessage("Actor3","Quit"));
        Thread.sleep(700);
        System.out.println("Everything correct!");
    }


    public static void ringActor() throws InterruptedException {
        System.out.println("-> Testing RING ACTOR");
        ActorProxy ring = ActorContext.spawnActor2("Ring", new RingActor(100));

        int i=0;
        while (i<99){
            ActorContext.spawnActor2("Actor"+i, new Actor());
            i++;
        }
        long inicio = System.currentTimeMillis();
        RingActor aux = (RingActor) ActorContext.lookup("Ring");
        aux.setStart(inicio);
        ring.sendTo(new Message("null", "Renville"));
    }

    public static void pimPom(int numMissatges) throws InterruptedException {
        System.out.println("-> Testing PIM POM, we will be doing " + numMissatges + " messages");

        ActorProxy a1 = ActorContext.spawnActor2("p1", new PimPom(numMissatges));
        ActorProxy a2 = ActorContext.spawnActor2("p2", new PimPom(numMissatges));
        a1.sendTo(new Message("p2", "0"));
    }

    public static void dynamicProxy() throws InterruptedException {
        System.out.println("-> Testing DYNAMIC PROXY");
        ActorProxy actor = ActorContext.spawnActor("actor",new InsultActor());
        IService insulter = (IService) DynamicProxy.newInstance(new InsultService(), actor);
        insulter.addInsult(null, "Bullshit");
        System.out.println(insulter.getInsult(null));
        System.out.println(insulter.getAllInsults(null));

        Thread.sleep(1000);
        Message message = actor.receive();
        System.out.println("Getting a random insult: " + insulter.getInsult(null));
        System.out.println("Getting a list of all insults: " + insulter.getAllInsults(null));
        System.out.println("The insult received is: " + message.toString());
        actor.sendTo(new QuitMessage(null, "Bye"));
        System.out.println("Everything correct!");
    }

    public static void allTest() throws InterruptedException {
        testHelloWorld();
        System.out.println("\n \n");
        insultActor();
        System.out.println("\n \n");
        firewallDecorator();
        System.out.println("\n \n");
        lambdaFirewallDecorator();
        System.out.println("\n \n");
        encryptionDecorator();
        System.out.println("\n \n");
        dynamicProxy();
    }
}

