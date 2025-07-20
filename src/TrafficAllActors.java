import Observer.MonitorService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TrafficAllActors extends JFrame{
    private JPanel allActors;
    private JTextArea textArea1;

    private Thread thread = new Thread(this::run);

    public TrafficAllActors(){
        setContentPane(allActors);
        setSize(600, 600);
        setTitle("Messages Sent By All Actors");
        setVisible(true);
        textArea1.setFont(new Font("Calibri", Font.BOLD, 20));
        textArea1.setForeground(Color.white);
        textArea1.setText(String.valueOf(MonitorService.getAllTraffics()));
        thread.start();
        close();
    }

    public void run() {
        while (true){
            textArea1.setText(String.valueOf(MonitorService.getAllTraffics()));
            allActors.updateUI();
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
