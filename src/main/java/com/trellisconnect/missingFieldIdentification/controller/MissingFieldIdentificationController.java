package com.trellisconnect.missingFieldIdentification.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.trellisconnect.missingFieldIdentification.domain.ColonePolicy;
import com.trellisconnect.missingFieldIdentification.domain.RanchersPolicy;

@RestController
@RequestMapping("/api")
public class MissingFieldIdentificationController {
 

	@PutMapping("/findMissingFields/colonel")
	public ResponseEntity<ObjectNode> findColonelMissingFields(@RequestBody ColonePolicy policy ) {
		return ResponseEntity.ok().body( policy.findMissingFields());
	}
	
	@PutMapping("/findMissingFields/ranchers")
	public ResponseEntity<ObjectNode> findRanchersMissingFields(@RequestBody RanchersPolicy policy ) {
		return ResponseEntity.ok().body( policy.findMissingFields());
	}

}
