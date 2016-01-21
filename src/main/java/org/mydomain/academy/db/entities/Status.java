package org.mydomain.academy.db.entities;

import com.fasterxml.jackson.annotation.JsonView;
import org.mydomain.academy.SpringBoot.view.View;
import org.mydomain.academy.db.utils.formatters.BasicStringDateFormatter;
import org.mydomain.academy.db.utils.formatters.StringDateFormatter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

@Component
@Entity
@Table(name = "status_sql")
public class Status {

    @Id
    @GeneratedValue
    private long id;

    @JsonView(View.Summary.class)
    @JoinColumn(nullable = false)
    private String ipAddress;

    @JsonView(View.Summary.class)
    @Column(nullable = false)
    private Date lastUpdate;

    @Transient
    private StringDateFormatter sdf;

    public Status() {
        sdf = new BasicStringDateFormatter();
    }

    public Status(String ipAddress) {
        this.ipAddress = ipAddress;
        this.lastUpdate = new Date();
        sdf = new BasicStringDateFormatter();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public StringDateFormatter getSdf() {
        return sdf;
    }

    public void setSdf(StringDateFormatter sdf) {
        this.sdf = sdf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Status that = (Status) o;

        if (id != that.id) return false;
        if (ipAddress != null ? !ipAddress.equals(that.ipAddress) : that.ipAddress != null) return false;
        return !(lastUpdate != null ? !lastUpdate.equals(that.lastUpdate) : that.lastUpdate != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (ipAddress != null ? ipAddress.hashCode() : 0);
        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Status{" +
                "id: " + id +
                ", ipAddress: '" + ipAddress + '\'' +
                ", lastUpdate: " + lastUpdate +'}';
    }
}