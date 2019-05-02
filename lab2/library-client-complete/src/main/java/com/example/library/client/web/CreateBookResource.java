package com.example.library.client.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class CreateBookResource {

  private String isbn;

  private String title;

  private String description;

  private boolean borrowed;

  private String author;

  private User borrowedBy = null;

  @SuppressWarnings("unused")
  public CreateBookResource() {}

  public CreateBookResource(
      String isbn,
      String title,
      String description,
      String author,
      boolean borrowed,
      User borrowedBy) {
    this.isbn = isbn;
    this.title = title;
    this.description = description;
    this.author = author;
    this.borrowed = borrowed;
    this.borrowedBy = borrowedBy;
  }

  public String getIsbn() {
    return isbn;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public String getAuthor() {
    return author;
  }

  public boolean isBorrowed() {
    return borrowed;
  }

  public void setBorrowed(boolean borrowed) {
    this.borrowed = borrowed;
  }

  public User getBorrowedBy() {
    return borrowedBy;
  }

  public void setBorrowedBy(User borrowedBy) {
    this.borrowedBy = borrowedBy;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    CreateBookResource bookResource = (CreateBookResource) o;
    return borrowed == bookResource.borrowed
        && isbn.equals(bookResource.isbn)
        && title.equals(bookResource.title)
        && description.equals(bookResource.description)
        && author.equals(bookResource.author)
        && Objects.equals(borrowedBy, bookResource.borrowedBy);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        super.hashCode(), isbn, title, description, borrowed, author, borrowedBy);
  }

  @Override
  public String toString() {
    return "BookResource{"
        + ", isbn='"
        + isbn
        + '\''
        + ", title='"
        + title
        + '\''
        + ", description='"
        + description
        + '\''
        + ", borrowed="
        + borrowed
        + ", author="
        + author
        + ", borrowedBy="
        + borrowedBy
        + '}';
  }
}
