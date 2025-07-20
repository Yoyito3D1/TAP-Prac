import ActorProperties.Actor;
import ActorProperties.ActorContext;
import ActorProperties.RingActor;
import Main.MainFunctions;
import Proxy.IService;
import Proxy.InsultActor;

import javax.swing.*;
import java.util.HashMap;

public class CreateNewActor extends JFrame {
    private JPanel panel;
    private JButton insultActorButton, actorButton, ringActorButton, pimPomButton;
    private String name;
    private HashMap<String, IService> hashMap;

    public CreateNewActor(HashMap<String, IService> inici){
        hashMap=inici;
        setContentPane(panel);
        setSize(600,600);
        setTitle("Decorator");
        setVisible(true);

        insultActorButton.addActionListener(e -> {
            name = JOptionPane.showInputDialog("Introduce the name of the actor insult");
            if(name!= null) {
                ActorContext.spawnActor(name, new InsultActor());
                JOptionPane.showMessageDialog(null,"Created Successfully -> "+name);
            }
        });

        actorButton.addActionListener(e -> {
            name = JOptionPane.showInputDialog("Introduce the name of the actor");
            if(name != null) {
                ActorContext.spawnActor(name, new Actor());
                JOptionPane.showMessageDialog(null, "Created Successfully -> " + name);
            }
        });

        ringActorButton.addActionListener(e -> {
            name = JOptionPane.showInputDialog("Introduce the name of the RingActor");
            int numero = Integer.parseInt(JOptionPane.showInputDialog("Introduce the number of turns that has to do"));
            try {
                ActorContext.spawnActor(name, new RingActor(numero));
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            JOptionPane.showMessageDialog(null,"Created Successfully -> "+name);
        });

        pimPomButton.addActionListener(e -> {
            int nombre = Integer.parseInt(JOptionPane.showInputDialog("Introduce the number of missatges for the Ping Pong"));
            try {
                MainFunctions.pimPom(nombre);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}
