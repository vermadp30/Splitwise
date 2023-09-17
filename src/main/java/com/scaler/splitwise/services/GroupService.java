package com.scaler.splitwise.services;

import com.scaler.splitwise.exceptions.InvalidArgumentException;
import com.scaler.splitwise.exceptions.UserNotFoundException;
import com.scaler.splitwise.models.Group;
import com.scaler.splitwise.models.User;
import com.scaler.splitwise.repositories.GroupRepository;
import com.scaler.splitwise.repositories.UserRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupService {
  private GroupRepository groupRepository;
  private UserRepository userRepository;

  @Autowired
  public GroupService(GroupRepository groupRepository, UserRepository userRepository) {
    this.groupRepository = groupRepository;
    this.userRepository = userRepository;
  }

  public Group createGroup(String inputUserId, String groupName)
      throws InvalidArgumentException, UserNotFoundException {

    Long userId = 0L;

    try {
      userId = Long.parseLong(inputUserId);
    } catch (Exception exception) {
      throw new InvalidArgumentException("Invalid userId provided");
    }

    Optional<User> userOptional = userRepository.findById(userId);

    if (userOptional.isEmpty()) {
      throw new UserNotFoundException("User with User Id: " + userId + " does not exist.");
    }

    User user = userOptional.get();

    Group group = new Group();
    group.setCreatedBy(user);
    group.setName(groupName);
    ArrayList<User> members = new ArrayList<>();
    members.add(user);
    group.setMembers(members);
    return groupRepository.save(group);
  }
}
