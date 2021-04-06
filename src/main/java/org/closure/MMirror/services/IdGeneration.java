package org.closure.MMirror.services;

import java.math.BigInteger;
import java.security.SecureRandom;

import org.springframework.stereotype.Component;

@Component
public class IdGeneration {

     static final SecureRandom secureRandom = new SecureRandom();
    
     
     public static String getNextRandomString(){
         
         
         BigInteger bInt = new BigInteger(130, secureRandom);
         
         
         return bInt.toString(32);
         
     }
}
