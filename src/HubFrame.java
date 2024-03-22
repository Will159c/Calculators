//puclic class that houses the main opperations

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeListener;
import java.util.*;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class HubFrame {
    public static void HUB(){
        JFrame frame = new JFrame();
        JPanel panel = new JPanel(new GridLayout(2, 2));
        frame.setSize(600, 400);
        frame.add(panel);

        JButton traceButton = new JButton("trace(A)");
        JButton determinateButton = new JButton("det(A)");
        JButton addButton = new JButton("display");
        JButton multiplyButton = new JButton("mutiply");

        frame.add(traceButton, BorderLayout.WEST);
        frame.add(determinateButton, BorderLayout.NORTH);
        frame.add(addButton, BorderLayout.SOUTH);
        frame.add(multiplyButton, BorderLayout.EAST);



//end
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
