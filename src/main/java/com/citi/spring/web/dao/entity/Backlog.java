package com.citi.spring.web.dao.entity;


import com.citi.spring.web.dao.data.CurrentlyWith;
import com.citi.spring.web.dao.data.Environment;
import com.citi.spring.web.dao.data.Status;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "backlog")
public class Backlog {

    @Id
    @GeneratedValue()
    private int id;

    @UpdateTimestamp
    private Timestamp lastMod;

    private String reportedBy;
    private String emailSubject;
    private String tracking;
    private String comments;
    private String username;
    private Status status;
    private CurrentlyWith currentlyWith;
    private Environment environment;
  
    public Backlog() {
    }

    public Backlog(Timestamp lastMod, String reportedBy, String emailSubject, String tracking, String comments, String username, Status status, CurrentlyWith currentlyWith, Environment environment) {
        this.lastMod = lastMod;
        this.reportedBy = reportedBy;
        this.emailSubject = emailSubject;
        this.tracking = tracking;
        this.comments = comments;
        this.username = username;
        this.status = status;
        this.currentlyWith = currentlyWith;
        this.environment = environment;
    }

    @Override
    public String toString() {
        return "Backlog{" +
                "id=" + id +
                ", lastMod=" + lastMod +
                ", reportedBy='" + reportedBy + '\'' +
                ", emailSubject='" + emailSubject + '\'' +
                ", tracking='" + tracking + '\'' +
                ", comments='" + comments + '\'' +
                ", username='" + username + '\'' +
                ", status=" + status +
                ", currentlyWith=" + currentlyWith +
                ", environment=" + environment +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Backlog)) return false;
        Backlog backlog = (Backlog) o;
        return getId() == backlog.getId() &&
                Objects.equals(getLastMod(), backlog.getLastMod()) &&
                Objects.equals(getReportedBy(), backlog.getReportedBy()) &&
                Objects.equals(getEmailSubject(), backlog.getEmailSubject()) &&
                Objects.equals(getTracking(), backlog.getTracking()) &&
                Objects.equals(getComments(), backlog.getComments()) &&
                Objects.equals(getUsername(), backlog.getUsername()) &&
                getStatus() == backlog.getStatus() &&
                getCurrentlyWith() == backlog.getCurrentlyWith() &&
                getEnvironment() == backlog.getEnvironment();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLastMod(), getReportedBy(), getEmailSubject(), getTracking(), getComments(), getUsername(), getStatus(), getCurrentlyWith(), getEnvironment());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Timestamp getLastMod() {
        return lastMod;
    }

    public void setLastMod(Timestamp lastMod) {
        this.lastMod = lastMod;
    }

    public String getReportedBy() {
        return reportedBy;
    }

    public void setReportedBy(String reportedBy) {
        this.reportedBy = reportedBy;
    }

    public String getEmailSubject() {
        return emailSubject;
    }

    public void setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
    }

    public String getTracking() {
        return tracking;
    }

    public void setTracking(String tracking) {
        this.tracking = tracking;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = Status.valueOf(status);
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public CurrentlyWith getCurrentlyWith() {
        return currentlyWith;
    }

    public void setCurrentlyWith(CurrentlyWith currentlyWith) {
        this.currentlyWith = currentlyWith;
    }

    public void setCurrentlyWith(String currentlyWith) {
        this.currentlyWith = CurrentlyWith.valueOf(currentlyWith);
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public void setEnvironment(String environment) {
        this.environment = Environment.valueOf(environment);
    }
}
