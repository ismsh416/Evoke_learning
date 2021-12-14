package com.Covid.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Covid.CovidReportApplication;
import com.Covid.exception.IdNotFoundException;
import com.Covid.model.Covid;
import com.Covid.repository.CovidRepository;

@RestController
@RequestMapping("/api/v1/")

public class CovidController {

	Logger log = LoggerFactory.getLogger(CovidReportApplication.class);
	@Autowired
	private CovidRepository covidRepository;

	// Get Report
	@GetMapping("/report")
	public List<Covid> getReport() {
		log.debug("Request :");
		List<Covid> resp = this.covidRepository.findAll();
		log.debug("Response :", resp);
		return resp;
	}

	// Get report by ID
	@GetMapping("/report/{id}")
	public ResponseEntity<Covid> getReportById(@PathVariable(value = "id") Long stateID) throws IdNotFoundException {
		log.debug("Request by id"+stateID);
		Covid cd = covidRepository.findById(stateID)
				.orElseThrow(() -> new IdNotFoundException("state ID not found :" + stateID));

		log.debug("Response ",log);
		return ResponseEntity.ok().body(cd);

	}

	// Save Report
	@PostMapping("/report")
	public Covid createState(@RequestBody Covid covid) {
		return this.covidRepository.save(covid);
	}

	@PutMapping("/report/{id}")
	public ResponseEntity<Covid> UpdateState(@PathVariable(value = "id") Long stateID,
			@Validated @RequestBody Covid covidDetails) throws IdNotFoundException {

		Covid cd = covidRepository.findById(stateID)
				.orElseThrow(() -> new IdNotFoundException("state ID not found :" + stateID));
		cd.setCases(covidDetails.getCases());
		cd.setState(covidDetails.getState());
		return ResponseEntity.ok(this.covidRepository.save(cd));

	}

	@DeleteMapping("/report/{id}")
	public Map<String, Boolean> DeleteState(@PathVariable(value = "id") Long stateID) throws IdNotFoundException {
		Covid cd = covidRepository.findById(stateID)
				.orElseThrow(() -> new IdNotFoundException("state ID not found :" + stateID));
		this.covidRepository.delete(cd);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
