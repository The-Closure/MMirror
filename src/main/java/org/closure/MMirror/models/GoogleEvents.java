package org.closure.MMirror.models;

import java.util.ArrayList;
import java.util.Objects;

public class GoogleEvents {
    private String kind;
    private String etag;
    private String summary;
    private String updated;
    private String timeZone;
    private String accessRole;
    ArrayList < Object > defaultReminders = new ArrayList < Object > ();
    private String nextSyncToken;
    ArrayList < GoogleEvent > items = new ArrayList < GoogleEvent > ();

    public GoogleEvents() {
    }

    public GoogleEvents(String kind, String etag, String summary, String updated, String timeZone, String accessRole, ArrayList<Object> defaultReminders, String nextSyncToken, ArrayList<GoogleEvent> items) {
        this.kind = kind;
        this.etag = etag;
        this.summary = summary;
        this.updated = updated;
        this.timeZone = timeZone;
        this.accessRole = accessRole;
        this.defaultReminders = defaultReminders;
        this.nextSyncToken = nextSyncToken;
        this.items = items;
    }

    public String getKind() {
        return this.kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getEtag() {
        return this.etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public String getSummary() {
        return this.summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getUpdated() {
        return this.updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getTimeZone() {
        return this.timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getAccessRole() {
        return this.accessRole;
    }

    public void setAccessRole(String accessRole) {
        this.accessRole = accessRole;
    }

    public ArrayList<Object> getDefaultReminders() {
        return this.defaultReminders;
    }

    public void setDefaultReminders(ArrayList<Object> defaultReminders) {
        this.defaultReminders = defaultReminders;
    }

    public String getNextSyncToken() {
        return this.nextSyncToken;
    }

    public void setNextSyncToken(String nextSyncToken) {
        this.nextSyncToken = nextSyncToken;
    }

    public ArrayList<GoogleEvent> getItems() {
        return this.items;
    }

    public void setItems(ArrayList<GoogleEvent> items) {
        this.items = items;
    }

    public GoogleEvents kind(String kind) {
        setKind(kind);
        return this;
    }

    public GoogleEvents etag(String etag) {
        setEtag(etag);
        return this;
    }

    public GoogleEvents summary(String summary) {
        setSummary(summary);
        return this;
    }

    public GoogleEvents updated(String updated) {
        setUpdated(updated);
        return this;
    }

    public GoogleEvents timeZone(String timeZone) {
        setTimeZone(timeZone);
        return this;
    }

    public GoogleEvents accessRole(String accessRole) {
        setAccessRole(accessRole);
        return this;
    }

    public GoogleEvents defaultReminders(ArrayList<Object> defaultReminders) {
        setDefaultReminders(defaultReminders);
        return this;
    }

    public GoogleEvents nextSyncToken(String nextSyncToken) {
        setNextSyncToken(nextSyncToken);
        return this;
    }

    public GoogleEvents items(ArrayList<GoogleEvent> items) {
        setItems(items);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof GoogleEvents)) {
            return false;
        }
        GoogleEvents googleEvents = (GoogleEvents) o;
        return Objects.equals(kind, googleEvents.kind) && Objects.equals(etag, googleEvents.etag) && Objects.equals(summary, googleEvents.summary) && Objects.equals(updated, googleEvents.updated) && Objects.equals(timeZone, googleEvents.timeZone) && Objects.equals(accessRole, googleEvents.accessRole) && Objects.equals(defaultReminders, googleEvents.defaultReminders) && Objects.equals(nextSyncToken, googleEvents.nextSyncToken) && Objects.equals(items, googleEvents.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kind, etag, summary, updated, timeZone, accessRole, defaultReminders, nextSyncToken, items);
    }

    @Override
    public String toString() {
        return "{" +
            " kind='" + getKind() + "'" +
            ", etag='" + getEtag() + "'" +
            ", summary='" + getSummary() + "'" +
            ", updated='" + getUpdated() + "'" +
            ", timeZone='" + getTimeZone() + "'" +
            ", accessRole='" + getAccessRole() + "'" +
            ", defaultReminders='" + getDefaultReminders() + "'" +
            ", nextSyncToken='" + getNextSyncToken() + "'" +
            ", items='" + getItems() + "'" +
            "}";
    }
   
   }
