package mk.java.calu4.demo.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Shelter {

  @Id
  @GeneratedValue
  private Long id;

  private String title;
  private String post;
  private LocalDateTime dateCreated;
}
