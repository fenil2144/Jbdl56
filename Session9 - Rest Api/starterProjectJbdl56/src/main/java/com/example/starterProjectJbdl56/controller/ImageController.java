package com.example.starterProjectJbdl56.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ImageController {
	
	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping(value = "/image", produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] getImage(@RequestParam("id") int id,
			@RequestParam(value = "length", required = false, defaultValue = "200") int length,
			@RequestParam(value="breadth") int breadth) {
		
		String url = "https://picsum.photos/id/"+id+"/"+length+"/"+breadth;
		System.out.println("In Image Controller: "+restTemplate);
		byte[] image = restTemplate.getForObject(url, byte[].class);
		return image;
		
	}
		

}
