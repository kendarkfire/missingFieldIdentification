package com.trellisconnect.missingFieldIdentification.service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.trellisconnect.missingFieldIdentification.constants.InsuranceCompany;
import com.trellisconnect.missingFieldIdentification.domain.Policy;

public interface FindMissingFields {
	
	public boolean canHandle(InsuranceCompany insuranceCompany);
	
	public ObjectNode findMissingFields(Policy policy);

}
