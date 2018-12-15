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
@Table(name = "handover")
public class Handover {

    @Id
    @GeneratedValue()
    private int id;

    @UpdateTimestamp
    private Timestamp lastMod;

    private String reportedBy;
    private String emailSubject;
    private String tracking;
    private String comments;
    private Status status;
    private CurrentlyWith currentlyWith;
    private Environment environment;


    public Handover() {
    }

    public Handover(Timestamp lastMod, String reportedBy, String emailSubject, String tracking, String comments, Status status, CurrentlyWith currentlyWith, Environment environment) {
        this.lastMod = lastMod;
        this.reportedBy = reportedBy;
        this.emailSubject = emailSubject;
        this.tracking = tracking;
        this.comments = comments;
        this.status = status;
        this.currentlyWith = currentlyWith;
        this.environment = environment;
    }


    @Override
    public String toString() {
        return "Handover{" +
                "id=" + id +
                ", lastMod='" + lastMod + '\'' +
                ", reportedBy='" + reportedBy + '\'' +
                ", emailSubject='" + emailSubject + '\'' +
                ", tracking='" + tracking + '\'' +
                ", comments='" + comments + '\'' +
                ", status=" + status +
                ", currentlyWith=" + currentlyWith +
                ", environment=" + environment +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Handover)) return false;
        Handover that = (Handover) o;
        return Objects.equals(getLastMod(), that.getLastMod()) &&
                Objects.equals(getReportedBy(), that.getReportedBy()) &&
                Objects.equals(getEmailSubject(), that.getEmailSubject()) &&
                Objects.equals(getTracking(), that.getTracking()) &&
                Objects.equals(getComments(), that.getComments()) &&
                getStatus() == that.getStatus() &&
                getCurrentlyWith() == that.getCurrentlyWith() &&
                getEnvironment() == that.getEnvironment();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLastMod(), getReportedBy(), getEmailSubject(), getTracking(), getComments(), getStatus(), getCurrentlyWith(), getEnvironment());
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
