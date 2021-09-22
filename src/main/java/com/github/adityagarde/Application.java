package com.github.adityagarde;

import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.Micronaut;
import io.micronaut.runtime.event.annotation.EventListener;
import jakarta.inject.Singleton;

import javax.transaction.Transactional;
import java.util.Arrays;

@Singleton
public class Application {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public Application(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
        System.out.println(System.getProperty("java.version"));
    }

    @EventListener
    @Transactional
    void startup(StartupEvent startupEvent) {
        Author savedAuthor = authorRepository.save(new Author(null, "R. K. Narayan"));
        System.out.println("savedAuthor id == " + savedAuthor.id());

        bookRepository.saveAll(Arrays.asList(
                new Book(null, "The Guide", 350, savedAuthor),
                new Book(null, "Malgudi Days", 400, savedAuthor),
                new Book(null, "A tiger for Malgudi", 300, savedAuthor)
        ));
    }
}
