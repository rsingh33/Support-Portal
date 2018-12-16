package com.citi.spring.web.dao.entity;


import com.citi.spring.web.validations.FormValidationGroup;
import com.citi.spring.web.validations.PersistenceValidationGroup;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "offers")
public class Offer {

    @Id
    @GeneratedValue
    private int id;


    @Size(min = 20, max = 100,groups = {PersistenceValidationGroup.class, FormValidationGroup.class})
    @Column(name = "text")
    private String text;

    @ManyToOne
    @JoinColumn(name = "username")
    private User user;

    public Offer() {
        this.user = new User();

    }


    public Offer(int id, User user, String text) {
        this.id = id;
        this.user = user;
        this.text = text;
    }

    public Offer(User user, String text) {

        this.user = user;
        this.text = text;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUsername() {
        return user.getusername();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Offer)) return false;
        Offer offer = (Offer) o;
        return Objects.equals(getText(), offer.getText()) &&
                Objects.equals(getUser(), offer.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getText(), getUser());
    }

    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                ", User='" + user + '\'' +
                ", text='" + text + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
