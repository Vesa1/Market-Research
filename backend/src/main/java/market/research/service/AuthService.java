package market.research.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import market.research.model.User;
import market.research.repository.UserRepository;

@Service
public class AuthService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		 
		 if (user == null) {
				throw new UsernameNotFoundException(String.format("No user found with email '%s'.", username));
			} else {
				System.out.println("\nNadjen je user: " + user.toString());
			}
		 
		 
	     return user;
	}

	public User findByUsername(String username) {
		return this.userRepository.findByUsername(username);
	}
	
	public User findByEmail(String email) {
		return this.userRepository.findByEmail(email);
	}
	
	public User save(User user) {
		return this.userRepository.save(user);
	}
	
	public List<User> findByRole(Long role) {
		return this.userRepository.findByRole(role);
	}
	
	public User findById(Long id) {
		Optional<User> user = this.userRepository.findById(id);
		if(user == null) 
			return null;
		return user.get();
	}
	
	public void approve(Long id) {
		this.userRepository.approve(id);
	}
	
	public boolean notUsing(Long id) {
		List<User> list = this.userRepository.notUsing(id);
		
		if(list.size() == 0) {
			return true;
		}
		return false;
	}
	
	public void deleteUser(Long id) {
		this.userRepository.delete(id);
	}
 }
