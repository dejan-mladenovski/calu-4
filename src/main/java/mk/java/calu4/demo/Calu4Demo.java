package mk.java.calu4.demo;

import lombok.extern.slf4j.Slf4j;
import mk.java.calu4.demo.models.User;
import mk.java.calu4.demo.repositories.UsersRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
@Slf4j
public class Calu4Demo {
  public static void main(String[] args) {
    SpringApplication.run(Calu4Demo.class, args);
  }
}
