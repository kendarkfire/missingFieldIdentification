package com.trellisconnect.missingFieldIdentification.domain;

import java.util.Optional;

public class PolicyHolder {
	Optional<Name> name = Optional.empty();
	Optional<Address> address = Optional.empty();
	private String email;
	private String phone;

	public Optional<Name> getName() {
		return name.or(Optional::empty);
	}

	public void setName(Optional<Name> name) {
		this.name = name;
	}

	public Optional<Address> getAddress() {
		return address.or(Optional::empty);
	}

	public void setAddress(Optional<Address> address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
