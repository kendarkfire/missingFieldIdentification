package com.trellisconnect.missingFieldIdentification.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class RanchersPolicy  extends Policy {

	public ObjectNode findMissingFields() {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode rootNode = mapper.createObjectNode();
		// TODO Implement findMissingFields for Ranchers
		return rootNode;
	}

}
