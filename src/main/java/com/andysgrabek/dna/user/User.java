package com.andysgrabek.dna.user;

import com.andysgrabek.dna.joboffer.JobOffer;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Entity
public class User {

    public User() { }

    public User(Long id) {
        this.id = id;
    }

    public User(String login, byte[] password, String name, Date creationDate, byte[] salt) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.creationDate = creationDate;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @NotBlank
    private String login;
    @NotNull
    private byte[] password;
    @NotBlank
    private String name;
    @NotNull
    private Date creationDate;
    @NotNull
    private byte[] salt;

    @OneToMany(mappedBy = "user")
    private Set<JobOffer> jobOffers;


    public void setPassword(byte[] password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public byte[] getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Set<JobOffer> getJobOffers() {
        return jobOffers;
    }

    public byte[] getSalt() {
        return salt;
    }
}