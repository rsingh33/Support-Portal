package com.citi.spring.web.dao.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "excelrow")
public class ExcelRow {


    @Id
    @GeneratedValue
    private int id;
    private String jiraKey;
    private String summary;
    private String scriptLocation;
    private String engineer;
    private String comments;
    private String status;
    private String tester;
    private String lastModUser;

    public ExcelRow(String jiraKey, String summary, String scriptLocation, String engineer, String comments, String status, String tester, String lastModUser) {
        this.jiraKey = jiraKey;
        this.summary = summary;
        this.scriptLocation = scriptLocation;
        this.engineer = engineer;
        this.comments = comments;
        this.status = status;
        this.tester = tester;
        this.lastModUser = lastModUser;
    }

    public ExcelRow() {

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExcelRow)) return false;
        ExcelRow excelRow = (ExcelRow) o;
        return getId() == excelRow.getId() &&
                getJiraKey().equals(excelRow.getJiraKey()) &&
                getSummary().equals(excelRow.getSummary()) &&
                getScriptLocation().equals(excelRow.getScriptLocation()) &&
                getEngineer().equals(excelRow.getEngineer()) &&
                getComments().equals(excelRow.getComments()) &&
                getStatus().equals(excelRow.getStatus()) &&
                getTester().equals(excelRow.getTester()) &&
                getLastModUser().equals(excelRow.getLastModUser());
    }

    @Override
    public String toString() {
        return "ExcelRow{" +
                "id=" + id +
                ", jiraKey='" +  + '\'' +
                ", summary='" + summary + '\'' +
                ", scriptLocation='" + scriptLocation + '\'' +
                ", engineer='" + engineer + '\'' +
                ", comments='" + comments + '\'' +
                ", status='" + status + '\'' +
                ", tester='" + tester + '\'' +
                ", lastModUser='" + lastModUser + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getJiraKey(), getSummary(), getScriptLocation(), getEngineer(), getComments(), getStatus(), getTester(), getLastModUser());
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJiraKey() {
        return jiraKey;
    }

    public void setJiraKey(String jiraKey) {
        this.jiraKey = jiraKey;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getScriptLocation() {
        return scriptLocation;
    }

    public void setScriptLocation(String scriptLocation) {
        this.scriptLocation = scriptLocation;
    }

    public String getEngineer() {
        return engineer;
    }

    public void setEngineer(String engineer) {
        this.engineer = engineer;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTester() {
        return tester;
    }

    public void setTester(String tester) {
        this.tester = tester;
    }

    public String getLastModUser() {
        return lastModUser;
    }

    public void setLastModUser(String lastModUser) {
        this.lastModUser = lastModUser;
    }


//
//    public ExcelRow(String key, String summary, String scriptLocation, String engineer, String comments, String status, String tester) {
//
//        this.jiraKey = key;
//        this.summary = summary;
//        this.scriptLocation = scriptLocation;
//        this.engineer = engineer;
//        this.comments = comments;
//        this.status = status;
//        this.tester = tester;
//    }
//    public ExcelRow() {
//
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof ExcelRow)) return false;
//        ExcelRow excelRow = (ExcelRow) o;
//        return id == excelRow.id &&
//
//                Objects.equals(getJiraKey(), excelRow.getJiraKey()) &&
//                Objects.equals(getSummary(), excelRow.getSummary()) &&
//                Objects.equals(getScriptLocation(), excelRow.getScriptLocation()) &&
//                Objects.equals(getEngineer(), excelRow.getEngineer()) &&
//                Objects.equals(getComments(), excelRow.getComments()) &&
//                Objects.equals(getStatus(), excelRow.getStatus()) &&
//                Objects.equals(getTester(), excelRow.getTester());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, getJiraKey(), getSummary(), getScriptLocation(), getEngineer(), getComments(), getStatus(), getTester());
//    }
//
//    @Override
//    public String toString() {
//        return "ExcelRow{" +
//                "id=" + id +
//                ", jiraKey=" + jiraKey +
//                ", summary=" + summary +
//                ", scriptLocation=" + scriptLocation +
//                ", engineer=" + engineer +
//                ", comments=" + comments +
//                ", status=" + status +
//                ", tester=" + tester +
//                '}';
//    }
//
//    public String getJiraKey() {
//        return jiraKey;
//    }
//
//    public void setJiraKey(String jiraKey) {
//        this.jiraKey = jiraKey;
//    }
//
//    public String getSummary() {
//        return summary;
//    }
//
//    public void setSummary(String summary) {
//        this.summary = summary;
//    }
//
//    public String getScriptLocation() {
//        return scriptLocation;
//    }
//
//    public void setScriptLocation(String scriptLocation) {
//        this.scriptLocation = scriptLocation;
//    }
//
//    public String getEngineer() {
//        return engineer;
//    }
//
//    public void setEngineer(String engineer) {
//        this.engineer = engineer;
//    }
//
//    public String getComments() {
//        return comments;
//    }
//
//    public void setComments(String comments) {
//        this.comments = comments;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public String getTester() {
//        return tester;
//    }
//
//    public void setTester(String tester) {
//        this.tester = tester;
//    }
//
}
//
