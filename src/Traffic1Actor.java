import Observer.MonitorService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Traffic1Actor extends JFrame{
    private JPanel oneActor;
    private JTextArea textArea1;
    private String name;

    private Thread thread = new Thread(this::run);

    public Traffic1Actor(String name){
        this.name=name;
        setContentPane(oneActor);
        setSize(600, 600);
        setTitle("Messages Received By An Actor");
        setVisible(true);
        textArea1.setFont(new Font("Calibri", Font.BOLD, 20));
        textArea1.setForeground(Color.white);
        textArea1.setText(MonitorService.getTrafficActor(name));
        thread.start();
        close();
    }

    public void run(){
        while (true){
            textArea1.setText(MonitorService.getTrafficActor(name));
            oneActor.updateUI();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void close() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                thread.stop();
            }
        });
    }
}
