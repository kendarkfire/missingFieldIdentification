package com.trellisconnect.missingFieldIdentification.service;

import java.util.ArrayList;
import java.util.Optional;

import org.apache.commons.validator.GenericValidator;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.BooleanNode;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.trellisconnect.missingFieldIdentification.constants.Constants;
import com.trellisconnect.missingFieldIdentification.constants.InsuranceCompany;
import com.trellisconnect.missingFieldIdentification.domain.Address;
import com.trellisconnect.missingFieldIdentification.domain.Name;
import com.trellisconnect.missingFieldIdentification.domain.Operator;
import com.trellisconnect.missingFieldIdentification.domain.Policy;
import com.trellisconnect.missingFieldIdentification.domain.PolicyHolder;

@Service
public class ColoneFieldsAnalyzor implements FindMissingFields {

	@Override
	public boolean canHandle(InsuranceCompany insuranceCompany) {
		return insuranceCompany == InsuranceCompany.Colone;
	}

	@Override
	public ObjectNode findMissingFields(Policy policy) {
		Optional<Policy> opPolicy = Optional.ofNullable(policy);
		Optional<PolicyHolder> opPolicyHolder = opPolicy.flatMap(Policy::getPolicyHolder);
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode rootNode = mapper.createObjectNode();
		ObjectNode policyHolderNode = mapper.createObjectNode();
		opPolicyHolder.ifPresentOrElse(op -> {
			op.getAddress().ifPresentOrElse(address -> checkAndSetAdressFields(mapper, policyHolderNode, address),
					() -> setPolicyHolderAllAddressFields(mapper, policyHolderNode));
			op.getName().ifPresentOrElse(name -> checkAndSetNameFields(mapper, policyHolderNode, name),
					() -> setPolicyHolderAllNameFields(mapper, policyHolderNode));
			if (ObjectUtils.isEmpty(op.getEmail())) {
				policyHolderNode.set("email", NullNode.instance);
			}
		}, () -> setPolicyHolderAllStandardFields(mapper, policyHolderNode));

		if (!policyHolderNode.isEmpty()) {
			rootNode.set("policyHolder", policyHolderNode);
		}
		ArrayNode operatorNodes = mapper.createArrayNode();
		Optional<ArrayList<Operator>> opOperator = opPolicy.flatMap(Policy::getOperators);
		opOperator.ifPresentOrElse(ops -> ops.forEach(op -> checkAndSetOperatorFields(mapper, operatorNodes, op)),
				() -> setDefaultOperatorFields(mapper, operatorNodes));
		if (!operatorNodes.isEmpty()) {
			rootNode.set("operators", operatorNodes);
		}
		return rootNode;
	}

	private void setDefaultOperatorFields(ObjectMapper mapper, ArrayNode operatorNodes) {
		ObjectNode opNode = mapper.createObjectNode();
		opNode.set("isPrimary", BooleanNode.TRUE);
		opNode.set("driversLicenseNumber", NullNode.instance);
		opNode.set("gender", NullNode.instance);
		setOperatorBirthDayRangeAllFields(mapper, opNode);
		operatorNodes.add(opNode);
	}

	private void checkAndSetOperatorFields(ObjectMapper mapper, ArrayNode operatorNodes, Operator op) {
		ObjectNode opNode = mapper.createObjectNode();
		if (op.getIsPrimary() && !validateDriverLicense(op.getDriversLicenseNumber())) {
			opNode.set("isPrimary", BooleanNode.TRUE);
			opNode.set("driversLicenseNumber", new TextNode(op.getDriversLicenseNumber()));
		}
		if (ObjectUtils.isEmpty(op.getGender())) {
			opNode.set("gender", NullNode.instance);
		}
		op.getBirthdayRange().ifPresentOrElse(bd -> {
			ObjectNode birthdayRangeNode = mapper.createObjectNode();
			if (!GenericValidator.isDate(bd.getStart(), Constants.DATE_FORMAT, true)) {
				birthdayRangeNode.set("start", new TextNode(bd.getStart()));
			}
			if (!GenericValidator.isDate(bd.getEnd(), Constants.DATE_FORMAT, true)) {
				birthdayRangeNode.set("end", new TextNode(bd.getEnd()));
			}
			if (!birthdayRangeNode.isEmpty()) {
				opNode.set("birthdayRange", birthdayRangeNode);
			}
		}, () -> {
			setOperatorBirthDayRangeAllFields(mapper, opNode);
		});
		if (!opNode.isEmpty()) {
			operatorNodes.add(opNode);
		}
	}

	private void setOperatorBirthDayRangeAllFields(ObjectMapper mapper, ObjectNode opNode) {
		ObjectNode birthDayRangeNode = mapper.createObjectNode();
		birthDayRangeNode.set("start", NullNode.instance);
		birthDayRangeNode.set("end", NullNode.instance);
		opNode.set("birthdayRange", birthDayRangeNode);
	}

	private void checkAndSetNameFields(ObjectMapper mapper, ObjectNode policyHolderNode, Name name) {
		ObjectNode objName = mapper.createObjectNode();
		if (ObjectUtils.isEmpty(name.getFirstName())) {
			objName.set("firstName", NullNode.instance);
		}
		if (ObjectUtils.isEmpty(name.getLastName())) {
			objName.set("lastName", NullNode.instance);
		}
		if (!objName.isEmpty()) {
			policyHolderNode.set("name", objName);
		}
	}

	private void checkAndSetAdressFields(ObjectMapper mapper, ObjectNode policyHolderNode, Address address) {
		ObjectNode objAddress = mapper.createObjectNode();
		if (ObjectUtils.isEmpty(address.getNumber())) {
			objAddress.set("number", NullNode.instance);
		}
		if (ObjectUtils.isEmpty(address.getStreet())) {
			objAddress.set("street", NullNode.instance);
		}
		if (ObjectUtils.isEmpty(address.getCity())) {
			objAddress.set("city", NullNode.instance);
		}
		if (ObjectUtils.isEmpty(address.getState())) {
			objAddress.set("state", NullNode.instance);
		}
		if (ObjectUtils.isEmpty(address.getZip())) {
			objAddress.set("zip", NullNode.instance);
		}
		if (!objAddress.isEmpty()) {
			policyHolderNode.set("address", objAddress);
		}
	}

	private void setPolicyHolderAllNameFields(ObjectMapper mapper, ObjectNode policyHolderNode) {
		ObjectNode objName = mapper.createObjectNode();
		objName.set("firstName", NullNode.instance);
		objName.set("lastName", NullNode.instance);
		policyHolderNode.set("name", objName);
	}

	private void setPolicyHolderAllAddressFields(ObjectMapper mapper, ObjectNode policyHolderNode) {
		ObjectNode objAddress = mapper.createObjectNode();
		objAddress.set("number", NullNode.instance);
		objAddress.set("street", NullNode.instance);
		objAddress.set("city", NullNode.instance);
		objAddress.set("state", NullNode.instance);
		objAddress.set("zip", NullNode.instance);
		policyHolderNode.set("address", objAddress);
	}

	private void setPolicyHolderAllStandardFields(ObjectMapper mapper, ObjectNode policyHolderNode) {
		setPolicyHolderAllNameFields(mapper, policyHolderNode);
		setPolicyHolderAllAddressFields(mapper, policyHolderNode);
		policyHolderNode.set("email", null);
	}

	private boolean validateDriverLicense(String driverLicense) {
		boolean isVaild = false;
		if (driverLicense != null) {
			// TODO Implement driver license validation
			if (driverLicense.indexOf("XXXX") > -1) {
				isVaild = false;
			} else {
				isVaild = true;
			}
		}
		return isVaild;
	}

}
