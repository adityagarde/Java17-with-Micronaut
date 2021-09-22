package com.github.adityagarde;

public sealed interface Entity<ID> permits Author, Book {
    ID id();
}
