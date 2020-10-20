package market.research.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import market.research.model.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {

	Location save(Location l);
	
	@Query(value = "select * from location l where l.active = true", nativeQuery = true)
	List<Location> findAll();
	
	@Query(value = "select * from location l where (((LOWER(l.grocery) LIKE %:param%) or (LOWER(l.address) LIKE %:param%)) and l.active = true)"
			+ " or (LOWER(l.city) LIKE %:param%)", nativeQuery = true)
	List<Location> search(@Param("param") String param);
	
	@Query(value = "select * from location l inner join check_in ci on l.id = ci.location_id where (l.id = :id and CAST(ci.date as DATE) = CAST(CURRENT_TIMESTAMP AS DATE) )", nativeQuery = true)
	List<Location> notUsingLocation(@Param("id") Long id);
	
	@Transactional
	@Modifying
	@Query(value = "update location u set u.active = false where u.id = :id", nativeQuery = true)	
	void setActive(@Param("id") Long id);
	
	@Query(value = "select * from location l left join check_in ci on l.id = ci.user_id where (ci.user_id = :id and CAST(ci.date as DATE) = CAST(CURRENT_TIMESTAMP AS DATE) )", nativeQuery = true)	
	Optional<Location> checkedLocation(@Param("id") Long id);
}