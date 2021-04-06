package org.closure.MMirror.models;

import java.util.List;
import java.util.Objects;

public class UserDto {

    private String id;
    private String email;
    private String password;
    private String created_at;
    private List<EventDto> events;


    public UserDto() {
    }

    public UserDto(String id, String email, String password, String created_at, List<EventDto> events) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.created_at = created_at;
        this.events = events;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getCreated_at() {
        return this.created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
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

    public UserDto email(String email) {
        setEmail(email);
        return this;
    }

    public UserDto password(String password) {
        setPassword(password);
        return this;
    }

    public UserDto created_at(String created_at) {
        setCreated_at(created_at);
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
        return Objects.equals(id, userDto.id) && Objects.equals(email, userDto.email) && Objects.equals(password, userDto.password) && Objects.equals(created_at, userDto.created_at) && Objects.equals(events, userDto.events);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, created_at, events);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", email='" + getEmail() + "'" +
            ", password='" + getPassword() + "'" +
            ", created_at='" + getCreated_at() + "'" +
            ", events='" + getEvents() + "'" +
            "}";
    }

    
}
