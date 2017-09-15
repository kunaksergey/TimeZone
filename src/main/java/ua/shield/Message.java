package ua.shield;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by sa on 13.09.17.
 */
public class Message {
    private ZoneCity zoneCity;
    private List<Salut> salutList = new ArrayList<>();

    {
        salutList.add(new Salut(getBundle().getString("message.morning"), LocalTime.of(6, 0), LocalTime.of(9, 0)));
        salutList.add(new Salut(getBundle().getString("message.afternoon"), LocalTime.of(9, 0), LocalTime.of(19, 0)));
        salutList.add(new Salut(getBundle().getString("message.evening"), LocalTime.of(19, 0), LocalTime.of(23, 0)));
        salutList.add(new Salut(getBundle().getString("message.night"), LocalTime.of(23, 0), LocalTime.of(6, 0)));

    }

    public Message() {
    }

    public Message(ZoneCity zoneCity) {
        this.zoneCity = zoneCity;
    }

    Salut getSalutByTime(LocalTime localTime) {
//        Optional<Salut> first = salutList.stream()
//                .filter(e -> {
//                            if (e.getStart().toSecondOfDay() > e.getEnd().toSecondOfDay()) {
//                                return e.getStart().toSecondOfDay() >= localTime.toSecondOfDay() || e.getStart().toSecondOfDay() < localTime.toSecondOfDay();
//                            } else
//                                return e.getStart().toSecondOfDay() <= localTime.toSecondOfDay() && e.getEnd().toSecondOfDay() > localTime.toSecondOfDay();
//                        }
//                )
//                .findFirst();
//        return first.isPresent() ? first.get() : new Salut(getBundle().getString("message.errorDateRange"));

        //changed on functional style
        return salutList.stream()
                .filter(e -> {
                            if (e.getStart().toSecondOfDay() > e.getEnd().toSecondOfDay()) {
                                return e.getStart().toSecondOfDay() >= localTime.toSecondOfDay() || e.getStart().toSecondOfDay() < localTime.toSecondOfDay();
                            } else
                                return e.getStart().toSecondOfDay() <= localTime.toSecondOfDay() && e.getEnd().toSecondOfDay() > localTime.toSecondOfDay();
                        }
                )
                .findFirst().orElse(new Salut(getBundle().getString("message.errorDateRange")));
    }

    public static ResourceBundle getBundle() {
        return ResourceBundle.getBundle("message", new UTF8Control());
    }

    String getMessageByZoneId() {
        return getSalutByTime(zoneCity.getLocalTimeZone()).getSalutation() + ", " + zoneCity.getCityName() + "!";
    }

    ZoneCity getZoneCity() {
        return zoneCity;
    }
}
