package com.citi.spring.web.dao.entity;


import com.citi.spring.web.validations.FormValidationGroup;
import com.citi.spring.web.validations.PersistenceValidationGroup;
import com.citi.spring.web.validations.ValidEmail;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

    @Id
    @NotBlank(groups = {PersistenceValidationGroup.class, FormValidationGroup.class})
    @Size(min = 6, max = 15, groups = {PersistenceValidationGroup.class, FormValidationGroup.class})
    @Pattern(regexp = "^\\w{6,}$", groups = {PersistenceValidationGroup.class, FormValidationGroup.class})
    @Column(name = "username")
    private String username;

    @NotBlank(groups = {PersistenceValidationGroup.class, FormValidationGroup.class})
    @Pattern(regexp = "^\\S+$", groups = {PersistenceValidationGroup.class, FormValidationGroup.class})
    @Size(min = 8, max = 15, groups = {FormValidationGroup.class})
    private String password;

    @ValidEmail(groups = {PersistenceValidationGroup.class, FormValidationGroup.class})
    private String email;

    private boolean enabled = false;

    private String authority;
    @Column(name = "reset_token")
    private String resetToken;

    @NotBlank(groups = {PersistenceValidationGroup.class, FormValidationGroup.class})
    @Size(min = 3, max = 60, groups = {PersistenceValidationGroup.class, FormValidationGroup.class})
    private String name;


    public User(String username, String name, String password, String email, boolean enabled, String authority) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.enabled = enabled;
        this.authority = authority;
    }

    public User(String username) {
        this.username = username;
    }

    public User() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return isEnabled() == user.isEnabled() &&
                Objects.equals(getusername(), user.getusername()) &&
                Objects.equals(getEmail(), user.getEmail()) &&
                Objects.equals(getAuthority(), user.getAuthority()) &&
                Objects.equals(getName(), user.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getusername(), getEmail(), isEnabled(), getAuthority(), getName());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", enabled=" + enabled +
                ", authority='" + authority + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getusername() {
        return username;
    }

    public void setusername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }
}
