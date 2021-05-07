package com.trellisconnect.missingFieldIdentification.domain;

public class Name {
	private String firstName;
	private String middleName;
	private String lastName;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}

		if (!(obj instanceof Name)) {
			return false;
		}

		Name n = (Name) obj;

		return ((firstName == null && n.getFirstName() == null) || firstName.equals(n.getFirstName()))
				&& ((middleName == null && n.getMiddleName() == null) || middleName.equals(n.getMiddleName()))
				&& ((lastName == null && n.getLastName() == null) || lastName.equals(n.getLastName()));
	}

}
