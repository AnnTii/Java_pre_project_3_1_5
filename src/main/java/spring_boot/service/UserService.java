package spring_boot.service;

import spring_boot.model.User;
import java.util.List;

public interface UserService {

    List<User> getAllUser();
    void saveUser(User user);
}
