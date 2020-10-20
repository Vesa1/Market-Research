package market.research.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import market.research.dto.UserDTO;
import market.research.model.Location;
import market.research.model.Role;
import market.research.model.User;
import market.research.security.TokenUtils;
import market.research.service.AuthService;
import market.research.service.LocationService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
	private TokenUtils tokenUtils;
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private LocationService locationService;

	@PreAuthorize("hasAuthority('CRUDUsers')")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<?> allUsers(HttpServletRequest httpRequest) {
		
		List<User> users = this.authService.findByRole(Long.parseLong("1"));
		
		List<UserDTO> ret = new ArrayList<UserDTO>();
		for(User u : users) {
			ret.add(new UserDTO(u.getId(), u.getName(), u.getSurname(), u.getUsername(), u.getEmail(), u.isApproved(), u.getRole()));
		}
		
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('CRUDUsers')")
	@RequestMapping(value = "/approve/{id}", method = RequestMethod.GET) 
	public ResponseEntity<?> approve(@PathVariable("id") Long id, HttpServletRequest httpRequest) {
		this.authService.approve(id);		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('CRUDUsers')")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE) 
	public ResponseEntity<?> delete(@PathVariable("id") Long id, HttpServletRequest httpRequest) {
		
		boolean notUsing = this.authService.notUsing(id);
		
		if(notUsing) {
			this.authService.deleteUser(id);
			return new ResponseEntity<>(HttpStatus.OK); 
		}
		
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}
	
	@RequestMapping(value = "/checkedLocation", method = RequestMethod.GET) 
	public ResponseEntity<?> checkedLocation(HttpServletRequest httpRequest) {
		String authToken = tokenUtils.getToken(httpRequest);
		String username = tokenUtils.getUsernameFromToken(authToken);
		
		User user = this.authService.findByUsername(username);
		
		if(user == null) {
			System.out.println("userrrrrr");
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);		
		}
		
		Role r = user.getRoles().iterator().next();
		
		Location l = this.locationService.chechedLocation(user.getId());
		return new ResponseEntity<>(l, HttpStatus.OK);
	}
}
