package org.closure.MMirror.entities;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users")
public class User {

    @Id
    private String id;

    private String name;
    @Column(nullable = false, unique = true, length = 45)
    private String email;
    private String password;
    private boolean google_account;
    private boolean is_active;
    //to check login and
    private boolean is_in;
    private Instant created_at;
    private String google_token;

    @JsonIgnore
    @OneToMany(mappedBy = "user", targetEntity = Event.class)
    private List<Event> events;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private Code code;

    public User() {
    }

    public User(String id, String name, String email, String password, boolean google_account, boolean is_active, boolean is_in, Instant created_at, String google_token, List<Event> events, Code code) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.google_account = google_account;
        this.is_active = is_active;
        this.is_in = is_in;
        this.created_at = created_at;
        this.google_token = google_token;
        this.events = events;
        this.code = code;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isGoogle_account() {
        return this.google_account;
    }

    public boolean getGoogle_account() {
        return this.google_account;
    }

    public void setGoogle_account(boolean google_account) {
        this.google_account = google_account;
    }

    public boolean isIs_active() {
        return this.is_active;
    }

    public boolean getIs_active() {
        return this.is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public boolean isIs_in() {
        return this.is_in;
    }

    public boolean getIs_in() {
        return this.is_in;
    }

    public void setIs_in(boolean is_in) {
        this.is_in = is_in;
    }

    public Instant getCreated_at() {
        return this.created_at;
    }

    public void setCreated_at(Instant created_at) {
        this.created_at = created_at;
    }

    public String getGoogle_token() {
        return this.google_token;
    }

    public void setGoogle_token(String google_token) {
        this.google_token = google_token;
    }

    public List<Event> getEvents() {
        return this.events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public Code getCode() {
        return this.code;
    }

    public void setCode(Code code) {
        this.code = code;
    }

    public User id(String id) {
        setId(id);
        return this;
    }

    public User name(String name) {
        setName(name);
        return this;
    }

    public User email(String email) {
        setEmail(email);
        return this;
    }

    public User password(String password) {
        setPassword(password);
        return this;
    }

    public User google_account(boolean google_account) {
        setGoogle_account(google_account);
        return this;
    }

    public User is_active(boolean is_active) {
        setIs_active(is_active);
        return this;
    }

    public User is_in(boolean is_in) {
        setIs_in(is_in);
        return this;
    }

    public User created_at(Instant created_at) {
        setCreated_at(created_at);
        return this;
    }

    public User google_token(String google_token) {
        setGoogle_token(google_token);
        return this;
    }

    public User events(List<Event> events) {
        setEvents(events);
        return this;
    }

    public User code(Code code) {
        setCode(code);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && google_account == user.google_account && is_active == user.is_active && is_in == user.is_in && Objects.equals(created_at, user.created_at) && Objects.equals(google_token, user.google_token) && Objects.equals(events, user.events) && Objects.equals(code, user.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password, google_account, is_active, is_in, created_at, google_token, events, code);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", email='" + getEmail() + "'" +
            ", password='" + getPassword() + "'" +
            ", google_account='" + isGoogle_account() + "'" +
            ", is_active='" + isIs_active() + "'" +
            ", is_in='" + isIs_in() + "'" +
            ", created_at='" + getCreated_at() + "'" +
            ", google_token='" + getGoogle_token() + "'" +
            ", events='" + getEvents() + "'" +
            ", code='" + getCode() + "'" +
            "}";
    }

}
