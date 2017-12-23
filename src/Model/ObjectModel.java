package Model;

import human.InputHandler;
import io.ExcelExporter;
import io.PlyExporter;

import java.io.File;
import java.io.IOException;

import com.jme3.asset.AssetManager;
import com.jme3.asset.plugins.FileLocator;
import com.jme3.input.InputManager;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.Node;
/**
 * Author : Gulraiz Iqbal
 */
public class ObjectModel extends Model {

	public ObjectModel(Node node, ViewPort v, AssetManager mg, Camera cam,
			InputManager im) {
		super(node, v, mg, cam, im);
	}

	@Override
	public void importModel(File file) {
		String path = file.getParent();

		assetManager.registerLocator(path,FileLocator.class);

		mymodel =  this.assetManager.loadModel(file.getName());
		//this.setModelScale();

		Material mat= new Material(assetManager,"Common/MatDefs/Misc/Unshaded.j3md");
		
		setCamLocation(new Vector3f(0f,.5f,3f));
		//mat.setBoolean("VertexColor", true);
		mymodel.setMaterial(mat);
		
		//mymodel.setLocalTranslation(0f,0f,3f); 
		addNode(mymodel);
    	addLight(new Vector3f(-10f,-10f,-10f));
    	
    	new InputHandler(im,(Geometry)mymodel,mat);	
	}

	@Override
	public void exportModel(File file) {
		if(file.getName().endsWith(".ply")){
			PlyExporter ex = new PlyExporter();
			try {
				ex.doExportObject(getMesh(),file);
			} catch (IOException e) {
				e.printStackTrace();
			}
			}
			else if(file.getName().endsWith(".xls")){
				try {
					new ExcelExporter(file.getName(),getMesh());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	}

	@Override
	public Mesh getMesh() {		
		Node n = (Node) mymodel;
		Geometry g =(Geometry) n.getChild(0);
		return g.getMesh(); 
	}

	@Override
	public Geometry getGeometry() {
		Node n = (Node) mymodel;
		return (Geometry) n.getChild(0);
	}
}
