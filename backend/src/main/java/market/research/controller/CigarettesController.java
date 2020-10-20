package market.research.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import market.research.dto.PollDTO;
import market.research.enums.BrandOfCigarettes;
import market.research.model.Cigarettes;
import market.research.model.Poll;
import market.research.model.Role;
import market.research.model.User;
import market.research.security.TokenUtils;
import market.research.service.AuthService;
import market.research.service.CigarettesService;
import market.research.service.PollService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/cigarettes")
public class CigarettesController {

	@Autowired
	private TokenUtils tokenUtils;
	
	@Autowired	
	private AuthService authService;
	
	@Autowired
	private CigarettesService cigarettesService;
	
	@Autowired
	private PollService pollService;
	
	@PreAuthorize("hasAuthority('CRUDCigarettes')")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<?> allBrands(HttpServletRequest httpRequest) {
		
		BrandOfCigarettes [] ret = BrandOfCigarettes.values();
		
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('CRUDCigarettes')")
	@RequestMapping(value = "/type/{type}", method = RequestMethod.GET)
	public ResponseEntity<?> getTypes(@PathVariable("type") String type, HttpServletRequest httpRequest) {
		
		BrandOfCigarettes cigarettes = BrandOfCigarettes.valueOf(type);
		
		if(cigarettes == null) 
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		System.out.println(cigarettes.toString());
		List<String> types = this.cigarettesService.findByBrand(cigarettes);
		System.out.println("Brrrr" + types.size());
		
		return new ResponseEntity<>(types, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('CRUDpolls')")
	@RequestMapping(value = "/send", method = RequestMethod.POST)
	public ResponseEntity<?> send(@RequestBody PollDTO poll, HttpServletRequest httpRequest) {
		
		String authToken = tokenUtils.getToken(httpRequest);
		String username = tokenUtils.getUsernameFromToken(authToken);	
		User user = this.authService.findByUsername(username);
		
		if(!poll.isAge()) {
			Poll newPoll = new Poll(user, poll.isAge());
			this.pollService.save(newPoll);
		}
		
		if(poll.isAge() && !poll.isSmoker()) {
			Poll newPoll = new Poll(user, poll.isAge(), poll.isSmoker());
			this.pollService.save(newPoll);
		}
		
		if(poll.isAge() && poll.isSmoker()) {
			if(poll.cigarettes<0 && poll.cigarettes>100) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			
			BrandOfCigarettes brand = BrandOfCigarettes.valueOf(poll.getBrandOfCigarettes());
			
			Cigarettes cig = this.cigarettesService.findByBrandAndType(brand, poll.getTypeOfCigarettes());
			Poll newPoll = new Poll(user, poll.isAge(), poll.isSmoker(), poll.getCigarettes(), cig);
			this.pollService.save(newPoll);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	@SuppressWarnings("deprecation")
	@PreAuthorize("hasAuthority('CRUDpolls')")
	@RequestMapping(value = "/myPolls/{date}", method = RequestMethod.GET)
	public ResponseEntity<?> send(@PathVariable("date") String date, HttpServletRequest httpRequest) {
		System.out.println(date);
		
		String authToken = tokenUtils.getToken(httpRequest);
		String username = tokenUtils.getUsernameFromToken(authToken);	
		User user = this.authService.findByUsername(username);
		
		String [] parts = date.split("-");
		int year = Integer.parseInt(-1900 + parts[0]);
		System.out.println("Broj" + year);
		Date now = new Date(-1900 + Integer.parseInt(parts[0]), Integer.parseInt(parts[1])-1, Integer.parseInt(parts[2]));

		System.out.println(now.toString());
		
		List<Poll> polls = this.pollService.getMyPolls(user, now);
		System.out.println("Ima anketa: " + polls);
		
		return new ResponseEntity<>(polls, HttpStatus.OK);
	}
	
	@SuppressWarnings("deprecation")
	@PreAuthorize("hasAuthority('allPolls')")
	@RequestMapping(value = "/polls/{date}/{user}", method = RequestMethod.GET)
	public ResponseEntity<?> paramPolls(@PathVariable("date") String date, @PathVariable("user") String u, HttpServletRequest httpRequest) {
		
		User userDTO = this.authService.findByUsername(u);
		
		String [] parts = date.split("-");
		Date now = new Date(-1900 + Integer.parseInt(parts[0]), Integer.parseInt(parts[1])-1, Integer.parseInt(parts[2]));
		
		List<Poll> ret = this.pollService.getMyPolls(userDTO, now);
		System.out.println("Vracene ankete: " + ret);
		
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
}
