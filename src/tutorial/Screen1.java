package tutorial;

import com.jme3.app.SimpleApplication;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.system.AppSettings;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.ScreenBuilder;
import de.lessvoid.nifty.builder.LayerBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.screen.DefaultScreenController;

/**
 * This demo shows a two-screen layout in Nifty's Java syntax.
 * You see two screens with two layers each, containing several panels.
 * @author iamcreasy  
 */
public class Screen1 extends SimpleApplication {
  private static Screen1 app;
  
  public static void main(String[] args) {
    app = new Screen1();
    AppSettings settings = new AppSettings(true);
    settings.setResolution(640, 480);
    app.setShowSettings(false); // splashscreen
    app.setSettings(settings);
    app.start();
  }

  @Override
  public void simpleInitApp() {
    app.setDisplayFps(false);
    app.setDisplayStatView(false);

    
    NiftyJmeDisplay niftyDisplay = new NiftyJmeDisplay(
            assetManager, inputManager, audioRenderer, guiViewPort);
    Nifty nifty = niftyDisplay.getNifty();
    guiViewPort.addProcessor(niftyDisplay);
    flyCam.setDragToRotate(true);

    nifty.loadStyleFile("nifty-default-styles.xml");
    nifty.loadControlFile("nifty-default-controls.xml");

    // <screen>
    nifty.addScreen("start", new ScreenBuilder("start") {{
        controller(new DefaultScreenController());
        layer(new LayerBuilder("background") {{
            childLayoutCenter();
            backgroundColor("#000f");
            
        }});

        layer(new LayerBuilder("foreground") {{
                childLayoutVertical();
                backgroundColor("#0000");
                
            // panel added
            panel(new PanelBuilder("panel_top") {{
                childLayoutCenter();
                alignCenter();
                backgroundColor("#f008");
                height("25%");
                width("75%");
                                
            }});

            panel(new PanelBuilder("panel_mid") {{
                childLayoutCenter();
                alignCenter();
                backgroundColor("#0f08");
                height("50%");
                width("75%");

            }});

            panel(new PanelBuilder("panel_bottom") {{
                childLayoutHorizontal();
                alignCenter();
                backgroundColor("#00f8");
                height("25%");
                width("75%");

                panel(new PanelBuilder("panel_bottom_left") {{
                    childLayoutCenter();
                    valignCenter();
                    backgroundColor("#44f8");
                    height("50%");
                    width("50%");
                                        
                }});

                panel(new PanelBuilder("panel_bottom_right") {{
                    childLayoutCenter();
                    valignCenter();
                    backgroundColor("#88f8");
                    height("50%");
                    width("50%");

                }});
            }}); // panel added
        }});

    }}.build(nifty));


    nifty.addScreen("hud", new ScreenBuilder("hud") {{
        controller(new DefaultScreenController());

        layer(new LayerBuilder("background") {{
            childLayoutCenter();
            backgroundColor("#000f");
            
        }});

        layer(new LayerBuilder("foreground") {{
            childLayoutHorizontal();
            backgroundColor("#0000");

            // panel added
            panel(new PanelBuilder("panel_left") {{
                childLayoutVertical();
                backgroundColor("#0f08");
                height("100%");
                width("80%");
                // <!-- spacer -->
            }});

            panel(new PanelBuilder("panel_right") {{
                childLayoutVertical();
                backgroundColor("#00f8");
                height("100%");
                width("20%");

                panel(new PanelBuilder("panel_top_right1") {{
                    childLayoutCenter();
                    backgroundColor("#00f8");
                    height("15%");
                    width("100%");
                                        
                }});

                panel(new PanelBuilder("panel_top_right2") {{
                    childLayoutCenter();
                    backgroundColor("#44f8");
                    height("15%");
                    width("100%");
                    
                }});

                panel(new PanelBuilder("panel_bot_right") {{
                    childLayoutCenter();
                    valignCenter();
                    backgroundColor("#88f8");
                    height("70%");
                    width("100%");
                }});
            }}); // panel added
        }});
    }}.build(nifty));
    
    nifty.gotoScreen("start"); // open the start screen
    // nifty.gotoScreen("hud"); // open the hud screen
  }
}
