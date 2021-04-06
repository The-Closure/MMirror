package org.closure.MMirror.models;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

public class UserDto {

    private String id;
    private String name;
    private String email;
    private String password;
    private Instant created_at;
    private boolean is_active;
    private List<EventDto> events;


    public UserDto() {
    }

    public UserDto(String id, String name, String email, String password, Instant created_at, boolean is_active, List<EventDto> events) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.created_at = created_at;
        this.is_active = is_active;
        this.events = events;
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

    public Instant getCreated_at() {
        return this.created_at;
    }

    public void setCreated_at(Instant created_at) {
        this.created_at = created_at;
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

    public List<EventDto> getEvents() {
        return this.events;
    }

    public void setEvents(List<EventDto> events) {
        this.events = events;
    }

    public UserDto id(String id) {
        setId(id);
        return this;
    }

    public UserDto name(String name) {
        setName(name);
        return this;
    }

    public UserDto email(String email) {
        setEmail(email);
        return this;
    }

    public UserDto password(String password) {
        setPassword(password);
        return this;
    }

    public UserDto created_at(Instant created_at) {
        setCreated_at(created_at);
        return this;
    }

    public UserDto is_active(boolean is_active) {
        setIs_active(is_active);
        return this;
    }

    public UserDto events(List<EventDto> events) {
        setEvents(events);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof UserDto)) {
            return false;
        }
        UserDto userDto = (UserDto) o;
        return Objects.equals(id, userDto.id) && Objects.equals(name, userDto.name) && Objects.equals(email, userDto.email) && Objects.equals(password, userDto.password) && Objects.equals(created_at, userDto.created_at) && is_active == userDto.is_active && Objects.equals(events, userDto.events);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password, created_at, is_active, events);
    }

    @Override
    public String toString() {
        return "{" +
            " \"id\" : \"" + getId() + "\"" +
            ", \"name\" : \"" + getName() + "\"" +
            ", \"email\" : \"" + getEmail() + "\"" +
            ", \"password\" : \"" + getPassword() + "\"" +
            ", \"created_at\" : \"" + getCreated_at() + "\"" +
            ", \"is_active\" : \"" + isIs_active() + "\"" +
            ", \"events\" : \"" + getEvents() + "\"" +
            "}";
    }

}