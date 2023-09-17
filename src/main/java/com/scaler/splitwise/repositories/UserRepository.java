package com.scaler.splitwise.repositories;

import com.scaler.splitwise.models.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  @Override
  User save(User user);

  Optional<User> findByUserName(String userName);

  Optional<User> findByMobile(String userName);
}
