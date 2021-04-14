package org.closure.MMirror.entities;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "events")
public class Event {

    @Id
    String id;
    String title;
    String summery;
    String start;
    String end;
    String candidates;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Event() {
    }

    public Event(String id, String title, String summery,String start,String end, String candidates, User user) {
        this.id = id;
        this.title = title;
        this.summery = summery;
        this.start = start;
        this.end = end;
        this.candidates = candidates;
        this.user = user;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummery() {
        return this.summery;
    }

    public void setSummery(String summery) {
        this.summery = summery;
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

    public String getCandidates() {
        return this.candidates;
    }

    public void setCandidates(String candidates) {
        this.candidates = candidates;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event id(String id) {
        setId(id);
        return this;
    }

    public Event title(String title) {
        setTitle(title);
        return this;
    }

    public Event summery(String summery) {
        setSummery(summery);
        return this;
    }

    public Event start(String start) {
        setStart(start);
        return this;
    }

    public Event end(String end) {
        setEnd(end);
        return this;
    }

    public Event candidates(String candidates) {
        setCandidates(candidates);
        return this;
    }

    public Event user(User user) {
        setUser(user);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Event)) {
            return false;
        }
        Event event = (Event) o;
        return Objects.equals(id, event.id) && Objects.equals(title, event.title) && Objects.equals(summery, event.summery) && Objects.equals(start, event.start) && Objects.equals(end, event.end) && Objects.equals(candidates, event.candidates) && Objects.equals(user, event.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, summery, start, end, candidates, user);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", title='" + getTitle() + "'" +
            ", summery='" + getSummery() + "'" +
            ", start='" + getStart() + "'" +
            ", end='" + getEnd() + "'" +
            ", candidates='" + getCandidates() + "'" +
            ", user='" + getUser() + "'" +
            "}";
    }


}