import javax.swing.*;

public class QueueActor extends JFrame{
    private JPanel panel;
    private JButton queueAllActorsButton, queueOneActorButton, queueTrafficButton, getAllTrafficButton, showOneActorButton, showAllActorsButton, queueActorMessageButton, queueAllActorMessageButton;

    public QueueActor() {
        setContentPane(panel);
        setSize(600, 600);
        setTitle("Queue Actors");
        setVisible(true);

        queueOneActorButton.addActionListener(e -> {
            String actor = JOptionPane.showInputDialog("Introduce the name of the actor: ");
            new MessagesSent1Actor(actor);
        });

        queueAllActorMessageButton.addActionListener(e -> {
            String actor = JOptionPane.showInputDialog("Introduce the name of the actor: ");
            new MessagesReceived1Actor(actor);
        });

        queueAllActorsButton.addActionListener(e -> new MessagesSentAllActors());

        queueActorMessageButton.addActionListener(e -> new MessagesReceivedAllActors());

        queueTrafficButton.addActionListener(e -> {
            String actor = JOptionPane.showInputDialog("Introduce the name of the actor: ");
            new Traffic1Actor(actor);
        });

        getAllTrafficButton.addActionListener(e -> new TrafficAllActors());

        showOneActorButton.addActionListener(e -> {
            String actor = JOptionPane.showInputDialog("Introduce the name of the actor: ");
            new Activity1Actor(actor);
        });

        showAllActorsButton.addActionListener(e -> new ActivityAllActors());
    }
}