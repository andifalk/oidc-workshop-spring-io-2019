package com.example.library.server.business;

import com.example.library.server.dataaccess.Book;
import com.example.library.server.dataaccess.BookRepository;
import com.example.library.server.dataaccess.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.IdGenerator;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class BookService {

  private final BookRepository bookRepository;
  private final UserRepository userRepository;
  private final IdGenerator idGenerator;

  @Autowired
  public BookService(
      BookRepository bookRepository, UserRepository userRepository, IdGenerator idGenerator) {
    this.bookRepository = bookRepository;
    this.userRepository = userRepository;
    this.idGenerator = idGenerator;
  }

  @Transactional
  @PreAuthorize("hasRole('CURATOR')")
  public UUID create(Book book) {
    if (book.getIdentifier() == null) {
      book.setIdentifier(idGenerator.generateId());
    }
    return bookRepository.save(book).getIdentifier();
  }

  @Transactional
  @PreAuthorize("hasRole('CURATOR')")
  public UUID update(Book book) {
    return bookRepository.save(book).getIdentifier();
  }

  public Optional<Book> findByIdentifier(UUID uuid) {
    return bookRepository.findOneByIdentifier(uuid);
  }

  @PreAuthorize("hasAnyRole('USER', 'CURATOR')")
  public Optional<Book> findWithDetailsByIdentifier(UUID uuid) {
    return bookRepository.findOneWithDetailsByIdentifier(uuid);
  }

  @Transactional
  @PreAuthorize("hasRole('USER')")
  public void borrowById(UUID bookIdentifier, UUID userIdentifier) {

    if (bookIdentifier == null || userIdentifier == null) {
      return;
    }

    userRepository
        .findOneByIdentifier(userIdentifier)
        .ifPresent(
            u ->
                bookRepository
                    .findOneByIdentifier(bookIdentifier)
                    .ifPresent(
                        b -> {
                          b.doBorrow(u);
                          bookRepository.save(b);
                        }));
  }

  @Transactional
  @PreAuthorize("hasRole('USER')")
  public void returnById(UUID bookIdentifier, UUID userIdentifier) {

    if (bookIdentifier == null || userIdentifier == null) {
      return;
    }

    userRepository
        .findOneByIdentifier(userIdentifier)
        .ifPresent(
            u ->
                bookRepository
                    .findOneByIdentifier(bookIdentifier)
                    .ifPresent(
                        b -> {
                          b.doReturn(u);
                          bookRepository.save(b);
                        }));
  }

  @PreAuthorize("hasAnyRole('USER', 'CURATOR')")
  public List<Book> findAll() {
    return bookRepository.findAll();
  }

  @Transactional
  @PreAuthorize("hasRole('CURATOR')")
  public void deleteByIdentifier(UUID bookIdentifier) {
    bookRepository.deleteBookByIdentifier(bookIdentifier);
  }
}
