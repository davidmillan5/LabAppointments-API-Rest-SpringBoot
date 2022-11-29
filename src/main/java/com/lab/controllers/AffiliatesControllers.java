package com.lab.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lab.entity.Affiliates;

import com.lab.repository.AffiliatesRepository;


import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/controller/affiliates")
public class AffiliatesControllers {

	@Autowired
	private AffiliatesRepository affiliatesRepository;
	

	
	@PostMapping("/post/")
	public ResponseEntity<Affiliates> saveAffiliates(@Valid @RequestBody Affiliates affiliates){
		Affiliates affiliatessaved = affiliatesRepository.save(affiliates);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(affiliatessaved.getId()).toUri();
		return ResponseEntity.created(location).body(affiliatessaved);
	}
	
	@GetMapping("/getlist/")
	public List<Affiliates> lists(){
		return affiliatesRepository.findAll();
	}
	
	@PutMapping("/put/{id}")
	public ResponseEntity<Affiliates> updateAffiliate(@PathVariable Integer id, @Valid @RequestBody Affiliates affiliates){
		Optional<Affiliates> affiliatesOptional = affiliatesRepository.findById(id);
		
		if(!affiliatesOptional.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}
		
		affiliates.setId(affiliatesOptional.get().getId());
		affiliatesRepository.save(affiliates);
		
		return ResponseEntity.noContent().build();
		
	}
		
	
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Integer id) {
		affiliatesRepository.deleteById(id);
	}
	
	
	@GetMapping("/getbyid/{id}")
	public Optional<Affiliates> getbyid(@PathVariable Integer id) {
		return affiliatesRepository.findById(id);
	}
	
	
}
