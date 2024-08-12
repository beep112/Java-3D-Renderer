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
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;
/**
 * 
 */
public class Viewer {
    
    private static class Vertex {
        private double x;
        private double y;
        private double z;
        
        
        public Vertex(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
    
    private static class Triangle {
        Vertex x1;
        Vertex x2;
        Vertex x3;
        Color color;
        
        public Triangle(Vertex x1, Vertex x2, Vertex x3, Color color) {
            this.x1 = x1;
            this.x2 = x2;
            this.x3 = x3;
            this.color = color;
        }
    }
    
    private static class Matrix3 {
        double[] values;
        
        public Matrix3(double[] values) {
            this.values = values;
        }
        
        public Matrix3 multiply(Matrix3 other) {
            double[] result = new double[9];
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    for (int i = 0; i < 3; i++) {
                        result[row * 3 + col] += this.values[row * 3 + i] * other.values[i * 3 + col];
                    }
                }
            }
            
            return new Matrix3(result);
        }
        
        public Vertex transform(Vertex in) {
            return new Vertex(
                    in.x * values[0] + in.y * values[3] + in.z * values[6],
                    in.x * values[1] + in.y * values[4] + in.z * values[7], 
                    in.x * values[2] + in.y * values[5] + in.z * values[8]
            );
                    
        }
    }
    
    
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
                
                // creating the points and color for triangle
                List<Triangle> tris = new ArrayList<>();
                tris.add(new Triangle(new Vertex(100, 100, 100),
                        new Vertex(-100, -100, 100),
                        new Vertex(-100, 100, -100),
                        Color.WHITE));
                tris.add(new Triangle(new Vertex(100, 100, 100),
                        new Vertex(-100, -100, 100),
                        new Vertex(100, -100, -100),
                        Color.RED));
                tris.add(new Triangle(new Vertex(-100, 100, -100),
                        new Vertex(100, -100, -100),
                        new Vertex(100, 100, 100),
                        Color.GREEN));
                tris.add(new Triangle(new Vertex(-100, 100, -100),
                        new Vertex(100, -100, -100),
                        new Vertex(-100, -100, 100),
                        Color.BLUE));
                
                // putting the triangle on the screen 
                double heading = Math.toRadians(headingSlider.getValue());
                // left right transformation matrix
                Matrix3 transform = new Matrix3(new double[] {
                        Math.cos(heading), 0, -Math.sin(heading),
                        0, 1, 0,
                        Math.sin(heading), 0, Math.cos(heading)
                });
                
                // up down rotation matrix
                Matrix3 headingTransform = new Matrix3(new double[] {
                        Math.cos(heading), 0, Math.sin(heading),
                        0, 1, 0,
                        -Math.sin(heading), 0, Math.cos(heading)
                });
                double pitch = Math.toRadians(pitchSlider.getValue());
                Matrix3 pitchTransform = new Matrix3(new double[] {
                        1, 0, 0,
                        0, Math.cos(pitch), Math.sin(pitch),
                        0, -Math.sin(pitch), Math.cos(pitch)
                });
                transform = headingTransform.multiply(pitchTransform);
                
                g2.translate(getWidth() / 2, getHeight() / 2);
                g2.setColor(Color.WHITE);
                
                for (Triangle t : tris) {
                    Vertex x1 = transform.transform(t.x1);
                    Vertex x2 = transform.transform(t.x2);
                    Vertex x3 = transform.transform(t.x3);
                    Path2D path = new Path2D.Double();
                    path.moveTo(x1.x, x1.y);
                    path.lineTo(x2.x, x2.y);
                    path.lineTo(x3.x, x3.y);
                    path.closePath();
                    g2.draw(path);
                }
                
            }
        };
        pane.add(renderPanel, BorderLayout.CENTER);
        
        frame.setSize(400, 400);
        frame.setVisible(true);
        headingSlider.addChangeListener(e -> renderPanel.repaint());
        pitchSlider.addChangeListener(e -> renderPanel.repaint());
        
    }
}
