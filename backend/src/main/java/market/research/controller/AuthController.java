package market.research.controller;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import market.research.dto.SignInDTO;
import market.research.dto.SignUpDTO;
import market.research.model.User;
import market.research.model.UserState;
import market.research.security.TokenUtils;
import market.research.service.AuthService;
import market.research.service.RoleService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/auth")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private AuthService authService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private TokenUtils tokenUtils;
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> test(@RequestBody SignUpDTO signUpDTO) {
		System.out.println(signUpDTO.toString());
		
		if(signUpDTO.getName().length()<3 || signUpDTO.getName().length()>20) 
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				
		if(signUpDTO.getSurname().length()<3 || signUpDTO.getSurname().length()>20)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				
		if(authService.findByUsername(signUpDTO.getUsername()) != null) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
				
		if(authService.findByEmail(signUpDTO.getEmail().toLowerCase()) != null) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
		if(!signUpDTO.getPassword().equals(signUpDTO.getRepeatedPassword()))
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		String salt = BCrypt.gensalt();
		String hashedPass = BCrypt.hashpw(signUpDTO.getPassword(), salt);
		
		User user = new User(signUpDTO.getName(), signUpDTO.getSurname(), signUpDTO.getUsername(), signUpDTO.getEmail().toLowerCase(), hashedPass);
		user.setRoles(Arrays.asList(roleService.findByName("ROLE_USER")));
		authService.save(user);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody SignInDTO signInDTO, HttpServletResponse response, HttpServletRequest request) throws IOException {
		
		User user = this.authService.findByUsername(signInDTO.getUsername());
		
		if(user == null)
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		
		if(!user.isApproved() || !user.isActive())
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
		if(!BCrypt.checkpw(signInDTO.getPassword(), user.getPassword())) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
		try {
			final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInDTO.getUsername(), signInDTO.getPassword()));
			System.out.println("Prosao je final ");
			SecurityContextHolder.getContext().setAuthentication(authentication);
			User u = (User) authentication.getPrincipal();
			System.out.println("Nasli smo user-a " + user.toString());
			String jwt = tokenUtils.generateToken(u.getUsername());
			System.out.println("Generisan token" + jwt);
			int expiresIn = tokenUtils.getExpiredIn();
			
			return new ResponseEntity<>(new UserState(jwt, Long.valueOf(expiresIn), user.getRole()), HttpStatus.OK);
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
