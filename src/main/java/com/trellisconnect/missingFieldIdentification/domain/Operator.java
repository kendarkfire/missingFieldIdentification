package com.trellisconnect.missingFieldIdentification.domain;

import java.util.Optional;

public class Operator {
	private Boolean isPrimary;
	Optional<Name> name= Optional.empty();
	Optional<BirthdayRange> birthdayRange= Optional.empty();
	private String gender;
	private String driversLicenseState;
	private String driversLicenseStatus;
	private String driversLicenseNumber;
	private String relationship;

	public Boolean getIsPrimary() {
		return isPrimary;
	}

	public void setIsPrimary(Boolean isPrimary) {
		this.isPrimary = isPrimary;
	}

	public Optional<Name> getName() {
		return name.or(Optional::empty);
	}

	public void setName(Optional<Name> name) {
		this.name = name;
	}

	public Optional<BirthdayRange> getBirthdayRange() {
		return birthdayRange.or(Optional::empty);
	}

	public void setBirthdayRange(Optional<BirthdayRange> birthdayRange) {
		this.birthdayRange = birthdayRange;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDriversLicenseState() {
		return driversLicenseState;
	}

	public void setDriversLicenseState(String driversLicenseState) {
		this.driversLicenseState = driversLicenseState;
	}

	public String getDriversLicenseStatus() {
		return driversLicenseStatus;
	}

	public void setDriversLicenseStatus(String driversLicenseStatus) {
		this.driversLicenseStatus = driversLicenseStatus;
	}

	public String getDriversLicenseNumber() {
		return driversLicenseNumber;
	}

	public void setDriversLicenseNumber(String driversLicenseNumber) {
		this.driversLicenseNumber = driversLicenseNumber;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

}
