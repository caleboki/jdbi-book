package org.jooby.books;

import java.util.List;

import org.jooby.Jooby;
import org.jooby.Results;
import org.jooby.jdbc.Jdbc;
import org.jooby.jdbi.Jdbi;
import org.jooby.json.Jackson;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.jooby.handlers.CorsHandler;

import com.typesafe.config.Config;

public class App extends Jooby {

  {
    use(new Jackson());

    use(new Jdbc());

    use(new Jdbi()
        // 1 dbi ready
        .doWith((final DBI dbi, final Config conf) -> {
          // 2 open a new handle
          try (Handle handle = dbi.open()) {
            // 3. execute script
            handle.execute(conf.getString("schema"));
          }
        }));


    use ("*", new CorsHandler());

    /** Pet API. */
    use("/api/books")
        /** List pets. */
        .get(req -> {
          return require(DBI.class).inTransaction((handle, status) -> {
            BookRepository repo = handle.attach(BookRepository.class);
            List<Book> books = repo.list();
            return books;
          });
        })
        /** Get a pet by ID. */
        .get("/:id", req -> {
          return require(DBI.class).inTransaction((handle, status) -> {
            int id = req.param("id").intValue();

            BookRepository repo = handle.attach(BookRepository.class);
            Book book = repo.findById(id);
            return book;
          });
        })
        /** Create a book. */
        .post(req -> {
          return require(DBI.class).inTransaction((handle, status) -> {
            // read from HTTP body
            Book book = req.body(Book.class);

            BookRepository repo = handle.attach(BookRepository.class);
            int bookId = repo.insert(book);
            book.setId(bookId);
            return book;
          });
        })
        /** Update a book. */
        .put(req -> {
          return require(DBI.class).inTransaction((handle, status) -> {
            // read from HTTP body
            Book book = req.body(Book.class);

            BookRepository repo = handle.attach(BookRepository.class);
            repo.update(book);
            return book;
          });

        })
        /** Delete a book by ID. */
        .delete("/:id", req -> {
          return require(DBI.class).inTransaction((handle, status) -> {
            int id = req.param("id").intValue();

            BookRepository repo = handle.attach(BookRepository.class);
            repo.deleteById(id);
            return Results.noContent();
          });
        });
  }

  public static void main(final String[] args) {
    run(App::new, args);
  }

}
