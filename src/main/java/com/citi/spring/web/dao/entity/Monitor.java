package com.citi.spring.web.dao.entity;

import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "Monitor")
public class Monitor {
    String name;
    String link;
    String env;
    String hostname;
    Boolean status;

    @UpdateTimestamp
    Timestamp lastRefreshed;
    @Id
    @GeneratedValue
    private int id;

    public Monitor(String name, String link, String env, String hostname, Boolean status, Timestamp lastRefreshed) {
        this.name = name;
        this.link = link;
        this.env = env;
        this.hostname = hostname;
        this.status = status;
        this.lastRefreshed = lastRefreshed;
    }

    public Monitor(String name, String link, String env, String hostname, Boolean status) {
        this.name = name;
        this.link = link;
        this.env = env;
        this.hostname = hostname;
        this.status = status;
    }

    public Monitor(String name) {
        this.name = name;
    }

    public Monitor(String name, String link, String env, String hostname) {
        this.name = name;
        this.link = link;
        this.env = env;
        this.hostname = hostname;
    }

    public Monitor() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Monitor)) return false;
        Monitor monitor = (Monitor) o;
        return getId() == monitor.getId() &&
                Objects.equals(getName(), monitor.getName()) &&
                Objects.equals(getLink(), monitor.getLink()) &&
                Objects.equals(getEnv(), monitor.getEnv()) &&
                Objects.equals(getHostname(), monitor.getHostname()) &&
                Objects.equals(getStatus(), monitor.getStatus()) &&
                Objects.equals(getLastRefreshed(), monitor.getLastRefreshed());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getLink(), getEnv(), getHostname(), getStatus(), getLastRefreshed(), getId());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Monitor{" +
                "name='" + name + '\'' +
                ", link='" + link + '\'' +
                ", env='" + env + '\'' +
                ", hostname='" + hostname + '\'' +
                ", status=" + status +
                ", lastRefreshed=" + lastRefreshed +
                '}';
    }

    public Timestamp getLastRefreshed() {
        return lastRefreshed;
    }

    public void setLastRefreshed(Timestamp lastRefreshed) {
        this.lastRefreshed = lastRefreshed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}