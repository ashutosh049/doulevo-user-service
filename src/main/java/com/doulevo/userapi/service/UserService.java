package com.doulevo.userapi.service;

import com.doulevo.userapi.config.UserApiConfig;
import com.doulevo.userapi.dto.github.GithubUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.doulevo.userapi.util.ObjectUtils.isEmpty;

@Getter
@Setter
@Slf4j
@Service
public class UserService extends AbstractUserService {

  private static final String PATH_SEPERATOR = "/";
  private static final String CREDENTIAL_SEPARATOR = ":";
  private static final String BASIC_AUTH_PREFIX = "Basic ";
  private static final String AUTHORIZATION = "Authorization";
  private static final String ACCEPT = "Accept";
  private static final String ACCEPT_APPLICATION_GITHUB_V3 = "application/vnd.github.v3+json";

  private RestTemplate restTemplate;
  private RestTemplate restTemplateImage;
  private UserApiConfig userApiConfig;
  private ObjectMapper objectMapper;

  private Supplier<HttpHeaders> buildAuthHeaders =
      () ->
          new HttpHeaders() {
            {
              String auth =
                  userApiConfig.getGithubApi().getUsername()
                      + CREDENTIAL_SEPARATOR
                      + userApiConfig.getGithubApi().getPassword();
              byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
              String authHeader = BASIC_AUTH_PREFIX + new String(encodedAuth);
              set(AUTHORIZATION, authHeader);
              set(ACCEPT, ACCEPT_APPLICATION_GITHUB_V3);

              // headers.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));
            }
          };

  private Supplier<HttpHeaders> buildImageHeaders =
      () -> {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));
        headers.set("Content-Type", "image/png");
        return headers;
      };

  private Function<Optional<String>, String> buildRequestUrl =
      userIdOp -> {
        if (userIdOp.isPresent()) {
          return new StringBuilder()
              .append(userApiConfig.getGithubApi().getUrl())
              .append(PATH_SEPERATOR)
              .append(userApiConfig.getGithubApi().getApiPath())
              .append(PATH_SEPERATOR)
              .append(userIdOp.get())
              .toString();
        }

        return new StringBuilder()
            .append(userApiConfig.getGithubApi().getUrl())
            .append(PATH_SEPERATOR)
            .append(userApiConfig.getGithubApi().getApiPath())
            .toString();
      };

  @Autowired
  public UserService(
      RestTemplate restTemplate,
      RestTemplate restTemplateImage,
      UserApiConfig userApiConfig,
      ObjectMapper objectMapper) {
    this.restTemplate = restTemplate;
    this.restTemplateImage = restTemplateImage;
    this.userApiConfig = userApiConfig;
    this.objectMapper = objectMapper;
  }

  @Override
  public ResponseEntity<?> getUser(String userid) {

    HttpHeaders httpHeaders = buildAuthHeaders.get();

    ResponseEntity<GithubUser> responseEntity =
        restTemplate.exchange(
            buildRequestUrl.apply(Optional.of(userid)),
            HttpMethod.GET,
            new HttpEntity(httpHeaders),
            new ParameterizedTypeReference<GithubUser>() {});

    if (log.isDebugEnabled()) {
      log.debug("Github user: " + responseEntity.getBody().toString());
    }

    return responseEntity;
  }

  @Override
  public ResponseEntity getUserList() {
    HttpHeaders httpHeaders = buildAuthHeaders.get();

    ResponseEntity<?> responseEntity =
        restTemplate.exchange(
            buildRequestUrl.apply(Optional.empty()),
            HttpMethod.GET,
            new HttpEntity(httpHeaders),
            new ParameterizedTypeReference<List>() {});

    if (log.isDebugEnabled()) {
      // Do not log here
      // log.debug("Github user: " + responseEntity.getBody().toString());
    }

    return responseEntity;
  }

  @Override
  public ResponseEntity<byte[]> getUserAvatar(String userid) {
    HttpHeaders httpHeaders = buildAuthHeaders.get();

    ResponseEntity<GithubUser> responseEntity =
        restTemplate.exchange(
            buildRequestUrl.apply(Optional.of(userid)),
            HttpMethod.GET,
            new HttpEntity(httpHeaders),
            new ParameterizedTypeReference<GithubUser>() {});

    if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
      GithubUser targetUser = responseEntity.getBody();
      final String avatarUrl = targetUser.getAvatar_url();
      if (!isEmpty(avatarUrl)) {

        /*List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        messageConverters.add(new ByteArrayHttpMessageConverter());
        restTemplate.setMessageConverters(messageConverters);*/

        /*ResponseEntity responseEntityAvatar =
            restTemplate.exchange(
                avatarUrl,
                HttpMethod.GET,
                new HttpEntity<String>(buildImageHeaders.get()),
                new ParameterizedTypeReference<Byte[]>() {});
        return responseEntityAvatar;*/

        byte[] imageBytes = restTemplateImage.getForObject(avatarUrl, byte[].class);
        try {
          Files.write(Paths.get(userid+".jpg"), imageBytes);
        } catch (IOException e) {
          e.printStackTrace();
        }

        return ResponseEntity.ok().body(imageBytes);
      }
    }

    return ResponseEntity.notFound().build();
  }
}
