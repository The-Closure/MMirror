package org.closure.MMirror.models.google_event;

import java.util.ArrayList;
import java.util.Objects;

import org.closure.MMirror.models.Attendees;


public class GoogleEvent {
    private String kind;
    private String etag;
    private String id;
    private String status;
    private String htmlLink;
    private String created;
    private String updated;
    private String summary;
    Creator creator;
    Organizer organizer;
    Start start;
    End end;
    private String iCalUID;
    private float sequence;
    ArrayList < Attendees > attendees = new ArrayList < Attendees > ();
    Reminders reminders;
    private String eventType;
   

    public GoogleEvent() {
    }

    public GoogleEvent(String kind, String etag, String id, String status, String htmlLink, String created, String updated, String summary, Creator creator, Organizer organizer, Start start, End end, String iCalUID, float sequence, ArrayList<Attendees> attendees, Reminders reminders, String eventType) {
        this.kind = kind;
        this.etag = etag;
        this.id = id;
        this.status = status;
        this.htmlLink = htmlLink;
        this.created = created;
        this.updated = updated;
        this.summary = summary;
        this.creator = creator;
        this.organizer = organizer;
        this.start = start;
        this.end = end;
        this.iCalUID = iCalUID;
        this.sequence = sequence;
        this.attendees = attendees;
        this.reminders = reminders;
        this.eventType = eventType;
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

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHtmlLink() {
        return this.htmlLink;
    }

    public void setHtmlLink(String htmlLink) {
        this.htmlLink = htmlLink;
    }

    public String getCreated() {
        return this.created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return this.updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getSummary() {
        return this.summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Creator getCreator() {
        return this.creator;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
    }

    public Organizer getOrganizer() {
        return this.organizer;
    }

    public void setOrganizer(Organizer organizer) {
        this.organizer = organizer;
    }

    public Start getStart() {
        return this.start;
    }

    public void setStart(Start start) {
        this.start = start;
    }

    public End getEnd() {
        return this.end;
    }

    public void setEnd(End end) {
        this.end = end;
    }

    public String getICalUID() {
        return this.iCalUID;
    }

    public void setICalUID(String iCalUID) {
        this.iCalUID = iCalUID;
    }

    public float getSequence() {
        return this.sequence;
    }

    public void setSequence(float sequence) {
        this.sequence = sequence;
    }

    public ArrayList<Attendees> getAttendees() {
        return this.attendees;
    }

    public void setAttendees(ArrayList<Attendees> attendees) {
        this.attendees = attendees;
    }

    public Reminders getReminders() {
        return this.reminders;
    }

    public void setReminders(Reminders reminders) {
        this.reminders = reminders;
    }

    public String getEventType() {
        return this.eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public GoogleEvent kind(String kind) {
        setKind(kind);
        return this;
    }

    public GoogleEvent etag(String etag) {
        setEtag(etag);
        return this;
    }

    public GoogleEvent id(String id) {
        setId(id);
        return this;
    }

    public GoogleEvent status(String status) {
        setStatus(status);
        return this;
    }

    public GoogleEvent htmlLink(String htmlLink) {
        setHtmlLink(htmlLink);
        return this;
    }

    public GoogleEvent created(String created) {
        setCreated(created);
        return this;
    }

    public GoogleEvent updated(String updated) {
        setUpdated(updated);
        return this;
    }

    public GoogleEvent summary(String summary) {
        setSummary(summary);
        return this;
    }

    public GoogleEvent creator(Creator creator) {
        setCreator(creator);
        return this;
    }

    public GoogleEvent organizer(Organizer organizer) {
        setOrganizer(organizer);
        return this;
    }

    public GoogleEvent start(Start start) {
        setStart(start);
        return this;
    }

    public GoogleEvent end(End end) {
        setEnd(end);
        return this;
    }

    public GoogleEvent iCalUID(String iCalUID) {
        setICalUID(iCalUID);
        return this;
    }

    public GoogleEvent sequence(float sequence) {
        setSequence(sequence);
        return this;
    }

    public GoogleEvent attendees(ArrayList<Attendees> attendees) {
        setAttendees(attendees);
        return this;
    }

    public GoogleEvent reminders(Reminders reminders) {
        setReminders(reminders);
        return this;
    }

    public GoogleEvent eventType(String eventType) {
        setEventType(eventType);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof GoogleEvent)) {
            return false;
        }
        GoogleEvent googleEvent = (GoogleEvent) o;
        return Objects.equals(kind, googleEvent.kind) && Objects.equals(etag, googleEvent.etag) && Objects.equals(id, googleEvent.id) && Objects.equals(status, googleEvent.status) && Objects.equals(htmlLink, googleEvent.htmlLink) && Objects.equals(created, googleEvent.created) && Objects.equals(updated, googleEvent.updated) && Objects.equals(summary, googleEvent.summary) && Objects.equals(creator, googleEvent.creator) && Objects.equals(organizer, googleEvent.organizer) && Objects.equals(start, googleEvent.start) && Objects.equals(end, googleEvent.end) && Objects.equals(iCalUID, googleEvent.iCalUID) && sequence == googleEvent.sequence && Objects.equals(attendees, googleEvent.attendees) && Objects.equals(reminders, googleEvent.reminders) && Objects.equals(eventType, googleEvent.eventType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kind, etag, id, status, htmlLink, created, updated, summary, creator, organizer, start, end, iCalUID, sequence, attendees, reminders, eventType);
    }

    @Override
    public String toString() {
        return "{" +
            " kind='" + getKind() + "'" +
            ", etag='" + getEtag() + "'" +
            ", id='" + getId() + "'" +
            ", status='" + getStatus() + "'" +
            ", htmlLink='" + getHtmlLink() + "'" +
            ", created='" + getCreated() + "'" +
            ", updated='" + getUpdated() + "'" +
            ", summary='" + getSummary() + "'" +
            ", creator='" + getCreator() + "'" +
            ", organizer='" + getOrganizer() + "'" +
            ", start='" + getStart() + "'" +
            ", end='" + getEnd() + "'" +
            ", iCalUID='" + getICalUID() + "'" +
            ", sequence='" + getSequence() + "'" +
            ", attendees='" + getAttendees() + "'" +
            ", reminders='" + getReminders() + "'" +
            ", eventType='" + getEventType() + "'" +
            "}";
    }

   
   }
   