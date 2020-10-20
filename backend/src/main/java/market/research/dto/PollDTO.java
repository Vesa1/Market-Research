package market.research.dto;

import market.research.enums.BrandOfCigarettes;

public class PollDTO {
	
	public boolean age;
	public boolean smoker;
	public int cigarettes;
	public String brandOfCigarettes;
	public String typeOfCigarettes;
	
	public PollDTO() {
		
	}

	public boolean isAge() {
		return age;
	}

	public void setAge(boolean age) {
		this.age = age;
	}

	public boolean isSmoker() {
		return smoker;
	}

	public void setSmoker(boolean smoker) {
		this.smoker = smoker;
	}

	public int getCigarettes() {
		return cigarettes;
	}

	public void setCigarettes(int cigarettes) {
		this.cigarettes = cigarettes;
	}

	public String getBrandOfCigarettes() {
		return brandOfCigarettes;
	}

	public void setBrandOfCigarettes(String brandOfCigarettes) {
		this.brandOfCigarettes = brandOfCigarettes;
	}

	public String getTypeOfCigarettes() {
		return typeOfCigarettes;
	}

	public void setTypeOfCigarettes(String typeOfCigarettes) {
		this.typeOfCigarettes = typeOfCigarettes;
	}

	@Override
	public String toString() {
		return "PollDTO [age=" + age + ", smoker=" + smoker + ", cigarettes=" + cigarettes + ", brandOfCigarettes="
				+ brandOfCigarettes + ", typeOfCigarettes=" + typeOfCigarettes + "]";
	}
}
