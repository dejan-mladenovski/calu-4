package mk.java.calu4.demo.repositories;

import mk.java.calu4.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, Long> {
  User findByName(String username);
}
