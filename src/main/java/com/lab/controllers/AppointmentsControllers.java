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
import com.lab.entity.Appointments;
import com.lab.entity.Tests;
import com.lab.repository.AffiliatesRepository;
import com.lab.repository.AppointmentsRepository;
import com.lab.repository.TestsRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/controller/appointments")
public class AppointmentsControllers {

	@Autowired
	private TestsRepository testsRepository;
	
	@Autowired
	private AffiliatesRepository affiliatesRepository;
	
	@Autowired
	private AppointmentsRepository appointmentsRepository;
	
	
	@PostMapping("/post/")
	public ResponseEntity<Appointments> saveAppointments(@Valid @RequestBody Appointments appointments){
		Optional<Affiliates> affiliatesOptional = affiliatesRepository.findById(appointments.getAffiliates().getId());
		Optional<Tests> testsOptional = testsRepository.findById(appointments.getTests().getId());
		
		if(!affiliatesOptional.isPresent() && !testsOptional.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}
		
		appointments.setAffiliates(affiliatesOptional.get());
		appointments.setTests(testsOptional.get());
		
		Appointments saveAppointments = appointmentsRepository.save(appointments);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(saveAppointments.getId()).toUri();
		
		return ResponseEntity.created(location).body(saveAppointments);
		
	}
	
	
	@GetMapping("/getlist/")
	public List<Appointments> lists(){
		return appointmentsRepository.findAll();
	}
	
	
	
	
	@PutMapping("/put/{id}")
	public ResponseEntity<Appointments> updateApointments(@Valid @RequestBody Appointments appointments,@PathVariable Integer id){
		Optional<Affiliates> affiliatesOptional = affiliatesRepository.findById(appointments.getAffiliates().getId());
		Optional<Tests> testsOptional = testsRepository.findById(appointments.getTests().getId());
		
		
		if(!affiliatesOptional.isPresent() && !testsOptional.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}
		
		Optional<Appointments> appointmentsOptional = appointmentsRepository.findById(id);
		
		
		if(!appointmentsOptional.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}
		
		appointments.setAffiliates(affiliatesOptional.get());
		appointments.setTests(testsOptional.get());
		
		appointments.setId(affiliatesOptional.get().getId());
		appointments.setId(testsOptional.get().getId());
		
		appointmentsRepository.save(appointments);
		
		return ResponseEntity.noContent().build();
		
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Integer id) {
		appointmentsRepository.deleteById(id);
	}
	
	@GetMapping("/getbyid/{id}")
	public Optional<Appointments> getbyid(@PathVariable Integer id) {
		return appointmentsRepository.findById(id);
	}
	
	@GetMapping("/getbyaffiliates/{id}")
	public Optional<Affiliates> getbyidAffiliates(@PathVariable Integer id) {
		return affiliatesRepository.findById(id);
	}
	
	@GetMapping("/getbydate/{date}")
	
	
}
