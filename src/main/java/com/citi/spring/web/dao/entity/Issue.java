package com.citi.spring.web.dao.entity;

import com.citi.spring.web.dao.data.CurrentlyWith;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
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
    public String toString() {
        return "Issue{" +
                "id=" + id +
                ", issueDescription='" + issueDescription + '\'' +
                ", solution='" + solution + '\'' +
                ", jira='" + jira + '\'' +
                ", workaround='" + workaround + '\'' +
                ", lastMod=" + lastMod +
                ", sourceSystem=" + sourceSystem +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Issue)) return false;
        Issue issue = (Issue) o;
        return Objects.equals(getId(), issue.getId()) &&
                Objects.equals(getIssueDescription(), issue.getIssueDescription()) &&
                Objects.equals(getSolution(), issue.getSolution()) &&
                Objects.equals(getJira(), issue.getJira()) &&
                Objects.equals(getWorkaround(), issue.getWorkaround()) &&
                Objects.equals(getLastMod(), issue.getLastMod()) &&
                getSourceSystem() == issue.getSourceSystem();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getIssueDescription(), getSolution(), getJira(), getWorkaround(), getLastMod(), getSourceSystem());
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

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = CurrentlyWith.valueOf(sourceSystem);
    }

    public void setSourceSystem(CurrentlyWith sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

}
