package org.closure.MMirror.models.google_event;

import java.util.ArrayList;

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
    Creator CreatorObject;
    Organizer OrganizerObject;
    Start StartObject;
    End EndObject;
    private String iCalUID;
    private float sequence;
    ArrayList < Attendees > attendees = new ArrayList < Attendees > ();
    Reminders RemindersObject;
    private String eventType;
   
   
    // Getter Methods 
   
    public String getKind() {
     return kind;
    }
   
    public String getEtag() {
     return etag;
    }
   
    public String getId() {
     return id;
    }
   
    public String getStatus() {
     return status;
    }
   
    public String getHtmlLink() {
     return htmlLink;
    }
   
    public String getCreated() {
     return created;
    }
   
    public String getUpdated() {
     return updated;
    }
   
    public String getSummary() {
     return summary;
    }
   
    public Creator getCreator() {
     return CreatorObject;
    }
   
    public Organizer getOrganizer() {
     return OrganizerObject;
    }
   
    public Start getStart() {
     return StartObject;
    }
   
    public End getEnd() {
     return EndObject;
    }
   
    public String getICalUID() {
     return iCalUID;
    }
   
    public float getSequence() {
     return sequence;
    }
   
    public Reminders getReminders() {
     return RemindersObject;
    }
   
    public String getEventType() {
     return eventType;
    }
   
    // Setter Methods 
   
    public void setKind(String kind) {
     this.kind = kind;
    }
   
    public void setEtag(String etag) {
     this.etag = etag;
    }
   
    public void setId(String id) {
     this.id = id;
    }
   
    public void setStatus(String status) {
     this.status = status;
    }
   
    public void setHtmlLink(String htmlLink) {
     this.htmlLink = htmlLink;
    }
   
    public void setCreated(String created) {
     this.created = created;
    }
   
    public void setUpdated(String updated) {
     this.updated = updated;
    }
   
    public void setSummary(String summary) {
     this.summary = summary;
    }
   
    public void setCreator(Creator creatorObject) {
     this.CreatorObject = creatorObject;
    }
   
    public void setOrganizer(Organizer organizerObject) {
     this.OrganizerObject = organizerObject;
    }
   
    public void setStart(Start startObject) {
     this.StartObject = startObject;
    }
   
    public void setEnd(End endObject) {
     this.EndObject = endObject;
    }
   
    public void setICalUID(String iCalUID) {
     this.iCalUID = iCalUID;
    }
   
    public void setSequence(float sequence) {
     this.sequence = sequence;
    }
   
    public void setReminders(Reminders remindersObject) {
     this.RemindersObject = remindersObject;
    }
   
    public void setEventType(String eventType) {
     this.eventType = eventType;
    }
   }
   