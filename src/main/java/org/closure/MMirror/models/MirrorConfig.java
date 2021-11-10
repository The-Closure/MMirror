package org.closure.MMirror.models;

import java.util.ArrayList;
import java.util.HashMap;

public class MirrorConfig {
    private String address;
    private float port;
    private String basePath;
    ArrayList<String> ipWhitelist = new ArrayList<String>();
    private boolean useHttps;
    private String httpsPrivateKey;
    private String httpsCertificate;
    private String language;
    private String locale;
    ArrayList<String> logLevel = new ArrayList<String>();
    private float timeFormat;
    private String units;
    ArrayList<Object> modules = new ArrayList<Object>();

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getPort() {
        return this.port;
    }

    public void setPort(float port) {
        this.port = port;
    }

    public String getBasePath() {
        return this.basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public ArrayList<String> getIpWhitelist() {
        return this.ipWhitelist;
    }

    public void setIpWhitelist(ArrayList<String> ipWhitelist) {
        this.ipWhitelist = ipWhitelist;
    }

    public boolean isUseHttps() {
        return this.useHttps;
    }

    public boolean getUseHttps() {
        return this.useHttps;
    }

    public void setUseHttps(boolean useHttps) {
        this.useHttps = useHttps;
    }

    public String getHttpsPrivateKey() {
        return this.httpsPrivateKey;
    }

    public void setHttpsPrivateKey(String httpsPrivateKey) {
        this.httpsPrivateKey = httpsPrivateKey;
    }

    public String getHttpsCertificate() {
        return this.httpsCertificate;
    }

    public void setHttpsCertificate(String httpsCertificate) {
        this.httpsCertificate = httpsCertificate;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLocale() {
        return this.locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public ArrayList<String> getLogLevel() {
        return this.logLevel;
    }

    public void setLogLevel(ArrayList<String> logLevel) {
        this.logLevel = logLevel;
    }

    public float getTimeFormat() {
        return this.timeFormat;
    }

    public void setTimeFormat(float timeFormat) {
        this.timeFormat = timeFormat;
    }

    public String getUnits() {
        return this.units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public ArrayList<Object> getModules() {
        return this.modules;
    }

    public void setModules(ArrayList<Object> modules) {
        this.modules = modules;
    }

}