package human;
/**
 * Author : Gulraiz Iqbal
 */
public class HumanApp    {

	
	
	
	
	
	public static void main(String[] args) {	
		 new AvatarGUI(); 
	}


	/*
	public void getSkeltopn(AnimControl control){
		SkeletonDebugger sk = 
		         new SkeletonDebugger("skeleton", control.getSkeleton());
		
	}
	
	
	
    @Override
    public void simpleInitApp() {
    	
    	Display.setResizable(true);
    	
    	// activate windowed input behaviour
    	flyCam.setDragToRotate(true);
    	//flyCam.setUpVector(new Vector3f(0.7f, 0.7f, -4.0f));
    	
    	JFrame window = new JFrame("Swing Application");
    	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // new Thread(new  Human_GUI(new MyModel(rootNode, viewPort, assetManager, cam, inputManager),this));
  /*  	wz = new MyGrid(rootNode, viewPort, assetManager, cam);
    	wz.addZone();
    	
    	
    	mymodel = model.importBaseModel();
    	mat= new Material(assetManager,"Common/MatDefs/Misc/Unshaded.j3md");
    	model.addNode(mymodel);
    	model.addLight(new Vector3f());
    	 
    	//InputHandler handler = new InputHandler(inputManager,mymodel,mat);
    	
    */	
    	
    	
    	
    	
    	
    	//mymodel = model.importScene(); 
    	//orignalMat=	mymodel.getMaterial();
    	
    	
    	
    	
    	
    	//mymodel.setLocalTranslation(0, 0, 0);
    	
    	
    	
    	//mymodel.setLocalScale(0.24f);
    	
    	
    	//mymodel.setMaterial(mat);
    	
    	//System.out.println(mymodel.getChildren().size());
    	
    	
    	
    //	bulletAppState = new BulletAppState();
    //    stateManager.attach(bulletAppState);
    	
    	
    //	Geometry geom = (Geometry) mymodel.getChild(1);
    //rootNode.attachChild(geom);	
   // 	geom.move(0.1f, 0.1f, 0.1f);
    	//Matrix4f m = new Matrix4f();
    	 
        //FIRST LINE TO UNCOMMENT
 // m.setRotationQuaternion(new Quaternion(new float[] {FastMath.HALF_PI, 0, 0}));

/*
        //SECOND LINE TO UNCOMMENT
 m.setScale(0.22f, 0.25f, 0.25f);
m.setTranslation(0, -1.5f, 0);
Transform t = new Transform();
t.setRotation(m.toRotationQuat());
 t.setTranslation(m.toTranslationVector());
t.setScale(m.toScaleVector());

geom.setLocalTransform(t); */
//System.out.println(geom.getLocalScale());
    	
    	
    	
    	
    	/*geom.addControl(new RigidBodyControl(0));
    	//geom.setLocalScale(0.2f);
    	geom.getControl(RigidBodyControl.class).getCollisionShape().setScale(new Vector3f(0.4f, 0.4f, 0.4f));
    	
    	bulletAppState.getPhysicsSpace().add(geom);
    	bulletAppState.startPhysics();
    	rootNode.attachChild(geom);
		//orignalMat = g.getMaterial();
		//g.setLocalScale(2f, 2f, 2f);
		
		//orignalMat =.getMaterial();
		
    	//g.getMesh().setDynamic();
    	/*	
    	for(int i=0; i<mymodel.getChildren().size(); i++){
    		Geometry g = (Geometry) mymodel.getChild(i);
    		System.out.println(g.getName());
    		g.setLocalScale(2f, 2f, 2f);
    	}
    	
    	*/
    	//orignalMat = g.getMaterial();
    	/*
    	Geometry g = (Geometry) mymodel.getChild(0);
		Mesh m = g.getMesh();
				Deformation normalScale = new Deformation() {
				    public void deform( Vector3f vert, Vector3f normal ) {
				        float scale = 0.1f;  // or whatever
				        vert.addLocal( normal.mult(scale) );
				    }
				};
				
				DMesh newMesh = new DMesh(m, normalScale);
				
				//newMesh.updateBound();
				
				Geometry g1 = new Geometry("human model", newMesh);
				g1.setMaterial(mat);
				rootNode.attachChild(g1);
    	
    	
    }
    
    
    
    
    
	@Override
    public void stop() {
	   
		//model.exportScene(rootNode);
	    super.stop(); // continue quitting the game
    }
	
	public void update() {
        super.update();
        if (Display.wasResized()) {
            int newWidth = Math.max(Display.getWidth(), 1);
            int newHeight = Math.max(Display.getHeight(), 1);
            reshape(newWidth, newHeight);
        } 
     }*/


	
    
}
 
