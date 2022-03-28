package com.andysgrabek.dna.user;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class UserDto implements Serializable {
    private final Long id;
    private final String name;
    private final Date creationDate;

    public UserDto(Long id, String name, Date creationDate) {
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto entity = (UserDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.creationDate, entity.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, creationDate);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "creationDate = " + creationDate + ")";
    }
}
