package exercise.repository;

import exercise.model.City;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {

    // BEGIN
    Iterable<City> findByNameStartingWithIgnoreCase(String prefix);
    @Query(value = "select * from cities order by name", nativeQuery = true)
    Iterable<City> findAllOrderByName();
    // END
}
