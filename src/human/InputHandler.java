package human;

/**
 * Author : Gulraiz Iqbal
 */
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.Trigger;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

public class InputHandler {

	private final static Trigger TRIGGER_MESH = new KeyTrigger(KeyInput.KEY_M);
	private final static Trigger TRIGGER_ROTATE_R = new KeyTrigger(
			KeyInput.KEY_R);
	private final static Trigger TRIGGER_ROTATE_L = new KeyTrigger(
			KeyInput.KEY_L);
	private final static Trigger TRIGGER_NORMAL = new KeyTrigger(KeyInput.KEY_N);
	private final static Trigger TRIGGER_SCALE_X = new KeyTrigger(
			KeyInput.KEY_X);
	private final static Trigger TRIGGER_SCALE_Y = new KeyTrigger(
			KeyInput.KEY_Y);
	private final static Trigger TRIGGER_SCALE_Z = new KeyTrigger(
			KeyInput.KEY_Z);

	private final static String MAPPING_MESH = "Mesh";
	private final static String MAPPING_ROTATE_R = "Rotate_r";
	private final static String MAPPING_ROTATE_L = "Rotate_l";
	private final static String MAPPING_NORMAL = "Normal";
	private final static String MAPPING_SCALE_X = "Scale_x";
	private final static String MAPPING_SCALE_Y = "Scale_y";
	private final static String MAPPING_SCALE_Z = "Scale_z";

	private InputManager inputManager;
	private Material mat = null;
	private Spatial mymodel;

		public InputHandler(InputManager in, Spatial sp, Material mat) {
		this.inputManager = in;

		this.mymodel = sp;
		this.mat = mat;


		inputManager.addMapping(MAPPING_MESH, TRIGGER_MESH);
		inputManager.addMapping(MAPPING_ROTATE_R, TRIGGER_ROTATE_R);
		inputManager.addMapping(MAPPING_ROTATE_L, TRIGGER_ROTATE_L);
		inputManager.addMapping(MAPPING_NORMAL, TRIGGER_NORMAL);
		inputManager.addMapping(MAPPING_SCALE_X, TRIGGER_SCALE_X);
		inputManager.addMapping(MAPPING_SCALE_Y, TRIGGER_SCALE_Y);
		inputManager.addMapping(MAPPING_SCALE_Z, TRIGGER_SCALE_Z);

		inputManager.addListener(actionListener, new String[] { MAPPING_MESH });
		inputManager.addListener(analogListener,
				new String[] { MAPPING_ROTATE_R });
		inputManager.addListener(analogListener,
				new String[] { MAPPING_ROTATE_L });
		inputManager.addListener(actionListener,
				new String[] { MAPPING_NORMAL });
		inputManager.addListener(analogListener,
				new String[] { MAPPING_SCALE_X });
		inputManager.addListener(analogListener,
				new String[] { MAPPING_SCALE_Y });
		inputManager.addListener(analogListener,
				new String[] { MAPPING_SCALE_Z });

	}


	public ActionListener actionListener = new ActionListener() {
		public void onAction(String name, boolean isPressed, float tpf) {
			if (name.equals(MAPPING_MESH) && !isPressed) {

				mat.setColor("Color", ColorRGBA.randomColor());

				// displaying mesh inofrmation around the cube
				mat.getAdditionalRenderState().setWireframe(true);

				mymodel.setMaterial(mat);

				// mymodel.getMaterial().setColor("Color",
				// ColorRGBA.randomColor());
			}
			if (name.equals(MAPPING_NORMAL) && !isPressed) {
				mat.getAdditionalRenderState().setWireframe(false);
				mymodel.setMaterial(mat);

			}
		}
	};

	public AnalogListener analogListener = new AnalogListener() {
		public void onAnalog(String name, float intensity, float tpf) {
			if (name.equals(MAPPING_ROTATE_R)) {
				mymodel.rotate(00, intensity, 0);
			} else if (name.equals(MAPPING_ROTATE_L)) {
				// Vector3f v = mymodel.getLocalTranslation();
				mymodel.rotate(00, -intensity, 0);
			}

			
		}
	};
}
