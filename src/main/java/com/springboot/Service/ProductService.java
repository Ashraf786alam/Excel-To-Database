package com.springboot.Service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.Entity.Product;
import com.springboot.Repository.ProductRepository;
import com.springboot.helper.Helper;

@Service
public class ProductService {
	
	
	@Autowired
	private ProductRepository productRepo;
	
	public void save(MultipartFile file) {
		
		try {
			List<Product> products=Helper.ConvertExcelToListofProduct(file.getInputStream());
			this.productRepo.saveAll(products);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public List<Product> getAllProducts(){
		return this.productRepo.findAll();
	}
	
	

}
