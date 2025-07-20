import Observer.MonitorService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class ActivityAllActors extends JFrame{
    private JPanel allActors;
    private JTextArea textArea1;

    private Thread thread = new Thread(this::run);

    public ActivityAllActors(){
        setContentPane(allActors);
        setSize(600, 600);
        setTitle("Messages Received By All Actors");
        setVisible(true);
        textArea1.setFont(new Font("Calibri", Font.BOLD, 20));
        textArea1.setForeground(Color.white);
        ArrayList aux = new ArrayList();
        MonitorService.getActiveActor().forEach((key, value) -> aux.add("Actor: "+key + " Active = "+value+"\n"));
        textArea1.setText(String.valueOf(aux));
        thread.start();
        close();
    }

    public void run() {
        while (true){
            ArrayList aux = new ArrayList();
            MonitorService.getActiveActor().forEach((key, value) -> aux.add("Actor: "+key + " Active = "+value+"\n"));
            textArea1.setText(String.valueOf(aux));
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
