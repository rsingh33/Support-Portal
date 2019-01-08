package com.citi.spring.web.dao.entity;

import com.citi.spring.web.dao.data.CurrentlyWith;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "issues")
public class Issue {

    @Id
    @GeneratedValue()
    private int id;
    private String issueDescription;
    private String solution;
    private String jira;
    private String workaround;

    @Override
    public String toString() {
        return "Issue{" +
                "id=" + id +
                ", issueDescription='" + issueDescription + '\'' +
                ", solution='" + solution + '\'' +
                ", jira='" + jira + '\'' +
                ", workaround='" + workaround + '\'' +
                ", username='" + username + '\'' +
                ", lastMod=" + lastMod +
                ", sourceSystem=" + sourceSystem +
                '}';
    }

    private String username;
    @UpdateTimestamp
    private Timestamp lastMod;
    private CurrentlyWith sourceSystem;
    public Issue() {
    }
    public Issue(String issueDescription, String solution, String jira, String workaround, Timestamp lastMod, CurrentlyWith sourceSystem) {
        this.issueDescription = issueDescription;
        this.solution = solution;
        this.jira = jira;
        this.workaround = workaround;
        this.lastMod = lastMod;
        this.sourceSystem = sourceSystem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Issue)) return false;
        Issue issue = (Issue) o;
        return getId() == issue.getId() &&
                Objects.equals(getIssueDescription(), issue.getIssueDescription()) &&
                Objects.equals(getSolution(), issue.getSolution()) &&
                Objects.equals(getJira(), issue.getJira()) &&
                Objects.equals(getWorkaround(), issue.getWorkaround()) &&
                Objects.equals(getUsername(), issue.getUsername()) &&
                Objects.equals(getLastMod(), issue.getLastMod()) &&
                getSourceSystem() == issue.getSourceSystem();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getIssueDescription(), getSolution(), getJira(), getWorkaround(), getUsername(), getLastMod(), getSourceSystem());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIssueDescription() {
        return issueDescription;
    }

    public void setIssueDescription(String issueDescription) {
        this.issueDescription = issueDescription;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getJira() {
        return jira;
    }

    public void setJira(String jira) {
        this.jira = jira;
    }

    public String getWorkaround() {
        return workaround;
    }

    public void setWorkaround(String workaround) {
        this.workaround = workaround;
    }

    public Timestamp getLastMod() {
        return lastMod;
    }

    public void setLastMod(Timestamp lastMod) {
        this.lastMod = lastMod;
    }

    public CurrentlyWith getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(CurrentlyWith sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = CurrentlyWith.valueOf(sourceSystem);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
