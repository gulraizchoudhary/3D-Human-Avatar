package Model;
/**
 * Author : Gulraiz Iqbal
 */
import human.HumanApp;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jme3.asset.AssetManager;
import com.jme3.asset.plugins.FileLocator;
import com.jme3.export.binary.BinaryExporter;
import com.jme3.input.InputManager;
import com.jme3.renderer.Camera;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.Node;

public class J3oModel extends Model{

	public J3oModel(Node node, ViewPort v, AssetManager mg, Camera cam,
			InputManager im) {
		super(node, v, mg, cam, im);
		
	}		
	

	@Override
	public void importModel(File file) {
		
		String path = file.getParent();

		assetManager.registerLocator(path,FileLocator.class);
		
		mymodel = (Node) assetManager.loadModel(file.getName());
		mymodel.setName("loaded node");
		
		rootNode.attachChild(mymodel);		
	}

	@Override
	public void exportModel(File file) {

		BinaryExporter exporter = BinaryExporter.getInstance();
		try {
			exporter.save(rootNode, file);
		} catch (IOException ex) {
			Logger.getLogger(HumanApp.class.getName()).log(Level.SEVERE,
					"Error: Failed to save game!", ex);
		}
	}

	
	@Override
	public Mesh getMesh() {
		return null;
	}

	@Override
	public Geometry getGeometry() {
		return null;
	}

}
