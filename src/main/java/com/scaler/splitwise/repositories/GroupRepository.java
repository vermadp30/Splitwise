package com.scaler.splitwise.repositories;

import com.scaler.splitwise.models.Group;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
  @Override
  Group save(Group group);

  Optional<Group> findByName(String description);

  @Override
  Optional<Group> findById(Long groupId);
}
