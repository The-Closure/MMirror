package org.closure.MMirror.models;

import java.util.Date;
import java.util.Objects;

public class MirrorDto {
    private String id;
    private String deviceid;
    private String soc;
    private Date productionDate;
    private String productionLocation;
    private Date createdAt;
    private String userId;
    private String userName;

    public MirrorDto() {
    }

    public MirrorDto(String id, String deviceid, String soc, Date productionDate, String productionLocation, Date createdAt, String userId, String userName) {
        this.id = id;
        this.deviceid = deviceid;
        this.soc = soc;
        this.productionDate = productionDate;
        this.productionLocation = productionLocation;
        this.createdAt = createdAt;
        this.userId = userId;
        this.userName = userName;
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

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public MirrorDto id(String id) {
        setId(id);
        return this;
    }

    public MirrorDto deviceid(String deviceid) {
        setDeviceid(deviceid);
        return this;
    }

    public MirrorDto soc(String soc) {
        setSoc(soc);
        return this;
    }

    public MirrorDto productionDate(Date productionDate) {
        setProductionDate(productionDate);
        return this;
    }

    public MirrorDto productionLocation(String productionLocation) {
        setProductionLocation(productionLocation);
        return this;
    }

    public MirrorDto createdAt(Date createdAt) {
        setCreatedAt(createdAt);
        return this;
    }

    public MirrorDto userId(String userId) {
        setUserId(userId);
        return this;
    }

    public MirrorDto userName(String userName) {
        setUserName(userName);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof MirrorDto)) {
            return false;
        }
        MirrorDto mirrorDto = (MirrorDto) o;
        return Objects.equals(id, mirrorDto.id) && Objects.equals(deviceid, mirrorDto.deviceid) && Objects.equals(soc, mirrorDto.soc) && Objects.equals(productionDate, mirrorDto.productionDate) && Objects.equals(productionLocation, mirrorDto.productionLocation) && Objects.equals(createdAt, mirrorDto.createdAt) && Objects.equals(userId, mirrorDto.userId) && Objects.equals(userName, mirrorDto.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, deviceid, soc, productionDate, productionLocation, createdAt, userId, userName);
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
            ", userId='" + getUserId() + "'" +
            ", userName='" + getUserName() + "'" +
            "}";
    }
    
}
