package com.springboot.helper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.Entity.Product;

public class Helper {
	
	
	//This method check the Format is Excel or not...
	
	public static boolean checkExcelFormat(MultipartFile file) {
		String contentType=file.getContentType();
		
//		if(contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
//			return true;
//		}
//		else {
//			return false;
//		}
		
		return contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	}
	
	
	//This method will Convert Excel to List
	
	public static List<Product> ConvertExcelToListofProduct(InputStream is){
		
		List<Product> list=new ArrayList<>();
		
		try {
			
			   XSSFWorkbook workbook=new XSSFWorkbook(is);
			   
			   XSSFSheet sheet=workbook.getSheet("data");
			   
			   int rowNumber=0;
			   Iterator<Row> iterator= sheet.iterator();
			   
			   while(iterator.hasNext()) {
				   
				   Row row=iterator.next();
				   if(rowNumber==0) {
					   rowNumber++;
					   continue;
				   }
				   
				   Iterator<Cell> cells=row.iterator();
				   int cid=0;
				   
				   Product product=new Product();
				   while(cells.hasNext()) {
					   Cell cell=cells.next();
					   
					   switch(cid) {
					   
					   case 0:
						   product.setProductId((int)cell.getNumericCellValue());
						   break;
					   case 1:
						   product.setProductName(cell.getStringCellValue());
						   break;
					   case 2:
						   product.setProductDesc(cell.getStringCellValue());
						   break;
					   case 3:
						   product.setProductPrice((int)cell.getNumericCellValue());
						   break;
						default:
							break;
					   
					   }
					   cid++;
				   }
				   list.add(product);
			   }
			   
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
		
	}

}
