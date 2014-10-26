/**
 * Stefano Prezioso 
 * COSC 311 
 * Project 2
 * Fall 2014
 */

public class DataStructureRecord {
	private String firstName, lastName, ID;

	public DataStructureRecord(String firstName, String lastName, String ID) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.ID = ID;
	}
	
	public DataStructureRecord() {
		this("", "", "");
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getID() {
		return ID;
	}

	@Override
	public String toString() {
		return firstName + " " + lastName + " " + ID;
	}
}
