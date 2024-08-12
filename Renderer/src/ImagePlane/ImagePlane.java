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
public class ImagePlane {
    private static ImagePlane singleton = new ImagePlane();
    private final Vector3D topLeft = new Vector3D(1.0f, 0.75f, 0f);
    private final Vector3D topRight = new Vector3D(-1.0f, 0.75f, 0f);
    private final Vector3D bottomLeft = new Vector3D(1.0f, -0.75f, 0f);
    private final Vector3D bottomRight = new Vector3D(-1.0f, -0.75f, 0f);
    
    private ImagePlane() {
    }
    
    public ImagePlane getInstance() {
        return singleton;
    }
}
