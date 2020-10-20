package market.research.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import market.research.model.Poll;
import market.research.model.User;

public interface PollRepository extends JpaRepository<Poll, Long> {
	Poll save(Poll p);
	
	@Query(value = "select * from poll p where (p.user_id = :user and CAST(p.date as DATE) = CAST(:date AS DATE) )", nativeQuery = true)	
	List<Poll> getMyPolls(@Param("user") Long user, @Param("date") Date date);
}
