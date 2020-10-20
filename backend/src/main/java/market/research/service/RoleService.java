package market.research.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import market.research.model.Role;
import market.research.repository.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	public Role findByName(String name) {
		return this.roleRepository.findByName(name);
	}
}
