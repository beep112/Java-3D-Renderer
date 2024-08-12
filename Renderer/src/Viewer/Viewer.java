/**
 * Name: Jason Perndreca\
 * Directory ID: jperndre
 * UID: 120089282
 * Discussion Section: 0302
 * I pledge on my honor that I have not given or received any 
 * unauthoried assistance on this assignment.
 */
package Viewer;
import javax.swing.*;
import java.awt.*;
/**
 * 
 */
public class Viewer {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Container pane = frame.getContentPane();
        pane.setLayout(new BorderLayout());
        
        
        // control horizontal rotation
        JSlider headingSlider = new JSlider(0, 360, 180);
        pane.add(headingSlider, BorderLayout.SOUTH);
        
        // control vertical rotation
        JSlider pitchSlider = new JSlider(SwingConstants.VERTICAL, -90, 90, 0);
        pane.add(pitchSlider, BorderLayout.EAST);
        
        // panel to display render results
        JPanel renderPanel = new JPanel() {
            public void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setColor(Color.BLACK);
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        pane.add(renderPanel, BorderLayout.CENTER);
        
        frame.setSize(400, 400);
        frame.setVisible(true);
    }
}
