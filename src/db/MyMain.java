package db;

import java.io.IOException;


public class MyMain {
public static void main(String args[]) throws IOException{
	MeasurementDB db = new MeasurementDB();
	db.importExcelDB("Measurements2.xlsx");
	//System.out.println("Size of imported ceser db "+db.getSize());
	db.printResult(db.queryGender(901,898,912,907,1752,1747,542,537,1252,1247,1352,1347,352,347));
	//System.out.println(db.toString());
	/*System.out.println("Max value for Stature: "+db.max_stature);
	System.out.println("Min value for Stature: "+db.min_stature);
	System.out.println("Max value for Trochenterien Height Left: "+db.max_troc_h_l);
	System.out.println("Min value for Trochenterien Height Left: "+db.min_troc_h_l);
	System.out.println("Max value for Trochenterien Height Right: "+db.max_troc_h_r);
	System.out.println("Min value for Trochenterien Height Right: "+db.min_troc_h_r);
	System.out.println("Max value for Acromian Height Stand Left: "+db.max_acro_h_l);
	System.out.println("Min value for Acromian Height Stand Left: "+db.min_acro_h_l);
	System.out.println("Max value for Acromian Height Stand Right: "+db.max_acro_h_r);
	System.out.println("Min value for Acromian Height Stand Right: "+db.min_acro_h_r);
	System.out.println("Min value for Acromian Knee Height: "+db.max_knee);
	System.out.println("Min value for Acromian Knee Height: "+db.min_knee);*/
	System.out.println("Min value for Max Bitroch: "+db.max_bitroch_Br_St);
	System.out.println("Min value for Min Bitroch: "+db.min_bitroch_Br_St);
	
	System.out.println("Height of Body "+ db.get_Bitroch_breadth(.5, 1.5, 400));
}
}
