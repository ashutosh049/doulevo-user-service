package com.doulevo.userapi.controller;

import com.doulevo.userapi.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Validated
@RestController
@RequestMapping({"/github"})
public class GithubApiController {

  private final IUserService userService;

  @Autowired
  public GithubApiController(IUserService userService) {
    this.userService = userService;
  }

  @GetMapping({"/users"})
  public CompletableFuture<ResponseEntity<?>> getGithubUserList() {
    return CompletableFuture.supplyAsync(() -> userService.getUserList());
  }

  @GetMapping({"/users/{userid}"})
  public CompletableFuture<ResponseEntity<?>> getGithubUser(
      @PathVariable(required = false) String userid) {
    return CompletableFuture.supplyAsync(() -> userService.getUser(userid));
  }

  @GetMapping(value = {"/users/{userid}/getAvatar"}, produces = MediaType.IMAGE_PNG_VALUE)
  public CompletableFuture<ResponseEntity<?>> getGithubUserAvatar(
          @PathVariable(required = false) String userid) {
    return CompletableFuture.supplyAsync(() -> userService.getUserAvatar(userid));
  }

}
