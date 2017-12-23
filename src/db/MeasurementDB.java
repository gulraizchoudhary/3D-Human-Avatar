package db;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class MeasurementDB {

	private ArrayList<Body> BodiesList;
	public double max_stature;
	public double min_stature;
	public double max_troc_h_l;
	public double min_troc_h_l;
	public double max_troc_h_r;
	public double min_troc_h_r;
	public double max_acro_h_l;
	public double min_acro_h_l;
	public double max_acro_h_r;
	public double min_acro_h_r;
	public double max_bi_cr;
	public double min_bi_cr;
	public double max_knee;
	public double min_knee;
	public double min_bitroch_Br_St;
	public double max_bitroch_Br_St;
	public int scale_max;
	public int scale_min;
	
	
	public MeasurementDB() {
		BodiesList = new ArrayList<Body>();
		this.max_stature = 0.0;
		this.min_stature = 0.0;
		this.max_troc_h_l = 0.0;
		this.min_troc_h_l = 0.0;
		this.max_troc_h_r = 0.0;
		this.min_troc_h_r = 0.0;
		this.max_acro_h_l = 0.0;
		this.min_acro_h_l = 0.0;
		this.max_acro_h_r = 0.0;
		this.min_acro_h_r = 0.0;
		this.max_bi_cr = 0.0;
		this.min_bi_cr = 0.0;
		this.max_knee = 0.0;
		this.min_knee = 0.0;
		this.min_bitroch_Br_St=0;
		this.max_bitroch_Br_St=0;
		this.scale_max = 2;
		this.scale_min = -2;
	}

	public ArrayList<Body> getDB() {
		return BodiesList;
	}

	public ArrayList<Body> queryDB() {
		return BodiesList;
	}

	public int getSize() {
		return BodiesList.size();
	}

	public void importExcelDB(String fileName) throws IOException {

		FileInputStream fis = new FileInputStream(new File(fileName));
		XSSFWorkbook wb = new XSSFWorkbook(fis);

		XSSFSheet sheet = wb.getSheetAt(0);
		
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);

			BodiesList.add(new Body(row.getCell(0).getNumericCellValue(), row
					.getCell(1).getStringCellValue(), row.getCell(4)
					.getNumericCellValue(), row.getCell(5)
					.getNumericCellValue(), row.getCell(6)
					.getNumericCellValue(), row.getCell(7)
					.getNumericCellValue(), row.getCell(8)
					.getNumericCellValue(), row.getCell(9)
					.getNumericCellValue(), row.getCell(10)
					.getNumericCellValue(), row.getCell(11)
					.getNumericCellValue(), row.getCell(12)
					.getNumericCellValue(), row.getCell(13)
					.getNumericCellValue(), row.getCell(14)
					.getNumericCellValue(), row.getCell(15)
					.getNumericCellValue(), row.getCell(16)
					.getNumericCellValue(), row.getCell(17)
					.getNumericCellValue(), row.getCell(18)
					.getNumericCellValue(), row.getCell(19)
					.getNumericCellValue(), row.getCell(20)
					.getNumericCellValue(), row.getCell(21)
					.getNumericCellValue(), row.getCell(22)
					.getNumericCellValue(), row.getCell(23)
					.getNumericCellValue(), row.getCell(24)
					.getNumericCellValue(), row.getCell(25)
					.getNumericCellValue(), row.getCell(26)
					.getNumericCellValue(), row.getCell(27)
					.getNumericCellValue(), row.getCell(28)
					.getNumericCellValue(), row.getCell(29)
					.getNumericCellValue(), row.getCell(30)
					.getNumericCellValue(), row.getCell(31)
					.getNumericCellValue(), row.getCell(32)
					.getNumericCellValue(), row.getCell(33)
					.getNumericCellValue(), row.getCell(34)
					.getNumericCellValue(), row.getCell(35)
					.getNumericCellValue(), row.getCell(36)
					.getNumericCellValue(), row.getCell(37)
					.getNumericCellValue(), row.getCell(38)
					.getNumericCellValue(), row.getCell(39)
					.getNumericCellValue(), row.getCell(40)
					.getNumericCellValue(), row.getCell(41)
					.getNumericCellValue(), row.getCell(42)
					.getNumericCellValue(), row.getCell(43)
					.getNumericCellValue(), row.getCell(44)
					.getNumericCellValue(), row.getCell(45)
					.getNumericCellValue(), row.getCell(46)
					.getNumericCellValue(), row.getCell(47)
					.getNumericCellValue(), row.getCell(48)
					.getNumericCellValue(), row.getCell(49)
					.getNumericCellValue(), row.getCell(50)
					.getNumericCellValue(), row.getCell(51)
					.getNumericCellValue(), row.getCell(52)
					.getNumericCellValue(), row.getCell(53)
					.getNumericCellValue(), row.getCell(54)
					.getNumericCellValue()/0.039370));
			
			//Assign first values to Max and Min variables for all the measurements
			if(i == 1){
				max_stature = row.getCell(34).getNumericCellValue();
				min_stature = row.getCell(34).getNumericCellValue();
				max_troc_h_l = row.getCell(49).getNumericCellValue();
				min_troc_h_l = row.getCell(49).getNumericCellValue();
				max_troc_h_r = row.getCell(50).getNumericCellValue();
				min_troc_h_r = row.getCell(50).getNumericCellValue();
				max_acro_h_l = row.getCell(51).getNumericCellValue();
				min_acro_h_l = row.getCell(51).getNumericCellValue();
				max_acro_h_r = row.getCell(52).getNumericCellValue();
				min_acro_h_r = row.getCell(52).getNumericCellValue();
				max_knee = row.getCell(30).getNumericCellValue();
				min_knee = row.getCell(30).getNumericCellValue();
				max_bi_cr = row.getCell(53).getNumericCellValue();
				min_bi_cr = row.getCell(53).getNumericCellValue();
				this.min_bitroch_Br_St = row.getCell(54).getNumericCellValue()/0.039370;
				this.max_bitroch_Br_St = row.getCell(54).getNumericCellValue()/0.039370;
			}

			//Calculating Max and Min values for stature
			if(row.getCell(34).getNumericCellValue() > this.max_stature)
				this.max_stature = row.getCell(34).getNumericCellValue(); 
			
			else if(row.getCell(34).getNumericCellValue() < this.min_stature && row.getCell(34).getNumericCellValue() > 0)
				this.min_stature = row.getCell(34).getNumericCellValue();
			
			//Calculating Max and Min values for Trochenterien Height Left
			if(row.getCell(49).getNumericCellValue() > this.max_troc_h_l)
				this.max_troc_h_l = row.getCell(49).getNumericCellValue(); 
			
			else if(row.getCell(49).getNumericCellValue() < this.min_troc_h_l && row.getCell(49).getNumericCellValue() > 0)
				this.min_troc_h_l = row.getCell(49).getNumericCellValue();
			
			//Calculating Max and Min values for Trochenterien Height Right
			if(row.getCell(50).getNumericCellValue() > this.max_troc_h_r)
				this.max_troc_h_r = row.getCell(50).getNumericCellValue(); 
			
			else if(row.getCell(50).getNumericCellValue() < this.min_troc_h_r && row.getCell(50).getNumericCellValue() > 0)
				this.min_troc_h_r = row.getCell(50).getNumericCellValue();
			
			//Calculating Max and Min values for Acromian Height Stand Left
			if(row.getCell(51).getNumericCellValue() > this.max_acro_h_l)
				this.max_acro_h_l = row.getCell(51).getNumericCellValue(); 
			
			else if(row.getCell(51).getNumericCellValue() < this.min_acro_h_l && row.getCell(51).getNumericCellValue() > 0)
				this.min_acro_h_l = row.getCell(51).getNumericCellValue();
			
			//Calculating Max and Min values for Acromian Height Stand Right
			if(row.getCell(52).getNumericCellValue() > this.max_acro_h_r)
				this.max_acro_h_r = row.getCell(52).getNumericCellValue(); 
			
			else if(row.getCell(52).getNumericCellValue() < this.min_acro_h_r && row.getCell(52).getNumericCellValue() > 0)
				this.min_acro_h_r = row.getCell(52).getNumericCellValue();
						
			//Calculating Max and Min values for Knee Height
			if(row.getCell(30).getNumericCellValue() > this.max_knee)
				this.max_knee = row.getCell(30).getNumericCellValue(); 
			
			else if(row.getCell(30).getNumericCellValue() < this.min_knee && row.getCell(30).getNumericCellValue() > 0)
				this.min_knee = row.getCell(30).getNumericCellValue();
			
			//Calculating Max and Min values for Bi-Crystal
			if(row.getCell(53).getNumericCellValue() > this.max_bi_cr)
				this.max_bi_cr = row.getCell(53).getNumericCellValue(); 
			
			else if(row.getCell(53).getNumericCellValue() < this.min_bi_cr && row.getCell(53).getNumericCellValue() > 0)
				this.min_bi_cr = row.getCell(53).getNumericCellValue(); 
			
			//Min and Max Bitrochanteric Breadth Standing (mm) 
			if((row.getCell(54).getNumericCellValue() /0.039370)> this.max_stature)
				this.max_bitroch_Br_St = row.getCell(54).getNumericCellValue()/0.039370; 
			
			else if((row.getCell(54).getNumericCellValue()/0.039370) < this.min_stature && row.getCell(54).getNumericCellValue() > 0)
				this.min_bitroch_Br_St = row.getCell(54).getNumericCellValue()/0.039370;
		}
		((Closeable) wb).close();
	}

	public ArrayList<Body> queryGender( double maxTroc_lt, double minTroc_lt, 
			double maxTroc_rt, double minTroc_rt, double maxStature, double minStature,
			double maxKnee_ht, double minKnee_ht, double maxAcro_lt, double minAcro_lt,
			double maxAcro_rt, double minAcro_rt, double maxBis_b, double minBis_b) {
		ArrayList<Body> result = new ArrayList<Body>();
		int rec_count=0;
		for (int i = 0; i < BodiesList.size(); i++) {
			if (BodiesList.get(i).trochanterion_ht_lt < maxTroc_lt && 
					BodiesList.get(i).trochanterion_ht_lt > minTroc_lt &&
					BodiesList.get(i).trochanterion_ht_rt < maxTroc_rt && 
					BodiesList.get(i).trochanterion_ht_rt > minTroc_rt &&		
					BodiesList.get(i).stature < maxStature /*&&
					BodiesList.get(i).stature > minStature &&
					BodiesList.get(i).knee_height < maxKnee_ht &&
					BodiesList.get(i).knee_height > minKnee_ht &&
					BodiesList.get(i).acromial_ht_stand_lt < maxAcro_lt &&
					BodiesList.get(i).acromial_ht_stand_lt > minAcro_lt &&
					BodiesList.get(i).acromial_ht_stand_rt < maxAcro_rt &&
					BodiesList.get(i).acromial_ht_stand_rt > minAcro_rt &&
					BodiesList.get(i).bicristale_brth < maxBis_b &&
					BodiesList.get(i).bicristale_brth > minBis_b*/){
				result.add(BodiesList.get(i));
				rec_count++;
				}
			
		}
		System.out.println("No of records found: "+rec_count);
		return result;
	}

	public void printResult(ArrayList<Body> body) {
		for (int i = 0; i < body.size(); i++)
			System.out
					.println("Record No: "
							+ body.get(i).subject_number
							+ ","
							+ body.get(i).gender
							+ ","
							+ body.get(i).acromial_height_sitting
							+ ","
							+ body.get(i).ankle_circumfrence
							+ ","
							+ body.get(i).spine_to_shoulder
							+ ","
							+ body.get(i).spine_to_elbow
							+ ","
							+ body.get(i).arm_length_spine_to_wrist
							+ ","
							+ body.get(i).arm_length_shoulder_to_wrist
							+ ","
							+ body.get(i).arm_length_shoulder_to_elbow
							+ ","
							+ body.get(i).armscye_circumference_scye_circ_over_acromion
							+ ","
							+ body.get(i).bizygomatic_breadth
							+ ","
							+ body.get(i).chest_circumference
							+ ","
							+ body.get(i).cust_chest_circumference_under_bust
							+ ","
							+ body.get(i).buttock_knee_length
							+ ","
							+ body.get(i).chest_girth_at_scye_chest_circumference_at_scye
							+ "," + body.get(i).crotch_height + ","
							+ body.get(i).elbow_height_sitting + ","
							+ body.get(i).eye_height_sitting + ","
							+ body.get(i).face_length + ","
							+ body.get(i).foot_lenght + ","
							+ body.get(i).hand_circumference + ","
							+ body.get(i).hand_length + ","
							+ body.get(i).head_breadth + ","
							+ body.get(i).head_circumference + ","
							+ body.get(i).head_length + ","
							+ body.get(i).hip_breadth_sitting + ","
							+ body.get(i).hip_circumference_maximum + ","
							+ body.get(i).hip_circ_max_height + ","
							+ body.get(i).knee_height + ","
							+ body.get(i).neck_base_circumference + ","
							+ body.get(i).shoulder_breadth + ","
							+ body.get(i).sitting_height + ","
							+ body.get(i).stature + ","
							+ body.get(i).subscapular_skinfold + ","
							+ body.get(i).thigh_circumference + ","
							+ body.get(i).thigh_circumference_max_sitting + ","
							+ body.get(i).thumb_tip_reach + ","
							+ body.get(i).ttr1 + "," + body.get(i).ttr2 + ","
							+ body.get(i).ttr3 + ","
							+ body.get(i).triceps_skinfold + ","
							+ body.get(i).total_crotch_length + ","
							+ body.get(i).vertical_trunk_circumference + ","
							+ body.get(i).waist_circumference + ","
							+ body.get(i).waist_front_length + ","
							+ body.get(i).waist_height_preferred + ","
							+ body.get(i).weight + ","
							+ body.get(i).trochanterion_ht_lt + ","
							+ body.get(i).trochanterion_ht_rt + ","
							+ body.get(i).acromial_ht_stand_lt+ ","
							+ body.get(i).acromial_ht_stand_rt+ ","
							+ body.get(i).bicristale_brth+ ","
							+ body.get(i).bitroch_Br_St + "\n");
	}

	public String toString() {
		String rs = "";
		for (int i = 0; i < BodiesList.size(); i++)
			rs += "Record No: "
					+ BodiesList.get(i).subject_number
					+ ","
					+ BodiesList.get(i).gender
					+ ","
					+ BodiesList.get(i).acromial_height_sitting
					+ ","
					+ BodiesList.get(i).ankle_circumfrence
					+ ","
					+ BodiesList.get(i).spine_to_shoulder
					+ ","
					+ BodiesList.get(i).spine_to_elbow
					+ ","
					+ BodiesList.get(i).arm_length_spine_to_wrist
					+ ","
					+ BodiesList.get(i).arm_length_shoulder_to_wrist
					+ ","
					+ BodiesList.get(i).arm_length_shoulder_to_elbow
					+ ","
					+ BodiesList.get(i).armscye_circumference_scye_circ_over_acromion
					+ ","
					+ BodiesList.get(i).bizygomatic_breadth
					+ ","
					+ BodiesList.get(i).chest_circumference
					+ ","
					+ BodiesList.get(i).cust_chest_circumference_under_bust
					+ ","
					+ BodiesList.get(i).buttock_knee_length
					+ ","
					+ BodiesList.get(i).chest_girth_at_scye_chest_circumference_at_scye
					+ "," + BodiesList.get(i).crotch_height + ","
					+ BodiesList.get(i).elbow_height_sitting + ","
					+ BodiesList.get(i).eye_height_sitting + ","
					+ BodiesList.get(i).face_length + ","
					+ BodiesList.get(i).foot_lenght + ","
					+ BodiesList.get(i).hand_circumference + ","
					+ BodiesList.get(i).hand_length + ","
					+ BodiesList.get(i).head_breadth + ","
					+ BodiesList.get(i).head_circumference + ","
					+ BodiesList.get(i).head_length + ","
					+ BodiesList.get(i).hip_breadth_sitting + ","
					+ BodiesList.get(i).hip_circumference_maximum + ","
					+ BodiesList.get(i).hip_circ_max_height + ","
					+ BodiesList.get(i).knee_height + ","
					+ BodiesList.get(i).neck_base_circumference + ","
					+ BodiesList.get(i).shoulder_breadth + ","
					+ BodiesList.get(i).sitting_height + ","
					+ BodiesList.get(i).stature + ","
					+ BodiesList.get(i).subscapular_skinfold + ","
					+ BodiesList.get(i).thigh_circumference + ","
					+ BodiesList.get(i).thigh_circumference_max_sitting + ","
					+ BodiesList.get(i).thumb_tip_reach + ","
					+ BodiesList.get(i).ttr1 + "," + BodiesList.get(i).ttr2
					+ "," + BodiesList.get(i).ttr3 + ","
					+ BodiesList.get(i).triceps_skinfold + ","
					+ BodiesList.get(i).total_crotch_length + ","
					+ BodiesList.get(i).vertical_trunk_circumference + ","
					+ BodiesList.get(i).waist_circumference + ","
					+ BodiesList.get(i).waist_front_length + ","
					+ BodiesList.get(i).waist_height_preferred + ","
					+ BodiesList.get(i).weight
					+ BodiesList.get(i).trochanterion_ht_lt + ","
					+ BodiesList.get(i).trochanterion_ht_rt + ","
					+ BodiesList.get(i).acromial_ht_stand_lt+ ","
					+ BodiesList.get(i).acromial_ht_stand_rt+ ","
					+ BodiesList.get(i).bicristale_brth +"\n";
		return rs;
	}

	/*
	 * Scaling formula A = min value B = max value a = Scale Min value b = Scale
	 * Max value
	 * 
	 * a + (x-A)(b-a)/(B-A)
	 */
	
	//Acromial HT RT (In standing material xl file)
	public float get_acro_height(double a, double b, double val){
		return (float) (a+(val-min_acro_h_r)*(b-a)/(max_acro_h_r-min_acro_h_r));
	}
	
	//Full human body height
	public float get_Stature_height(double a, double b, double val){
		return (float) (a+(val-min_stature)*(b-a)/(max_stature-min_stature));
	}
	
	/*Inverse of above formula to extract X value
		x= (res-a)(B-A)/(b-a) + A 
	 */
	public double get_Stature_height_org(double a, double b, double res){
		
		return  (((res-a)*(max_stature-min_stature))/(b-a))+min_stature;
	}
	
	
	// Biiliocristal distance between the most lateral points on the iliac crests

	public float get_BiCristal_breadth(double a, double b, double val) {
		return (float) (a + (val - this.min_bi_cr) * (b - a) / (max_bi_cr - min_bi_cr));
	}

	/*
	 * Inverse of above formula to extract X value x= (res-a)(B-A)/(b-a) + A
	 */
	public double get_BiCristal_breadth_org(double a, double b, double res) {

		return (((res - a) * (max_bi_cr - min_bi_cr)) / (b - a)) + min_bi_cr;
	}
	
	
	// Bitrochanteric Breadth Standing (mm) (In standing material xl file)


		public float get_Bitroch_breadth(double a, double b, double val) {
			return (float) (a + (val - this.min_bitroch_Br_St) * (b - a) / (max_bitroch_Br_St - min_bitroch_Br_St));
		}

		/*
		 * Inverse of above formula to extract X value x= (res-a)(B-A)/(b-a) + A
		 */
		public double get_Bitroch_breadth_org(double a, double b, double res) {

			return (((res - a) * (max_bitroch_Br_St - min_bitroch_Br_St)) / (b - a)) + min_bitroch_Br_St;
		}
}
