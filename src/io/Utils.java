
package io;

import java.io.File;
import javax.swing.ImageIcon;

/* Utils.java is used by FileChooserDemo2.java. */
public class Utils {
    public final static String ply = "ply";
    public final static String obj = "obj";
    public final static String mesh = "mesh.xml";
    public final static String j3o = "j3o";
    
    /*
     * Get the extension of a file.
     */
    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
      
        if(s.endsWith(".obj"))
        	ext = "obj";
        else if(s.endsWith(".ply"))
        	ext = "ply";
        else if(s.endsWith(".mesh.xml"))
        	ext = ".mesh.xml";
        else if(s.endsWith(".j3o"))
        	ext = ".j3o";
     /*   
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }*/
        return ext;
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = Utils.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}
