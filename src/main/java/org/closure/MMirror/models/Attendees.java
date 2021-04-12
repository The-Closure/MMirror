package org.closure.MMirror.models;

import java.util.Objects;

public class Attendees {
    private String email;
    private String displayName;
    private boolean organizer;
    private boolean self;
    private String responseStatus;


    public Attendees() {
    }

    public Attendees(String email, String displayName, boolean organizer, boolean self, String responseStatus) {
        this.email = email;
        this.displayName = displayName;
        this.organizer = organizer;
        this.self = self;
        this.responseStatus = responseStatus;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public boolean isOrganizer() {
        return this.organizer;
    }

    public boolean getOrganizer() {
        return this.organizer;
    }

    public void setOrganizer(boolean organizer) {
        this.organizer = organizer;
    }

    public boolean isSelf() {
        return this.self;
    }

    public boolean getSelf() {
        return this.self;
    }

    public void setSelf(boolean self) {
        this.self = self;
    }

    public String getResponseStatus() {
        return this.responseStatus;
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }

    public Attendees email(String email) {
        setEmail(email);
        return this;
    }

    public Attendees displayName(String displayName) {
        setDisplayName(displayName);
        return this;
    }

    public Attendees organizer(boolean organizer) {
        setOrganizer(organizer);
        return this;
    }

    public Attendees self(boolean self) {
        setSelf(self);
        return this;
    }

    public Attendees responseStatus(String responseStatus) {
        setResponseStatus(responseStatus);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Attendees)) {
            return false;
        }
        Attendees attendees = (Attendees) o;
        return Objects.equals(email, attendees.email) && Objects.equals(displayName, attendees.displayName) && organizer == attendees.organizer && self == attendees.self && Objects.equals(responseStatus, attendees.responseStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, displayName, organizer, self, responseStatus);
    }

    @Override
    public String toString() {
        return "{" +
            " email='" + getEmail() + "'" +
            ", displayName='" + getDisplayName() + "'" +
            ", organizer='" + isOrganizer() + "'" +
            ", self='" + isSelf() + "'" +
            ", responseStatus='" + getResponseStatus() + "'" +
            "}";
    }

}