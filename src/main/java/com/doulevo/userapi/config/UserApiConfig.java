package com.doulevo.userapi.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "app.doulevo")
public class UserApiConfig {

  private GithubApi githubApi = new GithubApi();

  @Bean
  public ByteArrayHttpMessageConverter byteArrayHttpMessageConverter() {
    return new ByteArrayHttpMessageConverter();
  }

  @Bean
  public RestTemplate restTemplateImage(List<HttpMessageConverter<?>> messageConverters) {
    return new RestTemplate(messageConverters);
  }

  @Bean
  public RestTemplate restTemplate(RestTemplateBuilder builder) {

    /*List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();

    messageConverters.add(new ByteArrayHttpMessageConverter());
    messageConverters.add(new StringHttpMessageConverter());
    messageConverters.add(new ResourceHttpMessageConverter());
    messageConverters.add(new SourceHttpMessageConverter<>());
    messageConverters.add(new AllEncompassingFormHttpMessageConverter());*/

    /*List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
    messageConverters.add(new ByteArrayHttpMessageConverter());

    RestTemplate restTemplate = new RestTemplate(messageConverters);

    return restTemplate;*/
    return builder.build();
  }

  @Bean
  public ObjectMapper objectMapper() {
    Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
    builder.indentOutput(true);
    builder.failOnEmptyBeans(false);
    builder.failOnUnknownProperties(false);
    return builder.build();
  }

  @Getter
  @Setter
  public static class GithubApi {
    private String url;
    private String apiPath;
    private String username;
    private String password;
  }
}
