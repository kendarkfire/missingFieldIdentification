package com.trellisconnect.missingFieldIdentification.service;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.trellisconnect.missingFieldIdentification.constants.InsuranceCompany;
import com.trellisconnect.missingFieldIdentification.domain.Policy;

@Service
public class RanchersFieldsAnalyzor implements FindMissingFields {

	@Override
	public boolean canHandle(InsuranceCompany insuranceCompany) {
		return insuranceCompany == InsuranceCompany.Ranchers;
	}

	@Override
	public ObjectNode findMissingFields(Policy policy) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode rootNode = mapper.createObjectNode();
		// TODO Implement findMissingFields for Ranchers
		return rootNode;
	}

}
