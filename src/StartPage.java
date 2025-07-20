import ActorProperties.ActorContext;
import Proxy.IService;

import javax.swing.*;
import java.util.HashMap;

public class StartPage extends JFrame{

    private JButton listNamesButton, queueActorButton, createNewActorButton, sendMessageButton;
    private JPanel panel;
    private HashMap<String, IService> hashDynamicProxy;

    public StartPage(){
        hashDynamicProxy = new HashMap<>();
        setContentPane(panel);
        setTitle("Start");
        setSize(600,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        listNamesButton.addActionListener(e -> JOptionPane.showMessageDialog(null, ActorContext.getNames()));

        queueActorButton.addActionListener(e -> new QueueActor());

        createNewActorButton.addActionListener(e -> new CreateNewActor(hashDynamicProxy));

        sendMessageButton.addActionListener(e -> new SendMessage());
    }

}
