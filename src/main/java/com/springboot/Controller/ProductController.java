package com.springboot.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.Entity.Product;
import com.springboot.Service.ProductService;
import com.springboot.helper.Helper;

@RestController
@CrossOrigin("*")
public class ProductController {
	
	
	@Autowired
	private ProductService productService;
	
	
	
	@PostMapping("/product/upload")
	public ResponseEntity<String> Upload(@RequestParam("file") MultipartFile file){
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(Helper.checkExcelFormat(file)) {
			this.productService.save(file);
			return ResponseEntity.ok("File Uploaded to DB Successfully");
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File Format is not Excel");
		}
		
	}
	
	@GetMapping("/product")
	public List<Product> getAllProduct(){
		return this.productService.getAllProducts();
	}

}
