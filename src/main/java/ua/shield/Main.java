package ua.shield;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

/**
 * Created by sa on 13.09.17.
 */
public class Main {
     private static final String file="log.txt";
     private static final String longFormat="dd.MM.yyyy HH:mm:s";
     private static final String shotFormat="HH:mm:s";


    public static void main(String[] args) {

        //check if we have at least one argument
        if (args.length == 0) {
            System.out.println(Message.getBundle().getString("message.error"));
            System.exit(0);
        }

        String cityName = args[0];
        ZoneCity zoneCity = (args.length >= 2) ? new ZoneCity(cityName, args[1]) : new ZoneCity(args[0]);
        Message message = new Message(zoneCity);
        System.out.println(message.getMessageByZoneId());
        save(message);
    }

    private static void save(Message message){
        try {
            SimpleDateFormat dateformatLong = new SimpleDateFormat(longFormat);
            FileWriter fw = new FileWriter(file,true);
            fw.append("["+ dateformatLong.format(new Date() )+"]");
            fw.append("\n");
            fw.append("City:"+message.getZoneCity().getCityName());
            fw.append(" ");
            fw.append("ZoneId :"+message.getZoneCity().getZoneId().getId());
            fw.append("\n");
            fw.append("Time :"+message.getZoneCity().getLocalTimeZone().format( DateTimeFormatter.ofPattern(shotFormat)));
            fw.append("\n");
            fw.append("Result:");
            fw.append(message.getMessageByZoneId());
            fw.append("\n");
            fw.append("-----------------------");
            fw.append("\n");
            fw.flush();
            fw.close();
      } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

