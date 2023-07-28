package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;;

public class Logger {
    public Logger(File logger) {
        this.logger = logger;
    }
    private File logger;
    public void write (String message) {

         String pattern = "MM/dd/yyyy hh:mm:ss a";
         SimpleDateFormat date = new SimpleDateFormat(pattern);
         String timestamp = date.format(new Date());
        try{
            File newFile = new File ("Logger.txt");
            PrintWriter writer = null;

            if(newFile.exists()) {
                writer = new PrintWriter(new FileOutputStream(newFile.getAbsoluteFile(), true));
            }else{
                writer = new PrintWriter(newFile.getAbsoluteFile());
            }
            writer.append(timestamp + " " + message + "\n");
            writer.flush();
            writer.close();
        }catch (FileNotFoundException e) {

        }
    }

}
