package market.research.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import market.research.enums.BrandOfCigarettes;
import market.research.model.Cigarettes;

public interface CigarettesRepository extends JpaRepository<Cigarettes, Long>{

	@Query(value = "select c.type from cigarettes c where c.brand = :id", nativeQuery = true)	
	List<String> findByBrand(@Param("id") int br);
	
	@Query(value = "select * from cigarettes c where ( c.brand = :brand and c.type = :type )", nativeQuery = true)	
	Optional<Cigarettes> findByBrandAndType(@Param("brand") int brand, @Param("type") String type);
}
