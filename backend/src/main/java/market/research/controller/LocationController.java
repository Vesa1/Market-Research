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

import market.research.dto.LocationDTO;
import market.research.model.CheckIn;
import market.research.model.Location;
import market.research.model.Role;
import market.research.model.User;
import market.research.security.TokenUtils;
import market.research.service.AuthService;
import market.research.service.CheckInService;
import market.research.service.LocationService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/location")
public class LocationController {
	
	@Autowired
	TokenUtils tokenUtils;
	
	@Autowired
	AuthService authService;
	
	@Autowired
	LocationService locationService;
	
	@Autowired
	CheckInService checkInService;

	@PreAuthorize("hasAuthority('CRUDLocation')")
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<?> newLocation(@RequestBody LocationDTO locationDTO, HttpServletRequest httpRequest) {
		
		Location loc = new Location(locationDTO.getGrocery(), locationDTO.getAddress(), locationDTO.getCity(), locationDTO.getLatitude(),
				locationDTO.getLongitude(), locationDTO.getTypeOfStore());
		
		this.locationService.saveLocation(loc);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('CRUDLocation')")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> editLocation(@PathVariable("id") Long id, @RequestBody LocationDTO locationDTO, HttpServletRequest httpRequest) {
		System.out.println(locationDTO.toString());
		System.out.println(id);
		
		List<Location> checked = this.locationService.notUsingLocation(id);
		System.out.println(checked.size());
		if(checked.size() != 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Location loc = new Location(locationDTO.getGrocery(), locationDTO.getAddress(), locationDTO.getCity(), locationDTO.getLatitude(),
				locationDTO.getLongitude(), locationDTO.getTypeOfStore());
		loc.setId(id);
		this.locationService.saveLocation(loc);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('allLocations')")
	@RequestMapping(value = "/allLocations", method = RequestMethod.GET)
	public ResponseEntity<?> allLocations(HttpServletRequest httpRequest) {
		
		String authToken = tokenUtils.getToken(httpRequest);
		String username = tokenUtils.getUsernameFromToken(authToken);
		
		User user = this.authService.findByUsername(username);
		
		if(user == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);		
		}
		
		Role r = user.getRoles().iterator().next();
		
		if(r.getName().equals("ROLE_USER")) {
			if(!this.authService.notUsing(user.getId())) {
				return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
			}
		}
				
		List<Location> allLocations = this.locationService.allLocations();
		
		return new ResponseEntity<>(allLocations, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/search/{param}", method = RequestMethod.GET)
	public ResponseEntity<?> allLocations(@PathVariable("param") String param, HttpServletRequest httpRequest) {
		List<Location> loc = this.locationService.search(param);
		return new ResponseEntity<>(loc, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('checkIn')")
	@RequestMapping(value = "/checkin/{param}", method = RequestMethod.GET)
	public ResponseEntity<?> checkIn(@PathVariable("param") Long param, HttpServletRequest httpRequest) {
		
		String authToken = tokenUtils.getToken(httpRequest);
		String username = tokenUtils.getUsernameFromToken(authToken);
		
		User user = this.authService.findByUsername(username);
		
		Location loc = this.locationService.findById(param);
		if(loc==null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		System.out.println(user.toString());
		CheckIn checkIn = new CheckIn(user, loc, new Date());
		this.checkInService.save(checkIn);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('CRUDLocation')")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> delete(@PathVariable("id") Long id, HttpServletRequest httpRequest) {
		
		Location loc = this.locationService.findById(id);
		if(loc == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		List<Location> checked = this.locationService.notUsingLocation(id);
		System.out.println(checked.size());
		if(checked.size() != 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		this.locationService.delete(id);
		
		return new ResponseEntity<> (HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('CRUDLocation')")
	@RequestMapping(value = "/todayLocations", method = RequestMethod.GET)
	public ResponseEntity<?> todayLocations(HttpServletRequest httpRequest) {
		
		List<CheckIn> list = this.checkInService.todayLocations();
			
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
}
