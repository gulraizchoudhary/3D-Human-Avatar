

package examples;

import io.PlyImporter;

import java.io.File;
import java.io.IOException;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.renderer.Camera;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.shape.Box;
import com.jme3.system.AppSettings;
import com.jme3.texture.FrameBuffer;
import com.jme3.texture.Image.Format;
import com.jme3.texture.Texture.MagFilter;
import com.jme3.texture.Texture.MinFilter;
import com.jme3.texture.Texture2D;

import de.lessvoid.nifty.Nifty;

public class TestNiftyToMesh extends SimpleApplication{

    private Nifty nifty;

    public static void main(String[] args){
AppSettings settings = new AppSettings(true);
		
		//settings.setFullscreen(true);
		//settings.setResolution(1590, 850);
		settings.setTitle("Human Model Application");
        TestNiftyToMesh app = new TestNiftyToMesh();
        app.setSettings(settings);
        app.start();
    }

    public void simpleInitApp() {
        /*     ViewPort niftyView = renderManager.createPreView("NiftyView", new Camera(1024, 768));
       niftyView.setClearFlags(true, true, true);
        NiftyJmeDisplay niftyDisplay = new NiftyJmeDisplay(assetManager,
                                                          inputManager,
                                                          audioRenderer,
                                                          niftyView);
    nifty = niftyDisplay.getNifty();
        nifty.fromXml("all/intro.xml", "start");
        niftyView.addProcessor(niftyDisplay);

        Texture2D depthTex = new Texture2D(1024, 768, Format.Depth);
        FrameBuffer fb = new FrameBuffer(1024, 768, 1);
        fb.setDepthTexture(depthTex);

        Texture2D tex = new Texture2D(1024, 768, Format.RGBA8);
        tex.setMinFilter(MinFilter.Trilinear);
        tex.setMagFilter(MagFilter.Bilinear);

        fb.setColorTexture(tex);
        niftyView.setClearFlags(true, true, true);
        niftyView.setOutputFrameBuffer(fb);
*/
    	
    	PlyImporter im = new PlyImporter();
    	Mesh m = null;
    	try {
			m =im.doLoadObject(new File("C:/Users/Gulraiz/Desktop/Skinning.org/PLY File sample/csr0001a.ply"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        //Box b = new Box(Vector3f.ZERO, 1, 1, 1);
       // 
        
        Geometry geom = new Geometry("Box", m);
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        //mat.setTexture("ColorMap", tex);
        geom.setMaterial(mat);
        rootNode.attachChild(geom);
    }
}