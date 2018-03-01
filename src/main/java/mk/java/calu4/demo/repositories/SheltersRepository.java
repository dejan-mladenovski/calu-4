package mk.java.calu4.demo.repositories;

import mk.java.calu4.demo.models.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SheltersRepository extends JpaRepository<Shelter, Long> {
}
