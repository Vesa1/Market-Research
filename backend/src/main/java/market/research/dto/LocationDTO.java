package market.research.dto;

import market.research.enums.TypeOfStore;

public class LocationDTO {
	
	private String grocery;
	private String address;
	private String city;
	private double longitude;
	private double latitude;
	private TypeOfStore typeOfStore;
	
	public LocationDTO() {
		
	}

	public String getGrocery() {
		return grocery;
	}

	public void setGrocery(String grocery) {
		this.grocery = grocery;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public TypeOfStore getTypeOfStore() {
		return typeOfStore;
	}

	public void setTypeOfStore(TypeOfStore typeOfStore) {
		this.typeOfStore = typeOfStore;
	}

	@Override
	public String toString() {
		return "LocationDTO [grocery=" + grocery + ", address=" + address + ", city=" + city + ", longitude="
				+ longitude + ", latitude=" + latitude + ", typeOfStore=" + typeOfStore + "]";
	}
}
