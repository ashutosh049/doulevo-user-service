package com.doulevo.userapi.service;

import org.springframework.http.ResponseEntity;

public interface IUserService {
  ResponseEntity getUser(String userid);

  ResponseEntity getUserList();

  ResponseEntity getUserAvatar(String userid);
}
