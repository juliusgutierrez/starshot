package com.starshot.demo.service.impl;

import com.starshot.demo.dao.UserRepository;
import com.starshot.demo.entity.User;
import com.starshot.demo.service.UserService;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

/**
 * Created by juliusgutierrez on 18/05/2019.
 */
@Service
public class UserServiceImpl implements UserService {

  private UserRepository userRepo;

  @Autowired
  public UserServiceImpl(UserRepository userRepo) {
    this.userRepo = userRepo;
  }

  /**
   * Get All user,
   * when name is not null, it will search for anything that is alike.
   *
   * @param name - full name
   * @param active - status
   * @return
   */
  @Override
  public List<User> getAllUsers(String name, String active) {
    if (StringUtils.isBlank(name) && StringUtils.isBlank(active)) {
      return userRepo.findAll();
    }

    User user = new User();
    if (StringUtils.isNotBlank(active)) {
      user.setActive(Boolean.parseBoolean(active));
    }

    Example<User> example = Example.of(user);
    if (StringUtils.isNotBlank(name)) {
      user.setFullName(name);
      ExampleMatcher matcher = ExampleMatcher.matching()
          .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
          .withIgnoreCase();

      //update the example with matcher
      example = Example.of(user, matcher);
    }

    return userRepo.findAll(example);
  }

  @Override
  public String deleteById(String id) {
    userRepo.deleteById(Long.parseLong(id));
    return "Success";
  }

  @Override
  public String save(Long id, String fullName, String timeIn, String timeOut, String active) {

    if (StringUtils.isBlank(fullName)
        || StringUtils.isBlank(timeIn)
        || StringUtils.isBlank(timeOut)
        || StringUtils.isBlank(active)) {
      throw new RuntimeException("Empty fields is not allowed");
    }

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy h:mm a");
    LocalDateTime ldTimeIn = LocalDateTime.parse(timeIn, dateTimeFormatter);
    LocalDateTime ldTimeOut = LocalDateTime.parse(timeOut, dateTimeFormatter);

    if (ldTimeIn.compareTo(ldTimeOut) > 0) {
      throw new RuntimeException("Time in should be be greater than Time out");
    }

    User user = new User();
    if (id != null) {
      user.setUserId(id);
    }
    user.setFullName(fullName);
    user.setTimeIn(Date.from(ldTimeIn.atZone(ZoneId.systemDefault()).toInstant()));
    user.setTimeOut(Date.from(ldTimeOut.atZone(ZoneId.systemDefault()).toInstant()));

    user.setActive(Boolean.parseBoolean(active));
    userRepo.save(user);

    return "Success";
  }
}

