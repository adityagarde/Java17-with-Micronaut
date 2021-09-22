package com.github.adityagarde;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@MappedEntity
public record Book(
        @GeneratedValue
        @Id
        @Nullable
        Long id,
        @NotBlank
        String title,
        @Positive
        int pages,
        @NotNull
        Author author) implements Entity<Long> {
}
