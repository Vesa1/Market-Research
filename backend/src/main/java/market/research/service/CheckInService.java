package market.research.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import market.research.model.CheckIn;
import market.research.repository.CheckInRepository;

@Service
public class CheckInService {

	@Autowired
	private CheckInRepository checkInRepository;
	
	public CheckIn save(CheckIn ci) {
		return this.checkInRepository.save(ci);
	}
	
	public List<CheckIn> todayLocations() {
		return this.checkInRepository.todayLocations();
	}
}
