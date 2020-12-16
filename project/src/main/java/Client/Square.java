package Client;

import javax.swing.*;
import java.awt.*;

public class Square extends JPanel {
    JLabel label = new JLabel();

    public Square() {
        setBackground(Color.WHITE);
        setLayout(new GridBagLayout());
        label.setFont(new Font("Arial", Font.BOLD, 40));
        add(label);
    }

    public void setText(char text) {
        label.setForeground(text == 'X' ? Color.BLUE : Color.RED);
        label.setText(text+ "");
    }
}
