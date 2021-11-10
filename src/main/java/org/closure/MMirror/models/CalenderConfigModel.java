package org.closure.MMirror.models;


public class CalenderConfigModel {
    private String module;
    private String header;
    private String position;
    private String classes;
    CalenderConfigObjectModel ConfigObject;
   
   
    // Getter Methods 
   
    public String getModule() {
     return module;
    }
   
    public String getHeader() {
     return header;
    }
   
    public String getPosition() {
     return position;
    }
   
    public String getClasses() {
     return classes;
    }
   
    public CalenderConfigObjectModel getConfig() {
     return ConfigObject;
    }
   
    // Setter Methods 
   
    public void setModule(String module) {
     this.module = module;
    }
   
    public void setHeader(String header) {
     this.header = header;
    }
   
    public void setPosition(String position) {
     this.position = position;
    }
   
    public void setClasses(String classes) {
     this.classes = classes;
    }
   
    public void setConfig(CalenderConfigObjectModel configObject) {
     this.ConfigObject = configObject;
    }
   }
   
