package com.example.authorizationcode.client.config;

import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.SslProvider;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.ProxyProvider;

@Configuration
public class WebClientConfiguration {

  @Profile("!proxy")
  @Bean
  WebClient webClient() {
    return WebClient.builder().build();
  }

  @Profile("proxy")
  @Bean
  WebClient webClientWithProxy() {
    HttpClient httpClient =
        HttpClient.create()
            .tcpConfiguration(
                tcpClient ->
                    tcpClient
                        .noSSL()
                        .proxy(
                            proxy ->
                                proxy.type(ProxyProvider.Proxy.HTTP).host("localhost").port(8085)));
    httpClient =
        httpClient.secure(
            (options) ->
                options.sslContext(
                    SslContextBuilder.forClient()
                        .sslProvider(SslProvider.JDK)
                        .trustManager(InsecureTrustManagerFactory.INSTANCE)));
    ReactorClientHttpConnector connector = new ReactorClientHttpConnector(httpClient);
    return WebClient.builder().clientConnector(connector).build();
  }
}
