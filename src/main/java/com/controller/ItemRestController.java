package com.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.dao.ItemDao;
import com.model.Item;

import io.swagger.annotations.*;

@RestController
public class ItemRestController {
	
	@Autowired
	ItemDao idao;
	
	@GetMapping("/homeinfo")
	public String gethomeinfo() {
		return "Home for Itemrestcontroller!  Your api is working fine";
	}
	
	@ApiResponse(code= 200, message= "Item Sucessfully Added")
	@PostMapping("/additem")
	public ResponseEntity additem(@RequestBody Item item) {
		
		idao.save(item);
		
		return new ResponseEntity("item added" , HttpStatus.OK);
	}
	
	@ApiResponse(code= 200, message= "Items Sucessfully dispalyed")
	@GetMapping("/getallitem")
	public List<Item> getAllItem(){
		
		return idao.findAll();
	}
	
	@ApiResponse(code= 200, message= "Item Updated Sucessfully")
	@PatchMapping("/updateitem")
	public ResponseEntity updateItem(@RequestBody Item item) {
		
		 idao.save(item);
		 return new ResponseEntity("entity updated" , HttpStatus.OK);
	}
	
	@ApiResponse(code= 200, message= "Item Deleted Sucessfully")
	@DeleteMapping("/deleteitem")
	public ResponseEntity deleteItem(@RequestBody Item item) {
		
		idao.delete(item);
		return new ResponseEntity("item deleted" , HttpStatus.OK);
	}
	
	@ApiResponse(code= 200, message= "Item Sucessfully Dispayed using Id")
	@GetMapping("findbyid/{id}")
	public Item getItem(@PathVariable int id) {
		
		Item item=idao.findById(id).get();
		return item;
	}
	
	@GetMapping("/findbyprice/{price}")
	public Item getPrice(@PathVariable float price ) {
		
		
		return (idao.findByPrice(price));
	}

}
