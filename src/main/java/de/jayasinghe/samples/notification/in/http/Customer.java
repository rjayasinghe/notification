package de.jayasinghe.samples.notification.in.http;

public class Customer {
	private final String firstName;
	private final String lastName;
	private final String email;
	private final String mobileNumber;

	public Customer(String firstName, String lastName, String email, String mobileNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobileNumber = mobileNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public de.jayasinghe.samples.notification.Customer toCustomer() {
		return new de.jayasinghe.samples.notification.Customer(firstName, lastName, email, mobileNumber);
	}
}
