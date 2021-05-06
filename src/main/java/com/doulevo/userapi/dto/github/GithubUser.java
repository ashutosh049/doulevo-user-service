package com.doulevo.userapi.dto.github;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubUser extends AppDto {
  private String login;
  private long id;
  private String node_id;
  private String avatar_url;
  private String gravatar_id;
  private String url;
  private String html_url;
  private String followers_url;
  private String following_url;
  private String gists_url;
  private String starred_url;
  private String subscriptions_url;
  private String organizations_url;
  private String repos_url;
  private String events_url;
  private String received_events_url;
  private String type;
  private boolean site_admin;
  private String name;
  private String company;
  private String blog;
  private String location;
  private String email;
  private String hireable;
  private String bio;
  private String twitter_username;
  private Integer public_repos;
  private Integer public_gists;
  private Integer followers;
  private Integer following;
  private String created_at;
  private String updated_at;

  @Override
  public String toString() {
    return "GithubUser{"
        + "login='"
        + login
        + '\''
        + ", id="
        + id
        + ", url='"
        + url
        + '\''
        + ", name='"
        + name
        + '\''
        + '}';
  }
}
