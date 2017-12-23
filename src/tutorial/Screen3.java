package tutorial;

import com.jme3.app.SimpleApplication;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.system.AppSettings;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.ImageBuilder;
import de.lessvoid.nifty.builder.ScreenBuilder;
import de.lessvoid.nifty.builder.LayerBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.builder.TextBuilder;
import de.lessvoid.nifty.controls.button.builder.ButtonBuilder;
import de.lessvoid.nifty.controls.label.builder.LabelBuilder;
import de.lessvoid.nifty.screen.DefaultScreenController;

/**
 * This demo shows a two-screen layout in Nifty's Java syntax.
 * You see two screens with two layers each, containing several panels.
 * The panels contain images, text, and controls (label and buttons).
 * Buttons have an interaction defined, and some of the text
 * is dynamically defined, using the MyStartScreen controller. 
 * @author iamcreasy  
 */
public class Screen3 extends SimpleApplication {
  private static Screen3 app;
  
  public static void main(String[] args) {
    app = new Screen3();
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
        controller(new tutorial.MyStartScreen());
        layer(new LayerBuilder("background") {{
            childLayoutCenter();
            
            // add image
            image(new ImageBuilder() {{
                filename("Interface/tutorial/start-background.png");
            }});

        }});

        layer(new LayerBuilder("foreground") {{
                childLayoutVertical();
                
            // panel added
            panel(new PanelBuilder("panel_top") {{
                childLayoutCenter();
                alignCenter();
                height("25%");
                width("75%");
                
                // add text
                text(new TextBuilder() {{
                    text("${CALL.getPlayerName()}'s Cool Game");
                    font("Interface/Fonts/Default.fnt");
                    height("100%");
                    width("100%");
                }});
                
            }});

            panel(new PanelBuilder("panel_mid") {{
                childLayoutCenter();
                alignCenter();
                height("50%");
                width("75%");
                
                // add text
                text(new TextBuilder() {{
                    text("Here goes some text describing the game and the rules and stuff. "+
                         "Incidentally, the text is quite long and needs to wrap at the end of lines. "+
                         "Here goes some text describing the game and the rules and stuff. "+
                         "Incidentally, the text is quite long and needs to wrap at the end of lines. "+
                         "Here goes some text describing the game and the rules and stuff. "+
                         "Incidentally, the text is quite long and needs to wrap at the end of lines. ");
                    font("Interface/Fonts/Default.fnt");
                    wrap(true);
                    height("100%");
                    width("100%");
                }});

            }});

            panel(new PanelBuilder("panel_bottom") {{
                childLayoutHorizontal();
                alignCenter();
                height("25%");
                width("75%");

                panel(new PanelBuilder("panel_bottom_left") {{
                    childLayoutCenter();
                    valignCenter();
                    height("50%");
                    width("50%");
                    
                    // add button control
                    control(new ButtonBuilder("StartButton", "Start") {{
                      alignCenter();
                      valignCenter();
                      height("50%");
                      width("50%");
                      visibleToMouse(true);
                      interactOnClick("startGame(hud)");
                    }});
                    
                }});

                panel(new PanelBuilder("panel_bottom_right") {{
                    childLayoutCenter();
                    valignCenter();
                    height("50%");
                    width("50%");

                    // add button control
                    control(new ButtonBuilder("QuitButton", "Quit") {{
                      alignCenter();
                      valignCenter();
                      height("50%");
                      width("50%");
                      visibleToMouse(true);
                      interactOnClick("quitGame()");
                    }});

                }});
            }}); // panel added
        }});

    }}.build(nifty));


    nifty.addScreen("hud", new ScreenBuilder("hud") {{
        controller(new tutorial.MyStartScreen());

        layer(new LayerBuilder("background") {{
            childLayoutCenter();
            
            // add image
            image(new ImageBuilder() {{
                filename("Interface/tutorial/hud-frame.png");
            }});

        }});

        layer(new LayerBuilder("foreground") {{
            childLayoutHorizontal();

            // panel added
            panel(new PanelBuilder("panel_left") {{
                childLayoutVertical();
                height("100%");
                width("80%");
                // <!-- spacer -->
            }});

            panel(new PanelBuilder("panel_right") {{
                childLayoutVertical();
                height("100%");
                width("20%");

                panel(new PanelBuilder("panel_top_right1") {{
                    childLayoutCenter();
                    height("15%");
                    width("100%");
                    
                    // add label control
                    control(new LabelBuilder(){{
                        id("score");
                        color("#000"); 
                        text("123"); 
                        width("100%"); 
                        height("100%");
                    }});
                    
                }});

                panel(new PanelBuilder("panel_top_right2") {{
                    childLayoutCenter();
                    height("15%");
                    width("100%");
                    
                    // add image
                    image(new ImageBuilder() {{
                        filename("Interface/tutorial/face1.png");
                        valignCenter();
                        alignCenter();
                        height("50%");
                        width("30%");
                    }});
                    
                }});

                panel(new PanelBuilder("panel_bot_right") {{
                    childLayoutCenter();
                    valignCenter();
                    height("70%");
                    width("100%");
                }});
            }}); // panel added
        }});
    }}.build(nifty));
    
    nifty.gotoScreen("start"); // start the screen
    //nifty.gotoScreen("hud"); // start the screen
  }
}
