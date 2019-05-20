package com.starshot.demo.service;

import com.starshot.demo.entity.User;

import java.util.List;

public interface UserService {

  List<User> getAllUsers(String name, String active);

  String deleteById(String id);

  String save(Long id, String fullName, String timeIn, String timeOut, String active);

}
