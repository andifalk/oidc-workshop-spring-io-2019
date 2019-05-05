package com.example.library.exporter.web;

import java.util.Collection;

public class BookListResource {

  private Collection<BookResource> books;

  public BookListResource() {
  }

  public BookListResource(Collection<BookResource> books) {
    this.books = books;
  }

  public Collection<BookResource> getBooks() {
    return books;
  }

  public void setBooks(Collection<BookResource> books) {
    this.books = books;
  }
}
