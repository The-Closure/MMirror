package org.closure.MMirror.models.google_event;

public class Organizer {
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