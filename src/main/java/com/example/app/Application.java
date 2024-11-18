package com.example.app;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.app.model.Product;

@SpringBootApplication
public class Application {

	public static final String GET_All_DATA_ = "http://localhost:9092/api/v2/products" ;
	public static final String UPDATE_DATA_URl = "http://localhost:9092/api/v2/products/{id}" ;
	public static final String DELETE_DATA_URl = "http://localhost:9092/api/v2/products/{id}" ;
	public static final String POST_API_URL = "http://localhost:9092/api/v2/products" ;
	public static final String GET_DATA_URL_SINGLE = "http://localhost:9092/api/v2/products/{id}";
	public static final String GET_DATA_URl = "http://localhost:9092/api/v2/products/{id}" ;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		
		
		RestTemplate template = new RestTemplate();
		
	  ResponseEntity<Product []> entity = template.getForEntity(GET_All_DATA_, Product[].class);
	  
	  
	  Product[] pro = entity.getBody();
	  
	  
	  for(Product pro1 :pro)
	  { 
		  System.out.println(pro1);
	  }
	  
	  
	}

	private static void deleteMapping(RestTemplate template) {
		Map<String, Integer> deleteMap = new HashMap<String, Integer>();
		  
		  deleteMap.put("id", 5);
		  
		  Product pro = new Product(5, "ABC", 5375.0, "XYZ", "PQR", "PINK");
		  
		  template.put(UPDATE_DATA_URl, pro , deleteMap);
	}

	private static void delete(RestTemplate template) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		  
		  map.put("id" , 8);
		  
		  template.delete(DELETE_DATA_URl, map);
	}

	private static void getByEntity(RestTemplate template) {
		Product pro = new Product(22, "Ball", 4000.00, "SG", "Sports", "Red");
		  
		   ResponseEntity<Product> entity = template.postForEntity(POST_API_URL, pro , Product.class);
			
			System.out.println(entity.getBody());
			
			System.out.println("=========");
			System.out.println(entity.getHeaders());
			System.out.println(entity.getStatusCode());
			System.out.println(entity.getBody());
			System.out.println(entity.getStatusCodeValue());
	}

	private static void PostForObject(RestTemplate template) {
		Product pro = new Product(21, "Bat", 2000.00, "MRF", "SPORTS", "Brown");
		
		Product pro1 = template.postForObject(POST_API_URL, pro, Product.class);
		
		
		System.out.println(pro1);
	}

	private static void getForEntity(RestTemplate template) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		map.put("id", 8);
		
		
		
		ResponseEntity<Product> entity = template.getForEntity(GET_DATA_URL_SINGLE, Product.class, map);
		
		System.out.println(entity.getBody());
		System.out.println("==============");
		System.out.println(entity.getHeaders());
		System.out.println(entity.getStatusCode());
		System.out.println(entity.getStatusCodeValue());
	}

	private static void getForObject(RestTemplate template) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		map.put("id", 5);
		
		String msg = template.getForObject(GET_DATA_URl,String.class, map);
		
		System.out.println(msg);
	}

}
