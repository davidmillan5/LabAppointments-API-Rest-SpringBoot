package com.lab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lab.repository.AffiliatesRepository;
import com.lab.repository.AppointmentsRepository;
import com.lab.repository.TestsRepository;

@RestController
@RequestMapping("/api/controller/appointments")
public class AppointmentsControllers {

	@Autowired
	private TestsRepository testsRepository;
	
	@Autowired
	private AffiliatesRepository affiliatesRepository;
	
	@Autowired
	private AppointmentsRepository appointmentsRepository;
	
	
	
	
	
	
	
	
}
