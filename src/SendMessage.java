import ActorProperties.ActorContext;
import ActorProperties.Message;
import Proxy.*;

import javax.swing.*;

public class SendMessage extends JFrame{
    private JButton messageButton, allInsultMessageButton, addInsultMessageButton, insultMessageButton, quitMessageButton;
    private JPanel panel;

    public SendMessage() {
    setContentPane(panel);
    setSize(600,600);
    setTitle("Send Messages");
    setVisible(true);

    messageButton.addActionListener(e -> {
        String receptor = JOptionPane.showInputDialog("Introduce the name of the actor that receives the message: ");
        String remitent = JOptionPane.showInputDialog("Introduce the name of the actor that sends the message: ");
        String missatgeBody = JOptionPane.showInputDialog("Introduce the message to be sent: ");
        ActorProxy actorProxy = ActorContext.getProxyMap().get(receptor);
        actorProxy.sendTo(new Message(remitent,missatgeBody));
    });

    quitMessageButton.addActionListener(e -> {
        String receptor = JOptionPane.showInputDialog("Introduce the name of the actor that receives the message: ");
        String remitent = JOptionPane.showInputDialog("Introduce the name of the actor that sends the message: ");
        String missatgeBody = JOptionPane.showInputDialog("Introduce the message to be sent: ");
        ActorProxy actorProxy = ActorContext.getProxyMap().get(receptor);
        actorProxy.sendTo(new QuitMessage(remitent,missatgeBody));
    });

    insultMessageButton.addActionListener(e -> {
        String receptor = JOptionPane.showInputDialog("Introduce the name of the actor that receives the message: ");
        String remitent = JOptionPane.showInputDialog("Introduce the name of the actor that sends the message: ");
        String missatgeBody = JOptionPane.showInputDialog("Introduce the message to be sent: ");
        ActorProxy actorProxy = ActorContext.getProxyMap().get(receptor);
        actorProxy.sendTo(new InsultMessage(remitent,missatgeBody));
        try {
            Thread.sleep(750);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
        Message message = actorProxy.receive();
        System.out.println(message.toString());
    });

    addInsultMessageButton.addActionListener(e -> {
        String receptor = JOptionPane.showInputDialog("Introduce the name of the actor that receives the message: ");
        String remitent = JOptionPane.showInputDialog("Introduce the name of the actor that sends the message: ");
        String missatgeBody = JOptionPane.showInputDialog("Introduce the insult: ");
        ActorProxy actorProxy = ActorContext.getProxyMap().get(receptor);
        actorProxy.sendTo(new AddInsultMessage(remitent,missatgeBody));
        try {
            Thread.sleep(750);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
        Message message = actorProxy.receive();
        System.out.println(message.toString());
    });

    allInsultMessageButton.addActionListener(e -> {
        String receptor = JOptionPane.showInputDialog("Introduce the name of the actor that receives the message: ");
        String remitent = JOptionPane.showInputDialog("Introduce the name of the actor that sends the message: ");
        String missatgeBody = JOptionPane.showInputDialog("Introduce the message to be sent: ");
        ActorProxy actorProxy = ActorContext.getProxyMap().get(receptor);
        actorProxy.sendTo(new AllInsultMessage(remitent,missatgeBody));
        try {
            Thread.sleep(750);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
        Message message = actorProxy.receive();
        System.out.println(message.toString());
    });
}
}
