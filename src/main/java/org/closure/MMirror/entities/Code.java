package org.closure.MMirror.entities;

import java.time.Instant;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "codes")
public class Code {

    @Id
    private String code;

    private Instant created_at;
    private Instant expire_at;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = true)
    private User user;


    public Code() {
    }

    public Code(String code, Instant created_at, Instant expire_at, User user) {
        this.code = code;
        this.created_at = created_at;
        this.expire_at = expire_at;
        this.user = user;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Instant getCreated_at() {
        return this.created_at;
    }

    public void setCreated_at(Instant created_at) {
        this.created_at = created_at;
    }

    public Instant getExpire_at() {
        return this.expire_at;
    }

    public void setExpire_at(Instant expire_at) {
        this.expire_at = expire_at;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Code code(String code) {
        setCode(code);
        return this;
    }

    public Code created_at(Instant created_at) {
        setCreated_at(created_at);
        return this;
    }

    public Code expire_at(Instant expire_at) {
        setExpire_at(expire_at);
        return this;
    }

    public Code user(User user) {
        setUser(user);
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, created_at, expire_at, user);
    }

    @Override
    public String toString() {
        return "{" +
            " code='" + getCode() + "'" +
            ", created_at='" + getCreated_at() + "'" +
            ", expire_at='" + getExpire_at() + "'" +
            ", user='" + getUser() + "'" +
            "}";
    }

    
}
