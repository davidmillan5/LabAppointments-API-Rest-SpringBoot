package com.lab.repository;

import java.util.List;
import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lab.entity.Appointments;

public interface AppointmentsRepository extends JpaRepository<Appointments,Integer> {

	
	
}
