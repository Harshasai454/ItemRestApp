package com.itemresrapp.itemrestapp;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.dao.ItemDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.model.Item;
import java.util.*;
@SpringBootTest
class ItemRestTest {

	@Autowired
	ItemDao itemDao;
	
	@Test
	void test() {
		Item item = new Item();
		item.setItemName("Coffee");
		item.setPrice(20);
		item.setQuantity(20);
		itemDao.save(item);
		
		Item item1 = itemDao.findById(item.getItemId()).get();
		Assertions.assertEquals(item.getItemName(), item1.getItemName());
	}
	@Test
	void testByPrice() {
		Item item = new Item();
		item.setItemName("Tea");
		item.setPrice(20);
		item.setQuantity(40);
		itemDao.save(item);
		
		Item item1 = itemDao.findByPrice(20);
		Assertions.assertEquals(item1.getItemName(),"Tea");
	}
	@Test
    void test1() throws URISyntaxException, JsonProcessingException {
      RestTemplate template=new RestTemplate();
      final String url="http://localhost:8080/findbyid/1";
      URI uri=new URI(url);
      
      ResponseEntity<String> res=template.getForEntity(uri,String.class);
      
      Assertions.assertEquals(HttpStatus.OK,res.getStatusCode());
      
  }
	/*
	@Test
	void testGetAllItems() throws URISyntaxException{
		Item i = new Item("Coffee",40,10);
		itemDao.save(i);
		RestTemplate template=new RestTemplate();
	      final String url="http://localhost:8080/getallitem";
	      URI uri=new URI(url);
	      ResponseEntity<List> res=template.getForEntity(uri,List.class);
	      System.out.println(res.getBody());
	      assertEquals(200, res.getStatusCodeValue());
	      assertEquals(i.getItemName(),((Item)(res.getBody().get(0))))
	      //assertEquals(true,res.getBody().contains(i));
	}
	
	/*@Test
	void testGetItem() {
		fail("Not yet implemented");
	}*/

}
