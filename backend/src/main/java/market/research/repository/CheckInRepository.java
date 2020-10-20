package market.research.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import market.research.model.CheckIn;

public interface CheckInRepository extends JpaRepository<CheckIn, Long>{
	
	CheckIn save(CheckIn ci);
	List<CheckIn> findAll();
	
	@Query(value = "select * from check_in ci where (CAST(ci.date as DATE) = CAST(CURRENT_TIMESTAMP AS DATE) )", nativeQuery = true)
	List<CheckIn> todayLocations();
	
}
