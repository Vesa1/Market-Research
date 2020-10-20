package market.research.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import market.research.enums.TypeOfStore;

@Entity
public class Location {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String grocery;
	private String address;
	private String city;
	public double latitude;
	public double longitude;
	public TypeOfStore typeOfStore;
	private boolean active;
	
	public Location() {}
	
	public Location(String grocery, String address, String city, double latitude, double longitude,
			TypeOfStore typeOfStore) {
		super();
		this.grocery = grocery;
		this.address = address;
		this.city = city;
		this.latitude = latitude;
		this.longitude = longitude;
		this.typeOfStore = typeOfStore;
		this.active = true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public TypeOfStore getTypeOfStore() {
		return typeOfStore;
	}

	public void setTypeOfStore(TypeOfStore typeOfStore) {
		this.typeOfStore = typeOfStore;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
