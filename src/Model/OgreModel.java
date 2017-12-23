package Model;
/**
 * Author : Gulraiz Iqbal
 */
import human.InputHandler;
import io.ExcelExporter;
import io.PlyExporter;

import java.io.File;
import java.io.IOException;

import com.jme3.animation.AnimControl;
import com.jme3.animation.Bone;
import com.jme3.animation.Skeleton;
import com.jme3.asset.AssetManager;
import com.jme3.asset.plugins.FileLocator;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.input.InputManager;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

public class OgreModel extends Model{
	private AnimControl ctrl;
	private File file=null;
	private BulletAppState bulletAppState;
	
	public OgreModel(Node node, ViewPort v, AssetManager mg, Camera cam,
			InputManager im, BulletAppState bulletAppState) {
			super(node, v, mg, cam, im);
			this.bulletAppState = bulletAppState;
	}

	
	@Override
	public void importModel(File file) {
		this.file=file;
		String path = file.getParent();

		assetManager.registerLocator(path,FileLocator.class);

		mymodel =  this.assetManager.loadModel(file.getName());

		Material mat= new Material(assetManager,"Common/MatDefs/Misc/Unshaded.j3md");
		
		setCamLocation(new Vector3f(0f,.5f,3f));
    	addNode(mymodel);
    	
    	setSize(new Vector3f(0.9f,0.9f,0.9f));
    	
    	addLight(new Vector3f(-20f,-20f,-20f));
    	
    	new InputHandler(im,mymodel,mat);
    	
    	this.ctrl = getAnimControl();
    	
    	this.getBonsDetails();
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

	public void resetModel(){
		removeModel();
		importModel(this.file);
	}
	
	public AnimControl getAnimControl() {
		AnimControl playerControl;
		playerControl = super.mymodel.getControl(AnimControl.class); // get
																	// control
																	// over this
																	// model
		playerControl.addListener(this); // add listener

		return playerControl;
	}

	public Skeleton getSkeleton() {
		return ctrl.getSkeleton();
	}

	public void resizAllBones() {

		Skeleton s = ctrl.getSkeleton();

		for (int i = 0; i < s.getBoneCount(); i++) {
			Bone b = s.getBone(i);
			//b.getLocalScale()
		
			b.setUserControl(true);
			b.setUserTransforms(b.getLocalPosition(),
					b.getModelSpaceRotation(), b.getLocalScale());
			// b.setBindTransforms(b.getLocalPosition(),
			// b.getModelSpaceRotation(), size);
			b.updateWorldVectors();
		}

	}

	public void resizeBone(Vector3f size, int bone) {

		Bone b = ctrl.getSkeleton().getBone(bone);

		// b.setUserControl(true);
		// b.setUserTransforms(b.getLocalPosition(), b.getModelSpaceRotation(),
		// size);
		System.out.println(b.getLocalPosition());
		b.setBindTransforms(b.getLocalPosition(), b.getModelSpaceRotation(),
				size);
		b.updateWorldVectors();
		// System.out.println(" Bone Info : "+b.getLocalScale());
	}

	// Get local scale of a given bone
	public Vector3f getBoneSize(String bone){
		return ctrl.getSkeleton().getBone(bone).getLocalScale();
	}
	
	
	public void resizeBone(Vector3f size, String bone) {

		Bone b = ctrl.getSkeleton().getBone(bone);

		// b.setUserControl(true);
		// b.setUserTransforms(b.getLocalPosition(), b.getModelSpaceRotation(),
		// size);
		System.out.println(b.getLocalScale().toString());
		b.setBindTransforms(b.getLocalPosition(), b.getModelSpaceRotation(),
				size);
		b.updateWorldVectors();
		
		
		// System.out.println(" Bone Info : "+b.getLocalScale());
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
	
	public String[] getBoneList(){
		Skeleton s = ctrl.getSkeleton();
		String bones[] = new String[s.getBoneCount()];
		for (int i = 0; i < s.getBoneCount(); i++) 
			bones[i]= s.getBone(i).getName();
	return bones;
	}
	
	public void scale(){
		Geometry geom = ((Geometry) ((Node) mymodel).getChild(0));
		
	
		geom.addControl(new RigidBodyControl(0));
		// WORKS great (scaling of a MeshCollisionShape)	
		geom.getControl(RigidBodyControl.class).getCollisionShape().setScale(new Vector3f(2, 2, 2));
		bulletAppState.getPhysicsSpace().add(geom);
	}
	
	public void getBonsDetails(){
		Skeleton s = ctrl.getSkeleton();
		for (int i = 0; i < s.getBoneCount(); i++){ 
			System.out.println( s.getBone(i).getName()+" size : "+s.getBone(i).getLocalScale());
	}
}}
