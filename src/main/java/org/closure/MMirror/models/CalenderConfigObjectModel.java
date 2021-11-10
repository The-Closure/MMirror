package org.closure.MMirror.models;

import java.util.ArrayList;
import java.util.HashMap;

public class CalenderConfigObjectModel {
    ArrayList < HashMap<String,String> > calendars = new ArrayList < HashMap<String,String> > ();
   

    public ArrayList<HashMap<String,String>> getCalendars() {
        return this.calendars;
    }

    public void setCalendars(ArrayList<HashMap<String,String>> calendars) {
        this.calendars = calendars;
    }
   
   
   }