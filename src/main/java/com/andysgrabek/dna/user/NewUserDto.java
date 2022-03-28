package com.andysgrabek.dna.user;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class NewUserDto implements Serializable {
    private final Long id;
    private final String login;
    private final String password;
    private final String name;
    private final Date creationDate;

    public NewUserDto(Long id, String login, String password, String name, Date creationDate) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.creationDate = creationDate;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
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
        NewUserDto entity = (NewUserDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.login, entity.login) &&
                Objects.equals(this.password, entity.password) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.creationDate, entity.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, name, creationDate);
    }

}
