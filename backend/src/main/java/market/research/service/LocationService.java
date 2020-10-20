package market.research.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import market.research.model.CheckIn;
import market.research.model.Location;
import market.research.repository.LocationRepository;

@Service
public class LocationService {
	
	@Autowired
	private LocationRepository locationRepository;
	
	public void saveLocation(Location l) {
		locationRepository.save(l);
	}

	public List<Location> allLocations() {
		return this.locationRepository.findAll();
	}
	
	public List<Location> search(String param) {
		return this.locationRepository.search(param.toLowerCase());
	}
	
	public Location findById(Long id) {
		Optional<Location> loc = this.locationRepository.findById(id);
		if(loc!=null) {
			return loc.get();
		}
		return null;
	}
	
	public List<Location> notUsingLocation(Long id) {
		return this.locationRepository.notUsingLocation(id);
	}
	
	public void delete(Long id) {
		System.out.println("Usao sa id " + id);
		this.locationRepository.setActive(id);
	}
	
	public Location chechedLocation(Long id) {
		Optional<Location> loc = this.locationRepository.checkedLocation(id);
		
		if(loc.isPresent())
			return loc.get();
		else 
			return null;
	}
}
