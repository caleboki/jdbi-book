package org.jooby.books;

public class Book {

  private Integer id;

  private String title;

  private String isbn;

  private String author;

  public Integer getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getIsbn() {
    return isbn;
  }

  public String getAuthor() {
    return author;
  }

  public void setId(final Integer id) {
    this.id = id;
  }

  public void setTitle(final String title) {
    this.title = title;
  }

  public void setIsbn(final String isbn) {
    this.isbn = isbn;
  }

  public void setAuthor(final String author) {
    this.author = author;
  }

}
