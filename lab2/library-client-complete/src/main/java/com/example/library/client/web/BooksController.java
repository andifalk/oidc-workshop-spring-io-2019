package com.example.library.client.web;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Controller
public class BooksController {

  private final WebClient webClient;

  public BooksController(WebClient webClient) {
    this.webClient = webClient;
  }

  @GetMapping("/")
  Mono<String> index(@AuthenticationPrincipal OAuth2User oauth2User, Model model) {

    model.addAttribute("fullname", oauth2User.getName());

    return webClient.get().uri("http://localhost:9091/library-service/books")
            .retrieve()
            .onStatus(s -> s.equals(HttpStatus.UNAUTHORIZED), cr -> Mono.just(new BadCredentialsException("Not authenticated")))
            .onStatus(HttpStatus::is4xxClientError, cr -> Mono.just(new IllegalArgumentException(cr.statusCode().getReasonPhrase())))
            .onStatus(HttpStatus::is5xxServerError, cr -> Mono.just(new Exception(cr.statusCode().getReasonPhrase())))
            .bodyToFlux(Book.class)
            .collectList()
            .map(bl -> { model.addAttribute("books", bl); return "index";});
  }

}
