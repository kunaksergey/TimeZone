package ua.shield;

import org.apache.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by sa on 13.09.17.
 */
public class Main {
    private static final String file = "log.txt";
    private static final String longFormat = "dd.MM.yyyy HH:mm:s";
    private static final String shotFormat = "HH:mm:s";
    final static Logger logger = Logger.getLogger(Main.class);


    public static void main(String[] args) {

        //check if we have at least one argument
        if (args.length == 0) {
            System.out.println(Message.getBundle().getString("message.errorEnteredArgument"));
            logger.debug("User didn't enter any argument");
            System.exit(0);
        }

        String cityName = args[0];
        ZoneCity zoneCity = null;

        if (args.length >= 2) {
            zoneCity = new ZoneCity(cityName, args[1]);
            logger.debug("User entered city: " + args[0] + " and zone:" + args[1]);
        } else {
            zoneCity = new ZoneCity(args[0]);
            logger.debug("User entered city: " + args[0]);
        }

        Message message = new Message(zoneCity);
        String result=message.getMessageByZoneId();

        /*Debug*/
        logger.debug("Selected zone: " + message.getZoneCity().getZoneId().getId());
        logger.debug("Time for selected zone: " + message.getZoneCity().getLocalTimeZone().format(DateTimeFormatter.ofPattern(shotFormat)));
        logger.debug("Result: "+result);
        /***********/

        System.out.println(result);

        //save(message); -disable
    }

    //save in file
    private static void save(Message message) {
        try {
            SimpleDateFormat dateformatLong = new SimpleDateFormat(longFormat);
            FileWriter fw = new FileWriter(file, true);
            fw.append("[" + dateformatLong.format(new Date()) + "]");
            fw.append("\n");
            fw.append("City:" + message.getZoneCity().getCityName());
            fw.append(" ");
            fw.append("ZoneId :" + message.getZoneCity().getZoneId().getId());
            fw.append("\n");
            fw.append("Time :" + message.getZoneCity().getLocalTimeZone().format(DateTimeFormatter.ofPattern(shotFormat)));
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

