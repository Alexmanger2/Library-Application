package project;

public class Address {

	private String street;
	private String city;
	private String zip;
	private String state;

	public Address() {
	}

	/**
	 * Address constructor
	 * 
	 * @param street String sets the street
	 * @param city   String sets the city
	 * @param state  String sets the state
	 * @param zip    String sets the zipcode
	 */
	public Address(String street, String city, String state, String zip) {
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}

	/**
	 * Getter for street
	 * 
	 * @return String street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * Setter for Street
	 * 
	 * @param street String sets the street
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * getter for city
	 * 
	 * @return String city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Setter for City
	 * 
	 * @param city String sets the city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Getter for zip
	 * 
	 * @return String return zip
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * Setter for zip
	 * 
	 * @param zip String sets the zip (zipcode)
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}

	/**
	 * Getter for state
	 * 
	 * @return String returns the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * Setter for state
	 * 
	 * @param state String sets the state.
	 */
	public void setState(String state) {
		this.state = state;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((street == null) ? 0 : street.hashCode());
		result = prime * result + ((zip == null) ? 0 : zip.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;
		if (zip == null) {
			if (other.zip != null)
				return false;
		} else if (!zip.equals(other.zip))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Address [street=" + street + ", city=" + city + ", zip=" + zip + ", state=" + state + "]";
	}

}