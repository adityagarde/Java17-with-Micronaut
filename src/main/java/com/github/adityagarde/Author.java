package com.github.adityagarde;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;

@MappedEntity
public record Author(
        @GeneratedValue
        @Id
        @Nullable
        Long id,
        String name)
        implements Entity<Long> {
}
