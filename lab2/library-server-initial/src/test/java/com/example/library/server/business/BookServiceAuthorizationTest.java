package com.example.library.server.business;

import com.example.library.server.DataInitializer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class BookServiceAuthorizationTest {

  @Autowired
  private BookService cut;

  @Test
  void create() {}

  @Test
  void update() {}

  @WithMockUser
  @Test
  @DisplayName("for USER role")
  void findByIdentifierIsAuthorizedForRoleUser() {
    assertThat(cut.findByIdentifier(DataInitializer.BOOK_CLEAN_CODE_IDENTIFIER)).isPresent();
  }

  @WithMockUser(roles = "CURATOR")
  @Test
  @DisplayName("for CURATOR role")
  void findByIdentifierIsAuthorizedForRoleCurator() {
    assertThat(cut.findByIdentifier(DataInitializer.BOOK_CLEAN_CODE_IDENTIFIER)).isPresent();
  }

  @Test
  void findWithDetailsByIdentifier() {}

  @Test
  void borrowById() {}

  @Test
  void returnById() {}

  @Test
  void findAll() {}

  @Test
  void deleteByIdentifier() {}
}