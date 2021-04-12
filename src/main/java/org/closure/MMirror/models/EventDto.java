package org.closure.MMirror.models;

import java.util.Date;
import java.util.Objects;

import org.springframework.format.annotation.DateTimeFormat;

public class EventDto {

    private String id;
    private String summery;
    private String title;
    private String user_id;
    private String user_name;
    private String start;
    private String end;

    public EventDto() {
    }

    public EventDto(String id, String summery, String title, String user_id, String user_name, String start, String end) {
        this.id = id;
        this.summery = summery;
        this.title = title;
        this.user_id = user_id;
        this.user_name = user_name;
        this.start = start;
        this.end = end;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSummery() {
        return this.summery;
    }

    public void setSummery(String summery) {
        this.summery = summery;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUser_id() {
        return this.user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return this.user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getStart() {
        return this.start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return this.end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public EventDto id(String id) {
        setId(id);
        return this;
    }

    public EventDto summery(String summery) {
        setSummery(summery);
        return this;
    }

    public EventDto title(String title) {
        setTitle(title);
        return this;
    }

    public EventDto user_id(String user_id) {
        setUser_id(user_id);
        return this;
    }

    public EventDto user_name(String user_name) {
        setUser_name(user_name);
        return this;
    }

    public EventDto start(String start) {
        setStart(start);
        return this;
    }

    public EventDto end(String end) {
        setEnd(end);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof EventDto)) {
            return false;
        }
        EventDto eventDto = (EventDto) o;
        return Objects.equals(id, eventDto.id) && Objects.equals(summery, eventDto.summery) && Objects.equals(title, eventDto.title) && Objects.equals(user_id, eventDto.user_id) && Objects.equals(user_name, eventDto.user_name) && Objects.equals(start, eventDto.start) && Objects.equals(end, eventDto.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, summery, title, user_id, user_name, start, end);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", summery='" + getSummery() + "'" +
            ", title='" + getTitle() + "'" +
            ", user_id='" + getUser_id() + "'" +
            ", user_name='" + getUser_name() + "'" +
            ", start='" + getStart() + "'" +
            ", end='" + getEnd() + "'" +
            "}";
    }

}