package Model;
/**
 * Author : Gulraiz Iqbal
 */
import human.InputHandler;
import io.ExcelExporter;
import io.PlyImporter;

import java.io.File;
import java.io.IOException;

import com.jme3.asset.AssetManager;
import com.jme3.input.InputManager;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.Node;

public class PlyModel extends Model {

	private PlyImporter imp;
	private Geometry geom;
	public PlyModel(Node node, ViewPort v, AssetManager mg, Camera cam,
			InputManager im) {
		super(node, v, mg, cam, im);
			imp = new PlyImporter();
	}

	
	@Override
	public void importModel(File file) {
			
	    	Mesh m = null;
	    	try {
				m =imp.doLoadObject(file);
				
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
	       
	        geom = new Geometry("PLY", m);
	        Material mat = new Material(super.assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
	       
	        mat.setBoolean("VertexColor", true);
	        geom.setMaterial(mat);
	        geom.rotate(-1.5f,1f,0);
	        geom.setLocalTranslation(0f,0f,3f); 
	        geom.setLocalScale(1f, 1f, 1f);
	        //addLight(new Vector3f(-10f,-10f,-10f));
	         addNode(geom);
	        
	        
	       // new InputHandler(this.im,geom,mat);
	}

	@Override
	public void exportModel(File file) {
		try {
			 new ExcelExporter(file.getAbsolutePath(),getMesh());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	@Override
	public Mesh getMesh() {
		return imp.getMesh();
	}


	@Override
	public Geometry getGeometry() {
		return this.geom;
	}

}
