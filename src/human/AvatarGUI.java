package human;
/**
 * Author : Gulraiz Iqbal
 */

import io.ModelFileView;
import io.ModelFilter;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.lwjgl.opengl.Display;

import Model.J3oModel;
import Model.Model;
import Model.ObjectModel;
import Model.OgreModel;
import Model.PlyModel;
import db.MeasurementDB;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import com.jme3.system.AppSettings;
import com.jme3.system.JmeCanvasContext;

public class AvatarGUI extends SimpleApplication implements ActionListener, ChangeListener, Runnable {
	static final int FPS_MIN = 5;
	static final int FPS_MAX = 15;
	static final int FPS_INIT = 10;
	
	JFrame window ;

	private String bone = "hips";

	Model model;
	JPanel panel;
	JmeCanvasContext ctx;
	LeftPanel pLeft=null;
	BulletAppState bulletAppState;
	MeasurementDB ceaserDb;
	
	
	public AvatarGUI() {	
		// Create Swing window
		ceaserDb = new MeasurementDB();
		try {
			ceaserDb.importExcelDB("Measurements2.xlsx");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		window = new JFrame(".::Avatar::.");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		window.setPreferredSize(screenSize);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		EventQueue.invokeLater(this);

	}

	public void setBone(String bone){
		this.bone=bone;
	}
	
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("Open")){
			JFileChooser fc = new JFileChooser();
			
			int val = fc.showOpenDialog(window);
			
	        if (val == JFileChooser.APPROVE_OPTION) {
	        	//rootNode.detachAllChildren();
	            File file = fc.getSelectedFile();
	            model = new J3oModel(rootNode, viewPort, assetManager, cam, inputManager);
	            model.importModel(file);
	        }
		}
		else if(e.getActionCommand().equals("Save")){
			JFileChooser fc = new JFileChooser();
			int val=fc.showSaveDialog(window);
			 if (val == JFileChooser.APPROVE_OPTION) {
	                File file = new File(fc.getSelectedFile()+".j3o");
	                if(model instanceof J3oModel)
	                	model.exportModel(file);
	                else
	                	new J3oModel(rootNode, viewPort, assetManager, cam, inputManager).exportModel(file);
	     } 
		}
		
		else if(e.getActionCommand().equals("Ply 3D")){
			JFileChooser fc = new JFileChooser();

			fc.addChoosableFileFilter(new ModelFilter());
            fc.setAcceptAllFileFilterUsed(false);

            //Add custom icons for file types.
            fc.setFileView(new ModelFileView());
			
			int val = fc.showOpenDialog(window);

	        if (val == JFileChooser.APPROVE_OPTION) {
	        	rootNode.detachAllChildren();
	        	File file = fc.getSelectedFile();
	            model = new PlyModel(rootNode, viewPort, assetManager, cam, inputManager);
	            model.importModel(file);
	        }
		}
		else if(e.getActionCommand().equals("Wavefront Obj 3D")){
			JFileChooser fc = new JFileChooser();

			fc.addChoosableFileFilter(new ModelFilter());
            fc.setAcceptAllFileFilterUsed(false);

            //Add custom icons for file types.
            fc.setFileView(new ModelFileView());
			
			int val = fc.showOpenDialog(window);

	        if (val == JFileChooser.APPROVE_OPTION) {
	        	rootNode.detachAllChildren();
	        	File file = fc.getSelectedFile();
	            model = new ObjectModel(rootNode, viewPort, assetManager, cam, inputManager);
	            model.importModel(file);
	        }
		}
		else if(e.getActionCommand().equals("Ogre 3D")){
			JFileChooser fc = new JFileChooser();

			//fc.addChoosableFileFilter(new ModelFilter());
			
            fc.setAcceptAllFileFilterUsed(false);

            //Add custom icons for file types.
            fc.setFileView(new ModelFileView());
			
			int val = fc.showOpenDialog(window);

	        if (val == JFileChooser.APPROVE_OPTION) {
	        	rootNode.detachAllChildren();
	            File file = fc.getSelectedFile();
	            model = new OgreModel(rootNode, viewPort, assetManager, cam, inputManager,bulletAppState);
	            model.importModel(file);
	            Dimension dim = new Dimension(1250, 800);
	    		ctx.getCanvas().setPreferredSize(dim);
	            pLeft = new LeftPanel(((OgreModel) model).getBoneList(), this, this);
	            panel.add(pLeft);
	        	panel.updateUI();
	        
	        }	
		}
		else if(e.getActionCommand().equals("Render Model")){
			this.setBone(this.OpenMsgBox());
		}
		else if(e.getActionCommand().equals("Reset")){
			if(model instanceof OgreModel)
				((OgreModel) model).resetModel();
		}
		else if(e.getActionCommand().equals("Exit")){
			System.exit(0);
		}
		else if(e.getActionCommand().equals("gender")){
			JComboBox cb = (JComboBox)e.getSource();
	        String gender = (String)cb.getSelectedItem();
	        OgreModel m = (OgreModel) model;
	        Vector3f size;
	        if(gender.equals("Male"))
	        	size = new Vector3f(0.8f,0.8f,0.8f);
	        
	        else
	        	size = new Vector3f(1.2f,1.2f,1.5f);
	        	
	        	m.resizeBone(size, "breast.L");
	        	m.resizeBone(size, "breast.R");
	        	
	        	
		}
		
		if(!(model instanceof OgreModel) && this.pLeft!=null){
			
			panel.remove(this.pLeft);
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			ctx.getCanvas().setPreferredSize(dim);
			panel.updateUI();
		}
		this.gainFocus();
		
	}

	@Override
	public void stateChanged(ChangeEvent e) {
	
		JSlider source = (JSlider) e.getSource();
		
		if(model instanceof OgreModel){
			OgreModel m = (OgreModel) model;
		if (!source.getValueIsAdjusting()) {
			double fps =   ((double) source.getValue());
		
			
		//	fps+=100;
		//	fps =Math.rint(fps*100)/100;
			float val = (float) fps;
			
			//Manipulating height of model 
			if (source.getName().equals("jsHeight")) {
				pLeft.setTxtHeight(fps+" mm");
				float s = ceaserDb.get_Stature_height(.5, 1.5, fps);
				//System.out.println("is it correct"+ceaserDb.get_Stature_height_org(.5, 1.5, s));
				//Vector3f size = new Vector3f((float)(s*1.2),s,(float)(s*1.8));
				Vector3f hip = m.getBoneSize("hips");
				Vector3f size = new Vector3f(hip.x,s,s);
				m.resizeBone(size, "hips");
				
				
			} else if (source.getName().equals("biCristal")) {
				pLeft.setTxtWeight(fps+" mm");
				float s = ceaserDb.get_BiCristal_breadth(1, 2, fps);
				Vector3f hip = m.getBoneSize("hips");
				Vector3f size = new Vector3f(s,hip.y,hip.z);
				
				m.resizeBone(size, "hips");
				System.out.println("fps..........................."+s);
			}
			else if (source.getName().equals("jsBitroch")) {
				pLeft.setTxtBust(fps+" mm");
				float s = ceaserDb.get_Bitroch_breadth(0.8, 1.1, fps);
				
				Vector3f size = new Vector3f(s,s,s);
				m.resizeBone(size, "spine");
			}
			else if ( source.getName().equals("jsWaist")) {
				pLeft.setTxtWaist(fps+" in");
				
			} else if (source.getName().equals("jsHip")) {
				pLeft.setTxtHip(fps+" in");
				
			} else if (source.getName().equals("jsInseam")) {
				pLeft.setTxtInseam(fps+" in");	
			
			} else if (source.getName().equals("jsNeck")) {
				pLeft.setTxtNeck(fps+" in");	
			
			} else if (source.getName().equals("jsSleeve")) {
				pLeft.setTxtSleeve(fps+" in");
		}}
		}

		this.gainFocus();
		window.revalidate();
		//window.repaint();
	}

	public JMenuBar addMenuBar(){
		JMenuBar menuBar = new JMenuBar();    
	    // build the File menu
		JMenu fileMenu = new JMenu("File");
		
		JMenuItem openMenuItem = new JMenuItem("Open");
		openMenuItem.setMnemonic(KeyEvent.VK_O);
		JMenuItem saveMenuItem = new JMenuItem("Save");
		
		JMenu imSub = new JMenu("Import 3D Model");
	    JMenuItem ply = new JMenuItem("Ply 3D");
	    imSub.add(ply);
		JMenuItem ogre = new JMenuItem("Ogre 3D");
		imSub.add(ogre);
		JMenuItem obj = new JMenuItem("Wavefront Obj 3D");
		imSub.add(obj);
		
		JMenu exSub = new JMenu("Export 3D Model");
		JMenuItem excel = new JMenuItem("Excel");
	    exSub.add(excel);
	    
	    JMenuItem exPly = new JMenuItem("Ply");
	    exSub.add(exPly);
		fileMenu.addSeparator();
		
		JMenuItem closeMenuItem = new JMenuItem("Close");
		
		
		JMenuItem exitMenuItem = new JMenuItem("Exit");
		
	    openMenuItem.addActionListener(this);
	    saveMenuItem.addActionListener(this);
	    ply.addActionListener(this);
	    ogre.addActionListener(this);
	    obj.addActionListener(this);
	    excel.addActionListener(this);
	    exitMenuItem.addActionListener(this);
	    
	    fileMenu.add(openMenuItem);
	    fileMenu.add(saveMenuItem);
	    fileMenu.add(imSub);
	    fileMenu.add(exSub);
	    
	    fileMenu.add(closeMenuItem);
	    fileMenu.add(exitMenuItem);
	    
		menuBar.add(fileMenu);
	    
		JMenu editMenu = new JMenu("Edit");
		JMenuItem scale = new JMenuItem("Render Model");
		JMenuItem reset = new JMenuItem("Reset");
		editMenu.add(scale);
		editMenu.add(reset);
		scale.addActionListener(this);
		reset.addActionListener(this);
		
		menuBar.add(editMenu);
	    return menuBar;
	}

	public String OpenMsgBox(){
	
	String[] boneString = { "hips", "gluteus.L", "thigh.L", "thigh.R",
			"hipside.L", "spine", "hipside.R", "gluteus.R", "pubis",
			"quadriceps.L", "femoris.L", "knee_fan.L", "shin.L",
			"quadriceps.R", "femoris.R", "gluteus0.R", "knee_fan.R",
			"shin.R", "spine-1", "soleus.L", "foot.L", "soleus.R",
			"foot.R", "chest", "toe.L", "sole.L", "toe.R", "sole.R",
			"chest-1", "clavicle.R", "clavicle.L", "breast.L", "breast.R",
			"trapezius2.L", "trapezius2.R", "stomach", "pectoralis.R",
			"pectoralis.L", "neck", "deltoid.R", "scapula.R", "deltoid.L",
			"scapula.L", "trapezius1.R", "trapezius1.L", "platysma.R",
			"platysma.L", "head", "upper_arm.R", "upper_arm.L", "jaw",
			"eye.R", "uplid.L", "uplid.R", "eye.L", "lolid.L", "lolid.R",
			"elbow_fan.R", "lat_dorsi.R", "forearm.R", "elbow_fan.L",
			"lat_dorsi.L", "forearm.L", "tongue_base", "hand.R", "hand.L",
			"tongue_mid", "palm_pinky.R", "palm_middle.R", "palm_ring.R",
			"thumb.01.R", "palm_index.R", "palm_pinky.L", "palm_middle.L",
			"palm_index.L", "palm_ring.L", "thumb.01.L", "tongue_tip",
			"f_pinky.01.R", "f_middle.01.R", "f_ring.01.R", "thumb.02.R",
			"f_index.01.R", "f_pinky.01.L", "f_middle.01.L",
			"f_index.01.L", "f_ring.01.L", "thumb.02.L", "f_pinky.02.R",
			"f_middle.02.R", "f_ring.02.R", "thumb.03.R", "f_index.02.R",
			"f_pinky.02.L", "f_middle.02.L", "f_index.02.L", "f_ring.02.L",
			"thumb.03.L", "f_pinky.03.R", "f_middle.03.R", "f_ring.03.R",
			"f_index.03.R", "f_pinky.03.L", "f_middle.03.L",
			"f_index.03.L", "f_ring.03.L" };
	
	String s = (String)JOptionPane.showInputDialog(
            window,
            "Select a Bone from List:\n"
            + "",
            "Customized Dialog",
            JOptionPane.PLAIN_MESSAGE,
            null,
            boneString,
            "hips");
	return s;

	}
	@Override
	public void run() {
		AppSettings settings = new AppSettings(true);

		//app = new HumanApp();
		this.setShowSettings(false);

		this.setSettings(settings);
		this.createCanvas(); // create canvas!

		ctx = (JmeCanvasContext) this.getContext();
		ctx.setSystemListener(this);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		//Dimension dim = new Dimension(1250, 800);
		ctx.getCanvas().setPreferredSize(dim);

		

		panel = new JPanel();

		panel.setLayout(new BorderLayout());
		panel.add(ctx.getCanvas(), BorderLayout.WEST); // add JME canvas


		// Adding menu bar
		window.setJMenuBar(addMenuBar());
		
		
		
		window.add(panel);
		window.pack();

		// Display Swing window including JME canvas!
		window.setVisible(true);
		this.startCanvas();

	}

	@Override
	public void simpleInitApp() {
		bulletAppState = new BulletAppState();
	    stateManager.attach(bulletAppState);
		Display.setResizable(true);
    	
		// activate windowed input behaviour
    	flyCam.setDragToRotate(true);
	}
}
