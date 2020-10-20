package market.research.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import market.research.enums.BrandOfCigarettes;

@Entity
public class Cigarettes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private BrandOfCigarettes brand;
	private String type;
	
	public Cigarettes() {
		
	}

	public BrandOfCigarettes getBrand() {
		return brand;
	}

	public void setBrand(BrandOfCigarettes brand) {
		this.brand = brand;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Cigarettes [brand=" + brand + ", type=" + type + "]";
	}
}
