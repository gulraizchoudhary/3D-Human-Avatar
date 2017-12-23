package human;
/**
 * Author : Gulraiz Iqbal
 */
import java.io.IOException;

import com.jme3.asset.AssetManager;
import com.jme3.export.InputCapsule;
import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.export.OutputCapsule;
import com.jme3.export.Savable;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.Node;
import com.jme3.scene.debug.Grid;

public class MyGrid implements Savable {
	private static float STEP = 0.15f; // space between grid lines
	private static float WIDTH = 4.2f;
	
	private float y = .5f;
	private float z= 3f;
	private float x = 0f;
	private Node rootNode;
	private ViewPort viewPort;
	private AssetManager assetManager;
	private Camera cam;

	private Geometry backGrid;
	private Grid grid;
	

	public MyGrid(Node n, ViewPort v, AssetManager mg, Camera cam) {
		rootNode = n;
		viewPort = v;
		assetManager = mg;
		this.cam = cam;
	}

	public void setXYZ(float x, float y, float z){
		this.x=x;
		this.y= y;
		this.z=z;
	}
	
	
	public void addZone() {
		viewPort.setBackgroundColor(ColorRGBA.DarkGray);

		cam.setLocation(new Vector3f(this.x, this.y, this.z));
		cam.lookAt(Vector3f.UNIT_Y, Vector3f.UNIT_Y); // look at the origin with
														// +y-axis being up

		int numLines = (int) ((WIDTH / STEP) * 4 + 1);

		grid = new Grid(numLines - 1, numLines - 1, STEP);
		
		this.backGrid = addWireShape(grid,ColorRGBA.LightGray, "back");
		
		
	
		// white light aimed down in back-right quadrant
		DirectionalLight dl2 = new DirectionalLight();
		dl2.setDirection(new Vector3f(1, -1, -1).normalizeLocal());
		dl2.setColor(ColorRGBA.White);
		rootNode.addLight(dl2); 

	}

	public void removeWireShape(String name) {
		Geometry g = (Geometry) rootNode.getChild(name);
		rootNode.detachChild(g);
	}

	public void addWireShape(String name) {
		if (name.equals("back"))
			rootNode.attachChild(this.backGrid);

	}

	private Geometry addWireShape(Mesh shape, ColorRGBA color, String name) {
		
		shape.setLineWidth(1.5f);
		Geometry g = new Geometry(name, shape);
		
		g.rotate((float) Math.toRadians(99), 0, 0);
		g.setLocalTranslation(new Vector3f(-4f, 4.5f, -2f));
		g.updateModelBound();

		Material mat = new Material(assetManager,"Common/MatDefs/Misc/Unshaded.j3md");
		mat.getAdditionalRenderState().setWireframe(true);
		mat.setColor("Color", color);
		g.setMaterial(mat);
		rootNode.attachChild(g);
		return g;
	}

	@Override
	public void read(JmeImporter ex) throws IOException {



		
		OutputCapsule capsule = (OutputCapsule) ex.getCapsule(this);
		

		capsule.write(STEP,   "step",   0f);
        capsule.write(WIDTH,   "width",   0f);
        
		capsule.write(y,   "yValue",   0f);
        capsule.write(z,   "zValue",   0f);
        capsule.write(x,   "xValue",   0f);
        
        
    	//capsule.write(viewPort,  "viewPort",  new ViewPort(null, cam));
    	
    	//capsule.write(assetManager,  "assetManager",  assetManager);
    	capsule.write(cam,  "cam",  new Camera());
        capsule.write(rootNode,  "rootNode",  new Node());
        capsule.write(backGrid,  "backGrid",  new Geometry());
        capsule.write(grid,  "grid",  new Grid(0, 0, x));
        
	}

	@Override
	public void write(JmeExporter im) throws IOException {
		 InputCapsule capsule = (InputCapsule) im.getCapsule(this);
		    STEP = capsule.readFloat(  "step", 0f);
	        WIDTH = capsule.readFloat(  "width", 0f);
	    
		 	y = capsule.readFloat(  "yValue", 0f);
	        z = capsule.readFloat(  "zValue", 0f);
	        x = capsule.readFloat(  "xValue", 0f);
	        
	        cam =  (Camera) capsule.readSavable("cam",  new Camera());
	        rootNode =  (Node) capsule.readSavable("rootNode",  new Node());
	        backGrid =    (Geometry) capsule.readSavable("backGrid",  new Geometry());
	        grid =    (Grid) capsule.readSavable("grid",  new Grid(0, 0, x));
		
	}

}
