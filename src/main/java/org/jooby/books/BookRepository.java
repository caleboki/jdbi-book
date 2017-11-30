package org.jooby.books;

import java.io.Closeable;
import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.helpers.MapResultAsBean;

/**
 * Basic CRUD operations around {@link Book}.
 */
public interface BookRepository extends Closeable {

  /**
   * @return List all books.
   */
  @SqlQuery("select * from books")
  @MapResultAsBean
  List<Book> list();

  /**
   * Find a book by ID.
   *
   * @param id Book ID.
   * @return A book.
   */
  @SqlQuery("select * from books where id = :id")
  @MapResultAsBean
  Book findById(@Bind("id") int id);

  /**
   * Insert a new book.
   *
   * @param book A book.
   * @return The generated key.
   */
  @SqlUpdate("insert into books (title, isbn, author) values (:book.title, :book.isbn, :book.author)")
  @GetGeneratedKeys
  int insert(@BindBean("book") Book book);




  /**
   * Update a book by ID.
   *
   * @param book Book to update.
   */
  @SqlUpdate("update books set title=:book.title, isbn=:book.isbn, author=:book.author where id=:book.id")
  void update(@BindBean("book") Book book);

  /**
   * Delete a book by ID.
   *
   * @param id Book ID.
   */
  @SqlUpdate("delete books where id = :id")
  void deleteById(@Bind("id") int id);
}
