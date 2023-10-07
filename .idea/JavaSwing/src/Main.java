import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        JFrame frame = new JFrame();
        JLabel label = new JLabel("Yo");
        label.setText("Yo come'ere");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("What the fuck");
        frame.setSize(1000,1000);
        frame.setVisible(true);
        frame.add(label);
    }
}
