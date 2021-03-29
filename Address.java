package project;

public class Address {

	
	private String street;
	private String city;
	private String zip;
	private String state;
	
	
	
	public Address() {
		// TODO Auto-generated constructor stub
	}
	
	public Address(String s, String c, String z, String st) {
		this.street = s;
		this.city = c;
		this.zip = z;
		this.state = st;
	}
	
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	

	@Override
	public String toString() {
		return "Address [street=" + street + ", city=" + city + ", zip=" + zip + ", state=" + state + "]";
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Address myAddress = new Address("100", "Smith", "Staten Island" , "New York");
		System.out.println(myAddress);
		
	}

}
