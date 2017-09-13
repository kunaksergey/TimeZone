package ua.shield;

/**
 * Created by sa on 13.09.17.
 */
public class Main {

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
    }

}

