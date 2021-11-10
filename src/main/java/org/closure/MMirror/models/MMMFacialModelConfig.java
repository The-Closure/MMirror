package org.closure.MMirror.models;

import java.util.ArrayList;
import java.util.HashMap;

public class MMMFacialModelConfig {
    private float threshold;
    private boolean useUSBCam;
    private String trainingFile;
    private float interval;
    private float logoutDelay;
    ArrayList<String> users = new ArrayList<String>();
    private String defaultClass;
    private String everyoneClass;
    private boolean welcomeMessage;

    public float getThreshold() {
        return this.threshold;
    }

    public void setThreshold(float threshold) {
        this.threshold = threshold;
    }

    public boolean isUseUSBCam() {
        return this.useUSBCam;
    }

    public boolean getUseUSBCam() {
        return this.useUSBCam;
    }

    public void setUseUSBCam(boolean useUSBCam) {
        this.useUSBCam = useUSBCam;
    }

    public String getTrainingFile() {
        return this.trainingFile;
    }

    public void setTrainingFile(String trainingFile) {
        this.trainingFile = trainingFile;
    }

    public float getInterval() {
        return this.interval;
    }

    public void setInterval(float interval) {
        this.interval = interval;
    }

    public float getLogoutDelay() {
        return this.logoutDelay;
    }

    public void setLogoutDelay(float logoutDelay) {
        this.logoutDelay = logoutDelay;
    }

    public  ArrayList<String> getUsers() {
        return this.users;
    }

    public void setUsers(  ArrayList<String> users) {
        this.users = users;
    }

    public String getDefaultClass() {
        return this.defaultClass;
    }

    public void setDefaultClass(String defaultClass) {
        this.defaultClass = defaultClass;
    }

    public String getEveryoneClass() {
        return this.everyoneClass;
    }

    public void setEveryoneClass(String everyoneClass) {
        this.everyoneClass = everyoneClass;
    }

    public boolean isWelcomeMessage() {
        return this.welcomeMessage;
    }

    public boolean getWelcomeMessage() {
        return this.welcomeMessage;
    }

    public void setWelcomeMessage(boolean welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }

}