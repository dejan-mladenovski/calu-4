package mk.java.calu4.demo.repositories;

import mk.java.calu4.demo.models.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SheltersRepository extends JpaRepository<Shelter, Long> {
}
