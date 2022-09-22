package spring_boot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import spring_boot.model.User;

public interface UserDao extends JpaRepository<User, Integer> {
}
