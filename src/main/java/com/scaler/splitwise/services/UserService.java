package com.scaler.splitwise.services;

import com.scaler.splitwise.enums.UserStatus;
import com.scaler.splitwise.exceptions.UserAlreadyExistsException;
import com.scaler.splitwise.models.User;
import com.scaler.splitwise.repositories.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User registerUser(String userName, String mobile, String password)
      throws UserAlreadyExistsException {
    Optional<User> userOptional = userRepository.findByMobile(mobile);
    if (userOptional.isPresent()) {
      if (userOptional.get().getUserStatus().equals(UserStatus.ACTIVE)) {
        throw new UserAlreadyExistsException("User with mobile " + mobile + " already present");
      } else {
        User user = userOptional.get();
        user.setUserName(userName);
        user.setUserStatus(UserStatus.ACTIVE);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(password));
        return userRepository.save(user);
      }
    }
    User user = new User();
    user.setUserName(userName);
    user.setMobile(mobile);
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    user.setPassword(encoder.encode(password));
    user.setUserStatus(UserStatus.ACTIVE);

    return userRepository.save(user);
  }
}
