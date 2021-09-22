package com.github.adityagarde;

import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@MicronautTest
class AuthorTest {

    @Inject
    AuthorRepository authorRepository;

    @Inject
    BookRepository bookRepository;

    // @Test
    void testItWorks(BookStoreAPI.BookClient bookClient) {
        Author author = authorRepository.findAll().iterator().next();

        String result = bookClient.describe("author", author.id());

        Assertions.assertEquals("R. K. Narayan", result);

/*        Assertions.assertEquals(
                2, authorRepository.count()
        );*/
    }

    @Client("/")
    interface TestBookStoreClient extends BookStoreAPI.BookClient {
    }
}