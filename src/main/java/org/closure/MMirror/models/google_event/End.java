package org.closure.MMirror.models.google_event;

import java.util.Objects;

public class End {
    private String dateTime;
    private String timeZone;
   

    public End() {
    }

    public End(String dateTime, String timeZone) {
        this.dateTime = dateTime;
        this.timeZone = timeZone;
    }

    public String getDateTime() {
        return this.dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getTimeZone() {
        return this.timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public End dateTime(String dateTime) {
        setDateTime(dateTime);
        return this;
    }

    public End timeZone(String timeZone) {
        setTimeZone(timeZone);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof End)) {
            return false;
        }
        End end = (End) o;
        return Objects.equals(dateTime, end.dateTime) && Objects.equals(timeZone, end.timeZone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateTime, timeZone);
    }

    @Override
    public String toString() {
        return "{" +
            " dateTime='" + getDateTime() + "'" +
            ", timeZone='" + getTimeZone() + "'" +
            "}";
    }
   
   }
