package de.jayasinghe.samples.notification;

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

	@Override
	public String toString() {
		return "Customer{" + "firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", email='" + email + '\'' + ", mobileNumber='" + mobileNumber + '\'' + '}';
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
}
