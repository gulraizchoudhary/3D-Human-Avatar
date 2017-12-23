/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorial;

import com.jme3.app.SimpleApplication;
import com.jme3.niftygui.NiftyJmeDisplay;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.ScreenBuilder;
import de.lessvoid.nifty.builder.LayerBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.controls.button.builder.ButtonBuilder;
import de.lessvoid.nifty.screen.DefaultScreenController;

/**
 * This is a minimal example of Nifty's Java syntax.
 * It shows one screen with one layer, containing one panel.
 * The panel contains one button control.
 * @author iamcreasy  
 */
public class NiftyTemplate extends SimpleApplication {

  public static void main(String[] args) {
    NiftyTemplate app = new NiftyTemplate();
    app.start();
  }

  @Override
  public void simpleInitApp() {
    NiftyJmeDisplay niftyDisplay = new NiftyJmeDisplay(
            assetManager, inputManager, audioRenderer, guiViewPort);
    Nifty nifty = niftyDisplay.getNifty();
    guiViewPort.addProcessor(niftyDisplay);
    flyCam.setDragToRotate(true);

    nifty.loadStyleFile("nifty-default-styles.xml");
    nifty.loadControlFile("nifty-default-controls.xml");

    // <screen>
    nifty.addScreen("Screen_ID", new ScreenBuilder("Hello Nifty Screen") {{
        controller(new DefaultScreenController()); // Screen properties       

        // <layer>
        layer(new LayerBuilder("Layer_ID") {{
            childLayoutCenter(); // layer properties, add more...

            // <panel>
            panel(new PanelBuilder("Panel_ID") {{
                childLayoutCenter(); // panel properties, add more...               

                // GUI elements
                control(new ButtonBuilder("Control_ID", "Hello Nifty") {{
                    alignCenter();
                    valignCenter();
                    height("5%");
                    width("15%");
                }});

                //.. add more GUI elements here              

            }});
            // </panel>
        }});
        // </layer>
    }}.build(nifty));
    // </screen>

    nifty.gotoScreen("Screen_ID"); // start the screen
  }
}
