package io;
/**
 * Author : Gulraiz Iqbal
 */

import java.io.FileOutputStream;
import java.nio.FloatBuffer;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.jme3.scene.Mesh;
import com.jme3.scene.VertexBuffer.Type;
import com.jme3.scene.mesh.IndexBuffer;
 
public class ExcelExporter {
 
    public ExcelExporter(String fileName,Mesh mesh ) throws Exception{
        Workbook workbook = null;
         
        FloatBuffer points = mesh.getFloatBuffer(Type.Position);
		FloatBuffer colors = mesh.getFloatBuffer(Type.Color);
		IndexBuffer faces = mesh.getIndexBuffer();
        
        if(fileName.endsWith("xlsx")){
            workbook = new XSSFWorkbook();
        }else if(fileName.endsWith("xls")){
            workbook = new HSSFWorkbook();
        }else{
            throw new Exception("invalid file name, should be xls or xlsx");
        }
         
        Sheet sheet = workbook.createSheet("Model Points");
        
         
        int rowIndex = 0;
        int sheetNumber = 1;
       
        int nbPoint = points.capacity() / 3;
		int nbFace = faces.size() / 3;
		
        int val = (int) nbPoint/3;
        for(int i=0; i<nbPoint; i+=3){
        	
        	if(val*sheetNumber < i){
        		
        		sheet = workbook.createSheet("Model Points" +sheetNumber);
        		rowIndex =0;
        		sheetNumber++;
        	}
        	
        	Row row = sheet.createRow(rowIndex++);
            
            Cell cell0 = row.createCell(0);
            cell0.setCellValue(points.get(i+0));
            
            Cell cell1 = row.createCell(1);
            cell1.setCellValue(points.get(i+1));
            
            Cell cell2 = row.createCell(2);
            cell2.setCellValue(points.get(i+2));
            
            Cell cell3 = row.createCell(3);
            cell3.setCellValue("");
            
            /*Set Color values*/
            Cell cell4 = row.createCell(4);
            cell4.setCellValue(colors.get(i+0));
            
            Cell cell5 = row.createCell(5);
            cell5.setCellValue(colors.get(i+1));
            
            Cell cell6 = row.createCell(6);
            cell6.setCellValue(colors.get(i+2));
            
            //Set faces
            Cell cell7 = row.createCell(7);
            cell7.setCellValue("");
            
            Cell cell8 = row.createCell(8);
            cell8.setCellValue(faces.get(i+0));
            
            Cell cell9 = row.createCell(9);
            cell9.setCellValue(faces.get(i+1));
            
            Cell cell10 = row.createCell(10);
            cell10.setCellValue(faces.get(i+2)); 
            
        }
         
        //lets write the excel data to file now
        FileOutputStream fos = new FileOutputStream(fileName);
        workbook.write(fos);
        fos.close();
        System.out.println(fileName + " written successfully");
    }
     
    
}