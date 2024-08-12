/**
 * Name: Jason Perndreca\
 * Directory ID: jperndre
 * UID: 120089282
 * Discussion Section: 0302
 * I pledge on my honor that I have not given or received any 
 * unauthoried assistance on this assignment.
 */
package ImagePlane;

import Vector.Vector3D;

/**
 * 
 */
public class Camera {
    private static Camera singleton = new Camera();
    private final Vector3D perspective = new Vector3D(-1, 0, 0);
    
    private Camera() {
        
    }
}
