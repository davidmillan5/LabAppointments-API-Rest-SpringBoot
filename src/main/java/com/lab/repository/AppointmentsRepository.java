package com.lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lab.entity.Appointments;

public interface AppointmentsRepository extends JpaRepository<Appointments,Integer> {

	
}
