package org.closure.MMirror.models;

import java.util.ArrayList;
import java.util.HashMap;

public class MMMFacialModel {
    private String module;
    MMMFacialModelConfig ConfigObject;
   
   
    // Getter Methods 
   
    public String getModule() {
     return module;
    }
   
    public MMMFacialModelConfig getConfig() {
     return ConfigObject;
    }
   
    // Setter Methods 
   
    public void setModule(String module) {
     this.module = module;
    }
   
    public void setConfig(MMMFacialModelConfig configObject) {
     this.ConfigObject = configObject;
    }
   }
   