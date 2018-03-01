package mk.java.calu4.demo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {
  @Id
  @GeneratedValue
  private Long id;
  private String username;
  private String password;

  public static User seed() {
    User u = new User();
    u.username = "admin";
    u.password = "admin";
    return u;
  }
}
