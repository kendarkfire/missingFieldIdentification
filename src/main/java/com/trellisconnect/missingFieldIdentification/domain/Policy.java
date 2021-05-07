package com.trellisconnect.missingFieldIdentification.domain;

import java.util.ArrayList;
import java.util.Optional;

public class Policy {
	private String policyId;
	private String issuer;
	private String issueDate;
	private String renewalDate;
	private int policyTermMonths;
	private long premiumCents;
	Optional<PolicyHolder> policyHolder = Optional.empty();
	Optional<ArrayList<Operator>> operators= Optional.empty();

	
	
	public String getPolicyId() {
		return policyId;
	}

	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}

	public String getIssuer() {
		return issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	public String getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	public String getRenewalDate() {
		return renewalDate;
	}

	public void setRenewalDate(String renewalDate) {
		this.renewalDate = renewalDate;
	}

	public float getPolicyTermMonths() {
		return policyTermMonths;
	}

	public long getPremiumCents() {
		return premiumCents;
	}

	public void setPremiumCents(long premiumCents) {
		this.premiumCents = premiumCents;
	}

	public Optional<PolicyHolder> getPolicyHolder() {
		return policyHolder.or(Optional::empty);
	}

	public void setPolicyHolder(Optional<PolicyHolder> policyHolder) {
		this.policyHolder = policyHolder;
	}

	public void setPolicyTermMonths(int policyTermMonths) {
		this.policyTermMonths = policyTermMonths;
	}

	public Optional<ArrayList<Operator>> getOperators() {
		return operators.or(Optional::empty);
	}

	public void setOperators(Optional<ArrayList<Operator>> operators) {
		this.operators = operators;
	}

}
