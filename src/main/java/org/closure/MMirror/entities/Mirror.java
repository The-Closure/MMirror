package org.closure.MMirror.entities;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "mirror")
public class Mirror {

    @Id
    private String id;

    @Column(unique = true,nullable = false)
    private String deviceid;

    private String soc; // system ownship

    private Date productionDate;

    private String productionLocation;

    private Date createdAt;

    @OneToOne
    private User user;

    public Mirror() {
    }

    public Mirror(String id, String deviceid, String soc, Date productionDate, String productionLocation, Date createdAt, User user) {
        this.id = id;
        this.deviceid = deviceid;
        this.soc = soc;
        this.productionDate = productionDate;
        this.productionLocation = productionLocation;
        this.createdAt = createdAt;
        this.user = user;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeviceid() {
        return this.deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public String getSoc() {
        return this.soc;
    }

    public void setSoc(String soc) {
        this.soc = soc;
    }

    public Date getProductionDate() {
        return this.productionDate;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    public String getProductionLocation() {
        return this.productionLocation;
    }

    public void setProductionLocation(String productionLocation) {
        this.productionLocation = productionLocation;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Mirror id(String id) {
        setId(id);
        return this;
    }

    public Mirror deviceid(String deviceid) {
        setDeviceid(deviceid);
        return this;
    }

    public Mirror soc(String soc) {
        setSoc(soc);
        return this;
    }

    public Mirror productionDate(Date productionDate) {
        setProductionDate(productionDate);
        return this;
    }

    public Mirror productionLocation(String productionLocation) {
        setProductionLocation(productionLocation);
        return this;
    }

    public Mirror createdAt(Date createdAt) {
        setCreatedAt(createdAt);
        return this;
    }

    public Mirror user(User user) {
        setUser(user);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Mirror)) {
            return false;
        }
        Mirror mirror = (Mirror) o;
        return Objects.equals(id, mirror.id) && Objects.equals(deviceid, mirror.deviceid) && Objects.equals(soc, mirror.soc) && Objects.equals(productionDate, mirror.productionDate) && Objects.equals(productionLocation, mirror.productionLocation) && Objects.equals(createdAt, mirror.createdAt) && Objects.equals(user, mirror.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, deviceid, soc, productionDate, productionLocation, createdAt, user);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", deviceid='" + getDeviceid() + "'" +
            ", soc='" + getSoc() + "'" +
            ", productionDate='" + getProductionDate() + "'" +
            ", productionLocation='" + getProductionLocation() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", user='" + getUser() + "'" +
            "}";
    }

}
