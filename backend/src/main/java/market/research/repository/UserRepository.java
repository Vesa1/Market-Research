package market.research.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import market.research.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
	User findByEmail(String email);
	User save(User user);
	
	@Query(value = "select * from user u left join users_roles ur on u.id = ur.user_id where (ur.role_id != :role and u.active = 1) ", nativeQuery = true)
	List<User> findByRole(@Param("role") Long role); 
	
	@Transactional
	@Modifying
	@Query(value = "update user u set u.approved = true where u.id = :id", nativeQuery = true)	
	void approve(@Param("id") Long id);
	
	@Query(value = "select * from user u left join check_in ci on u.id = ci.user_id where (u.id = :id and CAST(ci.date as DATE) = CAST(CURRENT_TIMESTAMP AS DATE) )", nativeQuery = true)	
	List<User> notUsing(@Param("id") Long id);
	
	@Transactional
	@Modifying
	@Query(value = "update user u set u.active = false where u.id = :id", nativeQuery = true)	
	void delete(@Param("id") Long id);
} 
