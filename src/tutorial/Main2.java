package tutorial;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.system.AppSettings;
import de.lessvoid.nifty.Nifty;

/**
 * Nifty GUI 1.3 demo using XML for static content 
 * and Java for dynamic content. 
 * @author zathras
 */
public class Main2 extends SimpleApplication {
 
  public static void main(String[] args) {
    Main2 app = new Main2();
    AppSettings settings = new AppSettings(true);
    settings.setResolution(640, 480);
    app.setShowSettings(false); // splashscreen
    app.setSettings(settings);
    app.start();
  }

  @Override
  public void simpleInitApp() {
    setDisplayFps(false);
    setDisplayStatView(false);
    
    
    /**
     * Just some simple JME content to show it's really a JME app:
     */
    Box b = new Box(Vector3f.ZERO, 1, 1, 1);
    Geometry geom = new Geometry("Box", b);
    Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
    mat.setColor("Color", ColorRGBA.Blue);
    geom.setMaterial(mat);
    rootNode.attachChild(geom);

    
    /**
     * Åctivate the Nifty-JME integration: 
     */
    NiftyJmeDisplay niftyDisplay = new NiftyJmeDisplay(
            assetManager, inputManager, audioRenderer, guiViewPort);
    Nifty nifty = niftyDisplay.getNifty();
    guiViewPort.addProcessor(niftyDisplay);
    flyCam.setDragToRotate(true); // you need the mouse for clicking now    
    //nifty.setDebugOptionPanelColors(true);
    nifty.fromXml("Interface/tutorial/screen2.xml", "start");
    //nifty.fromXml("Interface/tutorial/screen2.xml", "hud");
    
  }
}
