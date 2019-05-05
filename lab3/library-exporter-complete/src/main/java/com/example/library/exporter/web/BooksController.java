package com.example.library.exporter.web;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient;

@Controller
public class BooksController {

  private final WebClient webClient;

  public BooksController(WebClient webClient) {
    this.webClient = webClient;
  }

  @GetMapping("/")
  Mono<String> index(@RegisteredOAuth2AuthorizedClient("keycloak_client") OAuth2AuthorizedClient authorizedClient, Model model) {

    return webClient
            .get().uri("http://localhost:9091/library-server/books")
            .attributes(oauth2AuthorizedClient(authorizedClient))
            .headers(h -> h.setBearerAuth(authorizedClient.getAccessToken().getTokenValue()))
            .retrieve()
            .onStatus(s -> s.equals(HttpStatus.UNAUTHORIZED), cr -> Mono.just(new BadCredentialsException("Not authenticated")))
            .onStatus(HttpStatus::is4xxClientError, cr -> Mono.just(new IllegalArgumentException(cr.statusCode().getReasonPhrase())))
            .onStatus(HttpStatus::is5xxServerError, cr -> Mono.just(new Exception(cr.statusCode().getReasonPhrase())))
            .bodyToMono(BookListResource.class)
            .log()
            .map(BookListResource::getBooks)
            .map(c -> { model.addAttribute("books", c); return "index"; });
  }
}
