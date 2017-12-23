package tutorial;

import com.jme3.app.SimpleApplication;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.system.AppSettings;
import de.lessvoid.nifty.Nifty;
import java.util.Calendar;

/**
 * Nifty GUI 1.3 demo using XML for static content 
 * and Java for dynamic content. 
 * @author zathras
 */
public class Main3 extends SimpleApplication {

  private int health;
  private MyStartScreen startScreen;

  public static void main(String[] args) {
    AppSettings settings = new AppSettings(true);
    settings.setResolution(640, 480);
    Main3 app = new Main3();
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

    startScreen = new MyStartScreen();
    stateManager.attach(startScreen);

    /**
     * Åctivate the Nifty-JME integration: 
     */
    NiftyJmeDisplay niftyDisplay = new NiftyJmeDisplay(
            assetManager, inputManager, audioRenderer, guiViewPort);
    Nifty nifty = niftyDisplay.getNifty();
    guiViewPort.addProcessor(niftyDisplay);
    nifty.fromXml("Interface/tutorial/screen3.xml", "start", startScreen);
    //nifty.setDebugOptionPanelColors(true);
    
    flyCam.setDragToRotate(true); // you need the mouse for clicking now    
  }

  @Override
  public void simpleUpdate(float tpf) {
  }

  public String getPlayerName() {
    return System.getProperty("user.name");
  }

}
