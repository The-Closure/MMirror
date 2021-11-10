package org.closure.MMirror.models;

import java.util.Objects;

public class PicsDto {

    private String id;

    private String url;

    public PicsDto() {
    }

    public PicsDto(String id, String url) {
        this.id = id;
        this.url = url;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public PicsDto id(String id) {
        setId(id);
        return this;
    }

    public PicsDto url(String url) {
        setUrl(url);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PicsDto)) {
            return false;
        }
        PicsDto picsDto = (PicsDto) o;
        return Objects.equals(id, picsDto.id) && Objects.equals(url, picsDto.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", url='" + getUrl() + "'" +
            "}";
    }
        
}
