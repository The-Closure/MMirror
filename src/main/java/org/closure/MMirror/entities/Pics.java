package org.closure.MMirror.entities;

import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pics")
public class Pics {
    
    @Id
    private String id;

    private String url;

    @ManyToOne
    private User user;


    public Pics() {
    }

    public Pics(String id, String url, User user) {
        this.id = id;
        this.url = url;
        this.user = user;
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

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Pics id(String id) {
        setId(id);
        return this;
    }

    public Pics url(String url) {
        setUrl(url);
        return this;
    }

    public Pics user(User user) {
        setUser(user);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Pics)) {
            return false;
        }
        Pics pics = (Pics) o;
        return Objects.equals(id, pics.id) && Objects.equals(url, pics.url) && Objects.equals(user, pics.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, user);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", url='" + getUrl() + "'" +
            ", user='" + getUser() + "'" +
            "}";
    }


}
