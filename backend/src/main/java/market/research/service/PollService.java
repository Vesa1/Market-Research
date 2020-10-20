package market.research.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import market.research.model.Poll;
import market.research.model.User;
import market.research.repository.PollRepository;

@Service
public class PollService {

	@Autowired
	private PollRepository pollRepository;
	
	public void save(Poll p) {
		this.pollRepository.save(p);
	}
	
	public List<Poll> getMyPolls(User user, Date date) {
		return this.pollRepository.getMyPolls(user.getId(), date);
	}
}
