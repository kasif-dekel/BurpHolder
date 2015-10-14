package burp;

import java.io.*;
import java.util.HashMap;

public class IniParser {

     public static HashMap<String, String> ParseIni(String rawdata, String separatorToken) throws IOException {
          HashMap<String, String> properties = new HashMap<String, String>();
          String[] lines = rawdata.split(System.getProperty("line.separator"));
          for (String s: lines) {           
        	  	String[] pair = s.split(separatorToken); 
        	  	if( pair.length == 2 ) {
                    properties.put(pair[0].trim(), pair[1].trim());
               }
        	}
          return properties;
     }
}