package org.closure.MMirror.models;

import java.util.ArrayList;

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
   class Reminders {
    private boolean useDefault;
   
   
    // Getter Methods 
   
    public boolean getUseDefault() {
     return useDefault;
    }
   
    // Setter Methods 
   
    public void setUseDefault(boolean useDefault) {
     this.useDefault = useDefault;
    }
   }
   class End {
    private String dateTime;
    private String timeZone;
   
   
    // Getter Methods 
   
    public String getDateTime() {
     return dateTime;
    }
   
    public String getTimeZone() {
     return timeZone;
    }
   
    // Setter Methods 
   
    public void setDateTime(String dateTime) {
     this.dateTime = dateTime;
    }
   
    public void setTimeZone(String timeZone) {
     this.timeZone = timeZone;
    }
   }
   class Start {
    private String dateTime;
    private String timeZone;
   
   
    // Getter Methods 
   
    public String getDateTime() {
     return dateTime;
    }
   
    public String getTimeZone() {
     return timeZone;
    }
   
    // Setter Methods 
   
    public void setDateTime(String dateTime) {
     this.dateTime = dateTime;
    }
   
    public void setTimeZone(String timeZone) {
     this.timeZone = timeZone;
    }
   }
   class Organizer {
    private String email;
    private String displayName;
    private boolean self;
   
   
    // Getter Methods 
   
    public String getEmail() {
     return email;
    }
   
    public String getDisplayName() {
     return displayName;
    }
   
    public boolean getSelf() {
     return self;
    }
   
    // Setter Methods 
   
    public void setEmail(String email) {
     this.email = email;
    }
   
    public void setDisplayName(String displayName) {
     this.displayName = displayName;
    }
   
    public void setSelf(boolean self) {
     this.self = self;
    }
   }
   class Creator {
    private String email;
    private String displayName;
    private boolean self;
   
   
    // Getter Methods 
   
    public String getEmail() {
     return email;
    }
   
    public String getDisplayName() {
     return displayName;
    }
   
    public boolean getSelf() {
     return self;
    }
   
    // Setter Methods 
   
    public void setEmail(String email) {
     this.email = email;
    }
   
    public void setDisplayName(String displayName) {
     this.displayName = displayName;
    }
   
    public void setSelf(boolean self) {
     this.self = self;
    }
   }