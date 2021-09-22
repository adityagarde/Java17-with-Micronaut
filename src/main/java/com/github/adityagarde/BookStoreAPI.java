package com.github.adityagarde;

import io.micronaut.data.repository.CrudRepository;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import java.util.Locale;

public sealed interface BookStoreAPI {

    @Get("/describe/{type}/{id}")
    String describe(String type, Long id);

    non-sealed interface BookClient extends BookStoreAPI {
    }

    @Controller("/")
    final class BookStoreController implements BookStoreAPI {
        private final BookRepository bookRepository;
        private final AuthorRepository authorRepository;

        public BookStoreController(BookRepository bookRepository, AuthorRepository authorRepository) {
            this.bookRepository = bookRepository;
            this.authorRepository = authorRepository;
        }

        @Override
        public String describe(String type, Long id) {
            CrudRepository<? extends Entity<Long>, Long> crudRepository =
                    switch (type.toLowerCase(Locale.ROOT)) {
                        case "book" -> this.bookRepository;
                        case "author" -> this.authorRepository;
                        default -> null;
                    };

            if (crudRepository != null) {
                Entity<Long> entity = crudRepository.findById(id).orElse(null);

                if (entity instanceof Book b) {
                    return b.title();
                } else if (entity instanceof Author a) {
                    return a.name();
                }
            }

            return null;
        }
    }
}