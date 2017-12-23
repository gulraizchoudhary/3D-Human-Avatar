package Model;
/**
 * Author : Gulraiz Iqbal
 */

import java.io.File;
import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.animation.AnimEventListener;
import com.jme3.asset.AssetManager;
import com.jme3.input.InputManager;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

public abstract class Model implements AnimEventListener {
	public Spatial mymodel;
	public Node rootNode;
	public AssetManager assetManager;
	public ViewPort viewPort;
	public Camera cam;
	public InputManager im;
	public Vector3f size;

	public Model(Node node,ViewPort v, AssetManager mg, Camera cam, InputManager im) {
		this.rootNode = node;
		this.assetManager = mg;
		this.viewPort = v;
		this.cam = cam;
		this.im = im;
	}

	public abstract void importModel(File file);
	public abstract void exportModel(File file);
	public abstract Mesh getMesh();
	public abstract Geometry getGeometry();
	
	

	public void setSize(Vector3f v){
		mymodel.setLocalScale(v);
		mymodel.updateGeometricState();
	}
	
	public Vector3f getSize(){
		return this.size;
	}


	public void addNode(Spatial node){
		this.mymodel = node;
    	rootNode.attachChild(node);
    }
    
    public void removeModel(){
    	rootNode.detachChild(this.mymodel);
    	this.mymodel = null;
    	rootNode.updateGeometricState();
    }
	
    public void addLight(Vector3f v){
    	DirectionalLight sun = new DirectionalLight();
    	sun.setDirection(v.normalizeLocal());
    	sun.setColor(ColorRGBA.White);
    	rootNode.addLight(sun);
    }
    
    public void setCamLocation(Vector3f v){
    	cam.setLocation(v);
		cam.lookAt(Vector3f.UNIT_Y, Vector3f.UNIT_Y); // look at the origin with
    }
	
	@Override
	public void onAnimChange(AnimControl arg0, AnimChannel arg1, String arg2) {

	}

	@Override
	public void onAnimCycleDone(AnimControl arg0, AnimChannel arg1, String arg2) {

	}
}
