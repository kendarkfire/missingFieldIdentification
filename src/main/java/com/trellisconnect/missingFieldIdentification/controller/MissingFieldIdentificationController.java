package com.trellisconnect.missingFieldIdentification.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.trellisconnect.missingFieldIdentification.constants.InsuranceCompany;
import com.trellisconnect.missingFieldIdentification.domain.Policy;
import com.trellisconnect.missingFieldIdentification.service.FindMissingFields;

@RestController
@RequestMapping("/api")
public class MissingFieldIdentificationController {

	@Autowired
	private List<FindMissingFields> findMissingFields;

	public Optional<FindMissingFields> getFindMissingFieldsService(InsuranceCompany insuranceCompany) {
		return findMissingFields.stream().filter(service -> service.canHandle(insuranceCompany)).findFirst();
	}

	@PutMapping("/findMissingFields/colonel")
	public ResponseEntity<ObjectNode> findColonelMissingFields(@RequestBody Policy policy ) {
		Optional<FindMissingFields> fmf = getFindMissingFieldsService(InsuranceCompany.Colone);
		if(fmf.isEmpty()) {
			return ResponseEntity.badRequest().body(null);
		}else {
			return ResponseEntity.ok().body(fmf.get().findMissingFields(policy));
		}
	}
	
	@PutMapping("/findMissingFields/ranchers")
	public ResponseEntity<ObjectNode> findRanchersMissingFields(@RequestBody Policy policy ) {
		Optional<FindMissingFields> fmf = getFindMissingFieldsService(InsuranceCompany.Ranchers);
		if(fmf.isEmpty()) {
			return ResponseEntity.badRequest().body(null);
		}else {
			return ResponseEntity.ok().body(fmf.get().findMissingFields(policy));
		}
	}

}
