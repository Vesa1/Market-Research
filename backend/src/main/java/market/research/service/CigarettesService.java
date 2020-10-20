package market.research.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import market.research.enums.BrandOfCigarettes;
import market.research.model.Cigarettes;
import market.research.repository.CigarettesRepository;

@Service
public class CigarettesService {

	@Autowired
	private CigarettesRepository cigaretesRepository;
	
	public List<String> findByBrand(BrandOfCigarettes br) {
		System.out.println(br);
		return this.cigaretesRepository.findByBrand(br.ordinal());
	}
	
	public Cigarettes findByBrandAndType(BrandOfCigarettes brand, String type) {
		Optional<Cigarettes> cig = this.cigaretesRepository.findByBrandAndType(brand.ordinal(), type);
		if(cig.isPresent()) {
			return cig.get();
		}
		return null;
	}
}
