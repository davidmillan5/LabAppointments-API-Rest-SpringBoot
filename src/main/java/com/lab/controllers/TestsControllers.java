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

import com.lab.entity.Tests;
import com.lab.repository.TestsRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/controller/tests")
public class TestsControllers {

	@Autowired
	private TestsRepository testsRepository;
	
	@PostMapping("/post/")
	public ResponseEntity<Tests> saveAffiliates(@Valid @RequestBody Tests tests){
		Tests testssaved = testsRepository.save(tests);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(testssaved.getId()).toUri();
		return ResponseEntity.created(location).body(testssaved);
	}
	
	@GetMapping("/getlist/")
	public List<Tests> lists(){
		return testsRepository.findAll();
	}
	
	
	@PutMapping("/put/{id}")
	public ResponseEntity<Tests> updateAffiliate(@PathVariable Integer id, @Valid @RequestBody Tests tests){
		Optional<Tests> testsOptional = testsRepository.findById(id);
		
		if(!testsOptional.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}
		
		tests.setId(testsOptional.get().getId());
		testsRepository.save(tests);
		
		return ResponseEntity.noContent().build();
		
	}
	
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Integer id) {
		testsRepository.deleteById(id);
	}
	
	@GetMapping("/getbyid/{id}")
	public Optional<Tests> getbyid(@PathVariable Integer id) {
		return testsRepository.findById(id);
	}
	
}
